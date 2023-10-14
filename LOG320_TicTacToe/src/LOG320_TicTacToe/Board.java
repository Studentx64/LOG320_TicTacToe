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
    	//System.out.println("Plateau créé :");
    	//printBoard();
    }
    
	public void printBoard(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append("|");
			for (int j = 0; j < 3; j++) {
				if (this.board[i][j] == Mark.EMPTY) {
					sb.append("-");
				}
				if (board[i][j] == Mark.X) {
					sb.append("X");
				}
				if (board[i][j] == Mark.O) {
					sb.append("O");
				}
				sb.append("|");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}


    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
    	this.board[m.getRow()][m.getCol()] = mark;
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
				return 100;  // Winning row
			}
			if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
				return 100;  // Winning column
			}
		}
		if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
			return 100;  // Winning diagonal
		}
		if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
			return 100;  // Winning reverse diagonal
		}
	
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == Mark.EMPTY) {
					return 0;  // Game still ongoing
				}
			}
		}
		return -100;  // Loss (or draw, can be adjusted)
    }

	public ArrayList<Move> generatePossibleMoves() {
		ArrayList<Move> possibleMoves = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == Mark.EMPTY) {
					possibleMoves.add(new Move(i, j));
				}
			}
		}
		return possibleMoves;
	}

}
