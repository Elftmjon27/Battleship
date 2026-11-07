import java.util.Scanner;
public class Battleship {
    private Scanner sc = new Scanner(System.in);
    private Board b = new Board();

    private boolean shipLen3 = false;
    private boolean shipLen4 = false;
    
    public Battleship() {
        System.out.println("Welcome to Battleship!");
        setUp();
    }

    private void setUp() {
        System.out.println("Type \"a\" to add a new ship, \"b\" to see the board, \"p\" to play or \"q\" to quit.");
        String input = sc.nextLine();

        // guides all possible actions
        if (input.equals("a")) {
            addShip();
        } else if (input.equals("b")) {
            System.out.println("\n" + b.toString());
        } else if (input.equals("p")) {
            if (shipLen3 && shipLen4) {
                System.out.println("Ok, let's play!");
                play();
                return;
            } else {
                System.out.println("You need ships of length 3 and 4 to play!");
            }
        } else if (input.equals("q")) {
            return;
        }

        setUp();
    }

    // adds ship
    private void addShip() {
        int row = promptUserInt("Starting in which row?");
        int col = promptUserInt("Starting in which column?");
        int len = promptUserInt("How long?");

        if (b.addShip(row, col, len, promptUserChar())) {
            System.out.println("New ship added!");
            if (len == 3) {
                shipLen3 = true;
            } else if (len == 4) {
                shipLen4 = true;
            }
        } else {
            System.out.println("Unable to add ship");
        }
    }

    // helper method for addShip() & shoot(), gets int from user
    private int promptUserInt(String text) {
        int n;
        while (true) {
            System.out.println(text);
            try {
                n = Integer.parseInt(sc.nextLine());

                if (n > 0 && n < 10) {
                    return n;
                }
            } 
            catch (NumberFormatException e) {}
        }
    }

    // helper method for addShip(), gets char from user and converts it to boolean
    private boolean promptUserChar() {
        String input;
        while (true) {
            System.out.println("Horizontal (h) or vertical (v)?");
            input = sc.nextLine();

            if (input.length() == 1) {
                if (input.charAt(0) == 'h') {
                    return true;
                } else if (input.charAt(0) == 'v') {
                    return false;
                }
            }
        }
    }

    private void play() {
        if (b.gameOver()) {
            System.out.println("Game Over!");
            return;
        }

        System.out.println("Press \"s\" to shoot at a square, \"b\" to see the board, \"q\" to quit.");
        String input = sc.nextLine();

        // guides all possible actions
        if (input.equals("s")) {
            shoot();
        } else if (input.equals("b")) {
            System.out.println("\n" + b.toString());
        } else if (input.equals("q")) {
            return;
        }

        play();
    }

    private void shoot() {
        int row = promptUserInt("Input row");
        int col = promptUserInt("Input column");

        int n = b.shoot(row, col);

        if (n == 0) {
            System.out.println("Miss!");
        } else if (n == 1) {
            System.out.println("Hit!");
        } else if (n == 2) {
            System.out.println("You already tried that");
        }
    }
}