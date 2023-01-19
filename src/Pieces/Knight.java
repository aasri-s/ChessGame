package Pieces;

import java.util.ArrayList;

import java.util.List;

import gameBoard.Board;
import gameBoard.Moves;
import gameBoard.Square;
import gameBoard.Type;
import gameBoard.Moves.KillMove;
import gameBoard.Moves.NormalMove;

/*
 * represents the movements and abilities of the knight piece
 */
public class Knight extends Piece{

	public Knight(final Type typeIn, final int positionIn, boolean isWhiteIn) {
		super(typeIn, positionIn, isWhiteIn);
	}
	
	/*
	 * returns a list with all of the moves that the knight may make
	 * from its current position
	 */
	@Override
	public List<Moves> ValidMoves(Board board) {
		int[] validMovements = {-17, -15, -10, -6, 6, 10, 15, 17};
		ArrayList<Moves> validMoves = new ArrayList<Moves>();
		for( int i = 0; i < validMovements.length; i++) {
			if (isValid(this.position + validMovements[i])) {
				Square thereSquare = board.getSquare(this.position + validMovements[i]);
				if (thereSquare.empty == true) {
					validMoves.add(new Moves.NormalMove(this, this.position + validMovements[i], board));
				} else {
					Piece therePiece = thereSquare.getPiece();
					if(this.isWhite != therePiece.isWhite) {
						validMoves.add(new Moves.KillMove(this, this.position+validMovements[i], board, therePiece));
					}
				}
				
			}
		}
		return validMoves;
	}

	// if a piece is being changed to a knight this method returns the new piece
		// after the change occurs
	@Override
	public Piece changePiece(Moves changingPiece, boolean isWhite) {
		return new Knight(Type.KNIGHT, changingPiece.there, isWhite);
	}
}
