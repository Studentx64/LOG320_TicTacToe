package LOG320_TicTacToe;

import java.util.*;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
    	this.board = new Mark[3][3];
    	for(int i=0; i<3; i+=1) {
    		for(int j=0; j<3; j+=1) {
    			this.board[i][j]=Mark.EMPTY;
    		}
    	}
    	
    }
    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
    	if (m != null && isCellEmpty(m)) {
            board[m.getRow()][m.getCol()] = mark;
        } else {
            // Handle invalid move (Optional)
            System.out.println("Invalid move");
        }
    }
    


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
		for (int i = 0; i < 3; i++) {
			// Check rows and columns
            if ((board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) ||
                (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)) {
                return 100;  // Winning
            }
        }

        // Check diagonals
        if ((board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
            (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)) {
            return 100;  // Winning
        }

        // Check if the opponent has won
        Mark opponent = (mark == Mark.X) ? Mark.O : Mark.X;
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i][0] == opponent && board[i][1] == opponent && board[i][2] == opponent) ||
                (board[0][i] == opponent && board[1][i] == opponent && board[2][i] == opponent)) {
                return -100;  // Losing
            }
        }

        // Check diagonals for opponent
        if ((board[0][0] == opponent && board[1][1] == opponent && board[2][2] == opponent) ||
            (board[0][2] == opponent && board[1][1] == opponent && board[2][0] == opponent)) {
            return -100;  // Losing
        }

        // Check if it's a draw
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }

        if (isDraw) {
            return 0; // Draw
        }

        // Otherwise, the game is ongoing.
        return Integer.MIN_VALUE;  // Not terminal state
    }

    // Additional utility method to check if a cell is empty
    public boolean isCellEmpty(Move m) {
        return board[m.getRow()][m.getCol()] == Mark.EMPTY;
    }

	public Mark[][] getBoard() {
		return board;
	}
	

}
