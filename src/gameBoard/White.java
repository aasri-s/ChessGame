package gameBoard;

import java.util.ArrayList;

import Pieces.King;
import Pieces.Piece;


/* This class represents the Person paying with the white Pieces
 */
public class White extends GrandMaster{
	

	public White(ArrayList<Moves> wMoves,
			ArrayList<Moves> bMoves, Board board) {
		super(wMoves, bMoves, board);
		/*
		 * the King Piece is assigned here
		 */
		for(Piece p: board.getWhitePieces()) {
			if(p.type == Type.KING) {
				myKing = (King) p;
			}
		}
		isWhite = true;
		/*
		 * the other player in this game would be represented by the Black
		 * class. 
		 */
		other = board.getBlack();
		// represents whether the grandmaster using the white pieces is in
		// a checkmate position
		checked = !GrandMaster
				.hitsAgainstSquare(this.myKing.position, otherMoves).isEmpty();
	}

}
