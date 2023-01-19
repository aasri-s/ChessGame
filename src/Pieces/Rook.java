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
 * represents the movements and abilities of the rook piece
 */
public class Rook extends Piece{
	public Rook(final Type typeIn, final int positionIn, boolean isWhiteIn) {
		super(typeIn, positionIn, isWhiteIn);
	}
	
	/*
	 * returns a list with all of the moves that the rook may make
	 * from its current position
	 */
	@Override
	public List<Moves> ValidMoves(Board board) {
		int currPosition = this.position;
		int[] validMovements = {-1,1,-8,8};
		ArrayList<Moves> validMoves = new ArrayList<Moves>();
		for( int i = 0; i < validMovements.length; i++) {
			while(isValid(currPosition+validMovements[i])) {
					Square thereSquare = board.getSquare(currPosition + validMovements[i]);
					if(thereSquare.getPiece()!=null) {
						if(thereSquare.getPiece().isWhite!=this.isWhite) {
							validMoves.add(new Moves.KillMove(this ,currPosition + validMovements[i],
									board,thereSquare.getPiece() ));
							break;
						} else {
							break;
						}
					} else {
						validMoves.add(new Moves.NormalMove(this, currPosition + validMovements[i], board));
						currPosition+=validMovements[i];
					}
				}
		}
		return validMoves;
	
	}


	// if a piece is being changed to a rook this method returns the new piece
	// after the change occurs
	@Override
	public Piece changePiece(Moves changingPiece, boolean isWhite) {
		return new Rook(Type.ROOK, changingPiece.there, isWhite);
	}
}
