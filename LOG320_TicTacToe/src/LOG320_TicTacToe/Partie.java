package LOG320_TicTacToe;

import java.util.ArrayList;

public class Partie {

    public static void main(String[] args) {
        // Creating a Tic Tac Toe board
        Board board = new Board();

        // Creating a CPU player with 'X' as its mark
        CPUPlayer cpu = new CPUPlayer(Mark.X);

        // Playing a move on the board
        board.play(new Move(0, 0), Mark.X);

        // Evaluating the board for player 'X'
        int score = board.evaluate(Mark.X);
        System.out.println("Score after first move: " + score);

        // Generating possible moves
        ArrayList<Move> moves = board.generatePossibleMoves();
        System.out.println("Possible Moves: ");
        for (Move m : moves) {
            System.out.println("Row: " + (m.getRow() + 1) + ", Col: " + (m.getCol() + 1));
        }

        // Getting the CPU's next move using MinMax
        ArrayList<Move> bestMoves = cpu.getNextMoveMinMax(board);
        System.out.println("CPU's Next Best Moves using MinMax: ");
        for (Move m : bestMoves) {
            System.out.println("Row: " + (m.getRow() + 1) + ", Col: " + (m.getCol() + 1));
        }

        // Getting the CPU's next move using Alpha-Beta Pruning
        bestMoves = cpu.getNextMoveAB(board);
        System.out.println("CPU's Next Best Moves using Alpha-Beta Pruning: ");
        for (Move m : bestMoves) {
            System.out.println("Row: " + (m.getRow() + 1) + ", Col: " + (m.getCol() + 1));
        }

        // Number of explored nodes
        System.out.println("Number of explored nodes: " + cpu.getNumOfExploredNodes());
    }
}
