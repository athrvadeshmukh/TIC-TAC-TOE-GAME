import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;
    private static char currentPlayer = 'X';
    private static int playerXScore = 0;
    private static int playerOScore = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the board (e.g., 3 for a 3x3 board): ");
        int boardSize = scan.nextInt();
        board = new char[boardSize][boardSize];

        boolean playAgain = true;
        while (playAgain) {
            initializeBoard();
            displayBoard();

            boolean gameWon = false;
            boolean isDraw = false;
            while (!gameWon && !isDraw) {
                makeMove();
                displayBoard();
                gameWon = checkWin();
                isDraw = checkDraw();
                if (!gameWon && !isDraw) {
                    switchPlayer();
                }
            }

            if (gameWon) {
                displayWinner();
            } else {
                System.out.println("The game is a draw!");
            }

            displayScore();
            System.out.print("Do you want to play again? (y/n): ");
            String playAgainChoice = scan.next();
            playAgain = playAgainChoice.equalsIgnoreCase("y");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < board[0].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("-".repeat(board[0].length * 4 - 1));
            }
        }
        System.out.println();
    }

    private static void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter row (1-" + board.length + "): ");
            row = scan.nextInt() - 1; // Adjusting for 0-based index
            System.out.print("Player " + currentPlayer + ", enter column (1-" + board[0].length + "): ");
            col = scan.nextInt() - 1; // Adjusting for 0-based index
        } while (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != '-');

        board[row][col] = currentPlayer;
    }

    private static boolean checkWin() {
        for (int i = 0; i < board.length; i++) {
            if (checkRowWin(i) || checkColumnWin(i)) {
                return true;
            }
        }
        return checkDiagonalWin() || checkAntiDiagonalWin();
    }

    private static boolean checkRowWin(int row) {
        for (int i = 1; i < board[0].length; i++) {
            if (board[row][i] != board[row][0] || board[row][i] == '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumnWin(int col) {
        for (int i = 1; i < board.length; i++) {
            if (board[i][col] != board[0][col] || board[i][col] == '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonalWin() {
        for (int i = 1; i < board.length; i++) {
            if (board[i][i] != board[0][0] || board[i][i] == '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean checkAntiDiagonalWin() {
        for (int i = 1; i < board.length; i++) {
            if (board[i][board.length - 1 - i] != board[0][board.length - 1] || board[i][board.length - 1 - i] == '-') {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayWinner() {
        System.out.println("Player " + currentPlayer + " wins!");
        if (currentPlayer == 'X') {
            playerXScore++;
        } else {
            playerOScore++;
        }
    }

    private static void displayScore() {
        System.out.println("Player X Score: " + playerXScore);
        System.out.println("Player O Score: " + playerOScore);
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
