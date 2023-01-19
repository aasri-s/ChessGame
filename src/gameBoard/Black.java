package gameBoard;

import java.util.ArrayList;
import Pieces.Piece;

import Pieces.King;
import Pieces.Piece;

/* This class represents the Person paying with the Black Pieces
 */
public class Black extends GrandMaster{	
	public Black(ArrayList<Moves> wMoves,
			ArrayList<Moves> bMoves, Board board) {
		super(bMoves, wMoves, board);
		
		/*
		 * the King Piece is assigned here
		 */
		for(Piece p: board.getBlackPieces()) {
			if(p.type == Type.KING) {
				myKing = (King) p;
			}
		}
		/*
		 * the other player in this game would be represented by the White
		 * class. 
		 */
		other = board.getWhite();
		isWhite = false;
		// represents whether the grandmaster using the white pieces is in
		// a checkmate position
		checked = !GrandMaster
		.hitsAgainstSquare(this.myKing.position, otherMoves).isEmpty();
	}
	
}
