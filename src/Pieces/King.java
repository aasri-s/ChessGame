package Pieces;

import java.util.ArrayList;
import java.util.List;

import gameBoard.Board;
import gameBoard.Moves;
import gameBoard.Type;
import gameBoard.Moves.KillMove;
import gameBoard.Moves.NormalMove;
/*
 * represents the movements and abilities of the king piece
 */
public class King extends Piece{
	public King(final Type typeIn, final int positionIn, boolean isWhiteIn) {
		super(typeIn, positionIn, isWhiteIn);
	}
	
	/*
	 * returns a list with all of the moves that the king may make
	 * from its current position
	 */
	@Override
	public List<Moves> ValidMoves(Board board) {
		// where the king currently is
		int currPosition = this.position;
		// from its spot a king can move to these respective positions
		int[] validMovements = {-1, 1, -8, 8, -7, 7, -9, 9};
		// list where the moves will be stored
		ArrayList<Moves> validMoves = new ArrayList<Moves>();
		int moveTo;
		// traverses through the list of positions the 
		// king could move to
		for (int i = 0; i < validMovements.length; i++) {
			moveTo = this.position + validMovements[i];
			if (isValid(moveTo)) {
				// checks if the positions are positions on the board
				// identifies the square at the specific position
				if(board.getSquare(moveTo).empty) {
					//if the square is empty, a normal move is added to the list of valid moves
					validMoves.add(new Moves.NormalMove(this, moveTo, board));
				} else {
					// if there is a piece at the posiiton the piece may move to
					// a kill move is created
					validMoves.add(new Moves.KillMove(this, moveTo, board, board.getSquare(moveTo).getPiece()));
				}
			}
		}
		// list of moves that may be made are added to this list and returned
		return validMoves;
	}

	// if a piece is being changed to a king this method returns the new piece
		// after the change occurs
	@Override
	public Piece changePiece(Moves changingPiece, boolean isWhite) {
		return new King(Type.KING, changingPiece.there, isWhite);
	}
	
}
