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
 * represents the movements and abilities of the bishop piece
 */

public class Bishop extends Piece {

	public Bishop(final Type typeIn, final int positionIn, boolean isWhiteIn) {
		super(typeIn, positionIn, isWhiteIn);
	}

	/*
	 * returns a list with all of the moves that the bishop may make
	 * from its current position
	 */
	@Override
	public List<Moves> ValidMoves(Board board) {
		// where the bishop currently is
		int currPosition = this.position;
		// from its spot a bishop can move to these respective positions
		int[] validMovements = { -7, 7, -9, 9 };
		// list where the moves will be stored
		ArrayList<Moves> validMoves = new ArrayList<Moves>();
		for (int i = 0; i < validMovements.length; i++) {
			// traverses through the list of positions the 
			// bishop could move to
			while (isValid(currPosition + validMovements[i])) {
				// checks if the positions are positions on the board
				// identifies the square at the specific position
				Square thereSquare = board
						.getSquare(currPosition + validMovements[i]);
				// if there is a piece at the posiiton the piece may move to
				if (thereSquare.getPiece() != null) {
					if (thereSquare.getPiece().isWhite != this.isWhite) {
						// a kill move is created
						validMoves.add(new Moves.KillMove(this,
								currPosition + validMovements[i], board,
								thereSquare.getPiece()));
						// once an obstacle is met, no more moves in this
						// sequence of the specific validMovements is created
						break;
					}else {
						break;
					}
					// if not, a normal move is added
				} else {
					validMoves.add(new Moves.NormalMove(this,
							currPosition + validMovements[i], board));
					currPosition += validMovements[i];
				}
			}
		}
		// list of moves that may be made are added to this list
		return validMoves;
	}

	// if a piece is being changed to a bishop this method returns the new piece
	// after the change occurs
	@Override
	public Piece changePiece(Moves changingPiece, boolean isWhite) {
		return new Bishop(Type.BISHOP, changingPiece.there, isWhite);
	}
}
