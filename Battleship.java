import java.util.Scanner;
public class Battleship {
    private Scanner sc = new Scanner(System.in);
    private Board b = new Board();

    private boolean shipLen3 = false;
    private boolean shipLen4 = false;
    
    public Battleship() {
        setUp(); // setUp necessary because cnostructors can't be recursive
    }

    private void setUp() {
        System.out.println("Type \"a\" to add a new ship, \"b\" to see the board, or \"e\" to end building board.");
        String input = sc.nextLine();

        // guides all possible actions
        if (input.equals("a")) {
            addShip();
        } else if (input.equals("b")) {
            System.out.println("\n" + b.toString());
        } else if (input.equals("e")) {
            if (shipLen3 && shipLen4) {
                return;
            } else {
                System.out.println("You need ships of length 3 and 4 to play!");
            }
        }

        setUp();
    }

    // adds ship
    private void addShip() {
        int row = promptUserInt("Starting in which row?", 0, 9);
        int col = promptUserInt("Starting in which column?", 0, 9);
        int len = promptUserInt("How long?", 0, 10);
        boolean orientation = promptUserChar();
        System.out.println("Name of ship?");
        String name = sc.nextLine();

        if (b.addShip(row, col, len, orientation, name)) {
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

    // helper method for addShip() & shoot(), gets int from user where l <= int <= u
    private int promptUserInt(String text, int l, int u) {
        int n;
        while (true) {
            System.out.println(text);
            try {
                n = Integer.parseInt(sc.nextLine());

                if (n >= l && n <= u) {
                    return n;
                }
            } 
            catch (NumberFormatException e) {}
        }
    }

    // helper method for addShip(), gets orientation from user and converts it to boolean
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

    public boolean play(Board o, Board p) { // o is opponent's board && p is recorded opponent's board
        System.out.println("Press \"s\" to shoot at a square, \"b\" to see your board, or \"o\" to see your guesses for opponent's board");
        String input = sc.nextLine();

        // guides all possible actions
        if (input.equals("s")) {
            return shoot(o, p);
        } else if (input.equals("b")) {
            System.out.println("\n" + b.toString());
        } else if (input.equals("o")) {
            System.out.println("\n" + p.toString());
        }
        return play(o, p);
    }

    private boolean shoot(Board o, Board p) {
        int row = promptUserInt("Input row", 0, 9);
        int col = promptUserInt("Input column", 0, 9);

        int n = o.shoot(row, col);

        if (n == 0) {
            System.out.println("\nMiss!");
            o.setValue(row, col, "m");
            p.setValue(row, col, "m");
        } else if (n == 1) {
            System.out.println("\nHit!");
            o.setValue(row, col, "x");
            p.setValue(row, col, "x");
        } else if (n == 2) {
            System.out.println("\nYou already tried that");
            return false;
        }
        System.out.println("\n" + p.toString());
        return true;
    }

    public Board getBoard() {
        return b;
    }
}