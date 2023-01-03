import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("TIC TAC TOE " +
                "\n---------------------------------------------------------------------");
        // prompt players to enter their names
        System.out.println("Player \"X\" enter your name: ");
        String playerX = input.nextLine().trim();
        System.out.println("Player \"O\" enter your name: ");
        String playerO = input.nextLine().trim();

        // creating board using specified player names
        GameBoard game = new GameBoard(playerX, playerO);
        boolean turnPlayerX = true; // first move by player with "X" pieces
        boolean continueCurrGame = true; // flag to stop game
        boolean playAgain = true; // flag for rematches

        // gameplay
        while (playAgain) {
            while (continueCurrGame) {
                // determining which player is moving
                String playerMove;
                if (turnPlayerX) {
                    playerMove = playerX;
                } else {
                    playerMove = playerO;
                }
                // prompt for specific player to enter a move
                System.out.println(playerMove + " enter playable position: ");

                try { // making move based on user input
                    int move = input.nextInt();
                    boolean result = game.isValidMove(move);
                    // player move validation
                    while (!result) {
                        System.out.println("Try Again! Enter playable position (unoccupied and within boundary). ");
                        move = input.nextInt();
                        result = game.isValidMove(move);
                    }
                    // updating board after successful player move
                    System.out.println("\nGame Board Updated: ");
                    game.placeGamePiece(move); // place piece
                    game.printBoard(); // print updated board
                }
                catch (Exception ex) {
                    throw new InputMismatchException("Invalid Position: Must be an integer.");
                }

                // check if game should continue
                if (game.gameFinished()) {
                    continueCurrGame = false; // terminate while-loop
                }
                turnPlayerX = !turnPlayerX; // change player after each successful move
            }

            System.out.println("Play again? Enter \"Yes\" or \"No\": ");
            String continueGame = input.next().trim().toUpperCase();

            if (continueGame.equals("NO")) {
                playAgain = false;
            } else if (continueGame.equals("YES")) {
                continueCurrGame = true;
                System.out.print("\nCurrent Score: ");
                game.printScore();
                System.out.println("Rematch! Game Board Reset.");
                game.resetGame();
                game.printRules();
            }
        }

        System.out.print("\nFinal Score: ");
        game.printScore();
        System.out.println("THANK YOU FOR PLAYING!" +
                "\n---------------------------------------------------------------------");
    }
} // end Program