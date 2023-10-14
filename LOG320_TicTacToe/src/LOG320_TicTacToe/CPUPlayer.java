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
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = Integer.MIN_VALUE;

        for (Move move : board.generatePossibleMoves()) {
            board.play(move, cpuMark);
            int boardValue = minMax(board, Mark.O);  // Assume O goes next
            board.play(move, Mark.EMPTY);  // undo move

            if (boardValue > bestValue) {
                bestValue = boardValue;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (boardValue == bestValue) {
                bestMoves.add(move);
        }
    }
        return bestMoves;

    }

     private int minMax(Board board, Mark currentPlayer) {
        numExploredNodes++;
    
        int score = board.evaluate(cpuMark);
    
        if (score == 100) return score;
        if (score == -100) return score;
    
        ArrayList<Move> possibleMoves = board.generatePossibleMoves();
        if (possibleMoves.isEmpty()) return 0;
    
        if (currentPlayer == cpuMark) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentPlayer);
                maxEval = Math.max(maxEval, minMax(board, Mark.O));
                board.play(move, Mark.EMPTY);  // undo move
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentPlayer);
                minEval = Math.min(minEval, minMax(board, cpuMark));
                board.play(move, Mark.EMPTY);  // undo move
            }
            return minEval;
        }
    }
    

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestValue = Integer.MIN_VALUE;

        for (Move move : board.generatePossibleMoves()) {
            board.play(move, cpuMark);
            int boardValue = alphaBeta(board, Mark.O, Integer.MIN_VALUE, Integer.MAX_VALUE);  // Assume O goes next
            board.play(move, Mark.EMPTY);  // undo move

            if (boardValue > bestValue) {
                bestValue = boardValue;
                bestMoves.clear();
                bestMoves.add(move);
        } else if (boardValue == bestValue) {
            bestMoves.add(move);
        }
    }
    return bestMoves;
       

    }

   

    private int alphaBeta(Board board, Mark currentPlayer, int alpha, int beta) {
        numExploredNodes++;

        int boardVal = board.evaluate(cpuMark);

        if (boardVal == 100) return boardVal;
        if (boardVal == -100) return boardVal;

        ArrayList<Move> possibleMoves = board.generatePossibleMoves();
        if (possibleMoves.isEmpty()) return 0;

        if (currentPlayer == cpuMark) {
            for (Move move : possibleMoves) {
                board.play(move, currentPlayer);
                alpha = Math.max(alpha, alphaBeta(board, Mark.O, alpha, beta));
                board.play(move, Mark.EMPTY);  // undo move
                if (beta <= alpha) break;  // beta cut-off
            }
            return alpha;
        } else {
            for (Move move : possibleMoves) {
                board.play(move, currentPlayer);
                beta = Math.min(beta, alphaBeta(board, cpuMark, alpha, beta));
                board.play(move, Mark.EMPTY);  // undo move
                if (beta <= alpha) break;  // alpha cut-off
            }
            return beta;
        }
    }




}



