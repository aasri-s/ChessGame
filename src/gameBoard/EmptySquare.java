package gameBoard;

import Pieces.Piece;

/* The EmptySquare class represents a Square on the chessboard that 
 * does not have a Piece on it
 */
public class EmptySquare extends Square{
	// the constructor from the Square class is used to initialize the 
			// squareNo and an Empty square is empty so the empty variable
			// is set to true by default
			public EmptySquare(int squareNoIn) {
				super(squareNoIn);
				empty = true;
			}

			// Square is not occupied so null is returned
			@Override
			public Piece getPiece() {
				return null;
			}


}
