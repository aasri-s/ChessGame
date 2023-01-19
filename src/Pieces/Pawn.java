package Pieces;

import java.util.ArrayList;
import java.util.List;

import gameBoard.Board;
import gameBoard.Moves;
import gameBoard.Type;
import gameBoard.Moves.KillMove;
import gameBoard.Moves.NormalMove;


/* Whats different about a pawn compared to other pieces?
 * 
 * pawns attack in different ways from their moves
 * the first move from a pawn can be two spots above and for the other
 * times it is only one way: how to do this: check if it is white or black
 * and then check if its in a specific row
 * 
 */
public class Pawn extends  Piece{

	public Pawn(final Type typeIn,final int positionIn, boolean isWhiteIn) {
		super(typeIn, positionIn, isWhiteIn);
	}
	
	@Override
	public List<Moves> ValidMoves(Board board) {
		ArrayList<Moves> validMoves = new ArrayList<Moves>();
		//first we check if this is its first move
		if(this.isWhite) {
			if(inSecondRow(this.position)) {
				//then this means it is its first move
				//check if the position right in front of it is empty
				if(board.getSquare(this.position+8).empty) {
					//if it is empty, add it to the validMoves list
					validMoves.add(new Moves.NormalMove(this, this.position+8, board));
				}
				//check if the position two positions ahead of it is empty
				if(board.getSquare(this.position+16).empty) {
					//if it is empty, add it to the validMoves list
					validMoves.add(new Moves.NormalMove(this, this.position+16, board));
				}
			}
		//if the pawn is black it should be in the seventh row
		} else {
			if(inSeventhRow(this.position)) {
				//then this means it is its first move
				//check if the position right in front of it is empty
				if(board.getSquare(this.position-8).empty) {
					//if it is empty, add it to the validMoves list
					validMoves.add(new Moves.NormalMove(this, this.position+8, board));
				}
				//check if the position two positions ahead of it is empty
				if(board.getSquare(this.position-16).empty) {
					//if it is empty, add it to the validMoves list
					validMoves.add(new Moves.NormalMove(this, this.position+16, board));
				}
			}
		}
		//then we check if there are any options in its diagonals. we 
		//also need to do this by if the pawn is black or white
		if(this.isWhite) {
			//if the pawn is white its positions are moving up and if the 
			// pawn is black its positions are moving down
			
			//also here we need to check if the positions are valid first
			
			if (isValid(this.position+7)) {
				if(board.getSquare(position+7).empty!=true) {
					validMoves.add(new Moves.KillMove(this, this.position + 7, board, 
							board.getSquare(this.position+7).getPiece()));
					
					if (isValid(this.position+9)) {
						if(board.getSquare(position+9).empty!=true) {
							validMoves.add(new Moves.KillMove(this, this.position + 9, board, 
									board.getSquare(this.position+9).getPiece()));
						}
					}
				}
				
			}
		} else {
			if (isValid(this.position-7)) {
				if(board.getSquare(position-7).empty!=true) {
					validMoves.add(new Moves.KillMove(this, this.position - 7, board, 
							board.getSquare(this.position-7).getPiece()));
					
					if (isValid(this.position-9)) {
						if(board.getSquare(position-9).empty!=true) {
							validMoves.add(new Moves.KillMove(this, this.position - 9, board, 
									board.getSquare(this.position-9).getPiece()));
						}
					}
				}
			}
		}
		return validMoves;
	}
	
	// returns if a position is in the second row or not
	protected boolean inSecondRow(int position) {
		if( position== 9 ||position== 10 ||position== 12 ||position== 13 
				|| position==14 ||position== 15||position ==16) {
			return true;
			
		} else {
			return false;
		}
	}
	// returns if a position is in the seventh row or not
	protected boolean inSeventhRow(int position) {
		if(position== 50 ||position== 51 ||position== 52 ||position== 53 
				|| position==54 ||position== 56) {
			return true;
			
		} else {
			return false;
		}
	}


	// if a piece is being changed to a pawn this method returns the new piece
		// after the change occurs
	@Override
	public Piece changePiece(Moves changingPiece, boolean isWhite) {
		return new Pawn(Type.PAWN, changingPiece.there, isWhite);
	}
}
