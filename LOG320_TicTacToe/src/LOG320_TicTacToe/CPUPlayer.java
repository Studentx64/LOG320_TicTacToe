package LOG320_TicTacToe;

import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

	private Mark cpuMark;
	
    // Contient le nombre de noeuds visités (le nombre
    // d'appels à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.

    private int numExploredNodes;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
    	this.cpuMark = cpu;
        this.numExploredNodes=0;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

        
    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        
        numExploredNodes = 0;
        return minMax(board, cpuMark, 0);

    }


    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        return alphaBeta(board, cpuMark, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

    }

    private ArrayList<Move> minMax(Board board, Mark currentPlayer, int depth) {
        numExploredNodes++;

        // Add basic validation to ensure board state and current player are valid
        if (board == null || currentPlayer == null) {
            throw new IllegalArgumentException("Board and currentPlayer cannot be null");
        }

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue;

        if (currentPlayer == cpuMark) {
            bestValue = Integer.MIN_VALUE;
        } else {
            bestValue = Integer.MAX_VALUE;
        }

        int boardValue = board.evaluate(cpuMark);

        if (boardValue == 100 || boardValue == -100 || boardValue == 0) {
            Move terminalMove = new Move();
            terminalMove.setRow(boardValue);
            bestMoves.add(terminalMove);
            return bestMoves;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move move = new Move(i, j);
                if (board.isCellEmpty(move)) {
                    board.play(move, currentPlayer);
                    ArrayList<Move> currentMove = new ArrayList<>();
                    currentMove.add(move);

                    int currentValue;
                    if (currentPlayer == cpuMark) {
                        currentValue = minMax(board, Mark.O, depth + 1).get(0).getRow();
                        if (currentValue > bestValue) {
                            bestValue = currentValue;
                            bestMoves = currentMove;
                        }
                    } else {
                        currentValue = minMax(board, Mark.X, depth + 1).get(0).getRow();
                        if (currentValue < bestValue) {
                            bestValue = currentValue;
                            bestMoves = currentMove;
                        }
                    }
                    board.play(move, Mark.EMPTY);
                }
            }
        }
        bestMoves.get(0).setRow(bestValue);
        return bestMoves;
    }

    private ArrayList<Move> alphaBeta(Board board, Mark currentPlayer, int alpha, int beta, int depth) {
        numExploredNodes++;

        // Add basic validation to ensure board state and current player are valid
        if (board == null || currentPlayer == null) {
            throw new IllegalArgumentException("Board and currentPlayer cannot be null");
        }

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue;

        if (currentPlayer == cpuMark) {
            bestValue = Integer.MIN_VALUE;
        } else {
            bestValue = Integer.MAX_VALUE;
        }

        int boardValue = board.evaluate(cpuMark);

        if (boardValue == 100 || boardValue == -100 || boardValue == 0) {
            Move terminalMove = new Move();
            terminalMove.setRow(boardValue);
            bestMoves.add(terminalMove);
            return bestMoves;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move move = new Move(i, j);
                if (board.isCellEmpty(move)) {
                    board.play(move, currentPlayer);
                    ArrayList<Move> currentMove = new ArrayList<>();
                    currentMove.add(move);

                    int currentValue;
                    if (currentPlayer == cpuMark) {
                        currentValue = alphaBeta(board, Mark.O, alpha, beta, depth + 1).get(0).getRow();
                        if (currentValue > bestValue) {
                            bestValue = currentValue;
                            bestMoves = currentMove;
                        }
                        if (bestValue > alpha) {
                            alpha = bestValue;
                        }
                    } else {
                        currentValue = alphaBeta(board, Mark.X, alpha, beta, depth + 1).get(0).getRow();
                        if (currentValue < bestValue) {
                            bestValue = currentValue;
                            bestMoves = currentMove;
                        }
                        if (bestValue < beta) {
                            beta = bestValue;
                        }
                    }
                    board.play(move, Mark.EMPTY);

                    if (alpha >= beta) {
                        bestMoves.get(0).setRow(bestValue);
                        return bestMoves;
                    }
                }
            }
        }
        bestMoves.get(0).setRow(bestValue);
        return bestMoves;

   }




}



