public class GameBoard {
    private final String[][] board = {
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}
    };

    private final String playerX, playerO; // player names
    private int scorePlayerX, scorePlayerO; // tracks player scores
    private boolean turnPlayerX = true; // flag to track player move ==> X makes first move

    // default constructor: initializes player names and displays playable positions
    public GameBoard(String player1, String player2) {
        playerX = player1;
        playerO = player2;
        printRules();
    }

    public void printRules() {
        String[][] boardRules = { // board displays playable position => integers that the player can enter
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
        System.out.println("\nPlayable positions: ");
        printBoard(boardRules);
        System.out.println();
    }

    public boolean isValidMove(int move) {
        switch (move) {
            case 1 -> {
                return board[0][0].equals("-"); // "-" indicates empty position on game board => playable position
            }
            case 2 -> {
                return board[0][1].equals("-");
            }
            case 3 -> {
                return board[0][2].equals("-");
            }
            case 4 -> {
                return board[1][0].equals("-");
            }
            case 5 -> {
                return board[1][1].equals("-");
            }
            case 6 -> {
                return board[1][2].equals("-");
            }
            case 7 -> {
                return board[2][0].equals("-");
            }
            case 8 -> {
                return board[2][1].equals("-");
            }
            case 9 -> {
                return board[2][2].equals("-");
            }
            default -> { // Default: false if outside of boundary OR position occupied
                return false;
            }
        }
    }

    // updates board with players move
    public void placeGamePiece(int move) {
        String playerPiece = "X";
        if (!turnPlayerX) {
            playerPiece = "O";
        }
        switch (move) {
            case 1 -> board[0][0] = playerPiece; // updating game board based on specified case
            case 2 -> board[0][1] = playerPiece;
            case 3 -> board[0][2] = playerPiece;
            case 4 -> board[1][0] = playerPiece;
            case 5 -> board[1][1] = playerPiece;
            case 6 -> board[1][2] = playerPiece;
            case 7 -> board[2][0] = playerPiece;
            case 8 -> board[2][1] = playerPiece;
            case 9 -> board[2][2] = playerPiece;
        }
        // changing player
        turnPlayerX = !turnPlayerX;
    }

    // returns "X" or "O" for winning piece, "TIE GAME" for tied, or space
    private static String gameResult(String[][] board) {
        // Case: check rows
        for(int i = 0; i < 3; i++) {
            if( board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) ) {
                return board[i][0];
            }
        }
        // Case: check columns
        for(int j = 0; j < 3; j++) {
            if( board[0][j].equals(board[1][j]) && board[1][j].equals(board[2][j]) ) {
                return board[0][j];
            }
        }
        // Case: check diagonals
        if( board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) ) {
            return board[0][0];
        }
        if( board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]) ) {
            return board[2][0];
        }
        // Case: check Tie
        if( !board[0][0].equals("-") && !board[0][1].equals("-") && !board[0][2].equals("-")
                && !board[1][0].equals("-") && !board[1][1].equals("-") && !board[1][2].equals("-")
                && !board[2][0].equals("-") && !board[2][1].equals("-") && !board[2][2].equals("-") ) {
            return "TIE GAME";
        }
        // Default Case: no winner
        return " ";
    }

    public boolean gameFinished() {
        String result = gameResult(board);
        switch (result) {
            case "X" -> {
                System.out.println(playerX + " is winner!");
                scorePlayerX++;
                return true;
            }
            case "O" -> {
                System.out.println(playerO + " is winner!");
                scorePlayerO++;
                return true;
            }
            case "TIE GAME" -> {
                System.out.println("TIE GAME!");
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    // resets all playable positions back to empty
    public void resetGame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = "-";
            }
        }
    }

    public void printScore() {
        System.out.println(playerX + "(" + scorePlayerX + ") - " +
                playerO + "(" + scorePlayerO + ")");
    }

    public void printBoard() { // used to access private printBoard method
        printBoard(board);
    }

    // displays game board
    private void printBoard(String[][] board) {
        System.out.println("=============");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |\n");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |\n");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("=============");
    }
} // end GameBoard