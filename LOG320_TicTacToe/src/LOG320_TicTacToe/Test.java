package LOG320_TicTacToe;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // Initializing a new board
        Board board = new Board();

        // Display the initial state of the board
        System.out.println("Initial Board State:");
        printBoard(board);

        // Initialize a CPU player with mark X
        CPUPlayer cpu = new CPUPlayer(Mark.X);

        // Example move to place an X at position (1,1)
        Move move = new Move(1, 1);
        board.play(move, Mark.X);

        // Display board after move
        System.out.println("After Move (1,1) with X:");
        printBoard(board);

        // Evaluate the board
        int score = board.evaluate(Mark.X);
        System.out.println("Board Score: " + score);

        // Example CPU player decision-making
        ArrayList<Move> nextMoves = cpu.getNextMoveMinMax(board);

        // Display possible next moves
        System.out.println("Possible Next Moves:");
        for (Move m : nextMoves) {
            System.out.println("(" + m.getRow() + ", " + m.getCol() + ")");
        }
    }

    // Print the current state of the board
    public static void printBoard(Board board) {
        // Note: Implementation of getBoard might be needed in Board class
        Mark[][] currentState = board.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(currentState[i][j] + " ");
            }
            System.out.println();
        }
    }
}
