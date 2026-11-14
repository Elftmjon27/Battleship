import java.util.Scanner;
public class GameBoard {
    private Battleship[] bPersonal = new Battleship[2];
    private Board[] bOpponent = new Board[2];
    Scanner sc = new Scanner(System.in);

    public GameBoard() {
        System.out.println("Welcome to Battleship!");
        System.out.println("Player 1, create your board");
        bPersonal[0] = new Battleship();
        bOpponent[0] = new Board();
        clearScreen();
        System.out.println("Welcome to Battleship!");
        System.out.println("Player 2, create your board");
        bPersonal[1] = new Battleship();
        bOpponent[1] = new Board();
        clearScreen();
        playGame(0);
    }

    // clears the terminal window, looked this up
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // given the player number (p), goes through players' turns
    private void playGame(int p) {
        System.out.println("Player " + (p + 1) + ", what do you want to do?");
        Board o = bPersonal[(p + 1) % 2].getBoard();
        boolean play = bPersonal[p].play(o, bOpponent[p]);

        while (!play) {
            play = bPersonal[p].play(o, bOpponent[p]);
        }

        if (o.gameOver()) {
            System.out.println("Player " + (p + 1) + " has won");
        } else {
            System.out.print("Press any key to end turn ");
            String temp = sc.nextLine(); // allows player to choose when end turn
            clearScreen();
            playGame((p + 1) % 2);
        }
    }
}