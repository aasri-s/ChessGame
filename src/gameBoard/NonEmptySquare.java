package gameBoard;

import Pieces.Piece;

/* the NonEmptySquare is a Square that has a Piece on it. A NonEmptySquare
* has an extra variable from the other Square classes as it has a 
* variable that holds the Piece that is currently occuping that Square.
*/
public class NonEmptySquare extends Square{
	Piece currPiece;

	/* Also utilizes the constructor from the Square class to set the 
	 * squareNo and since a NonEmptySquare is not empty, the empty 
	 * atrribute is set to false by default. Also, notice that 
	 * this constructor includes a Piece variable that will set the value 
	 * that is holding the currPiece value.
	 */
	public NonEmptySquare(int squareNoIn, Piece currPieceIn) {
		super(squareNoIn);
		empty = false;
		currPiece = currPieceIn;
	}

	// returns the Piece currentlu occupying the Square.
	@Override
	public Piece getPiece() {
		return currPiece;
	}


}
