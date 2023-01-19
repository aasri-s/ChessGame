package gameBoard;

import java.util.ArrayList;

import Pieces.Piece;

/* This class represented the idea of a single square on a chess board. There
 * are two types of Square objects: EmptySquares and NonEmptySquares.
 * 
 *  An Empty Square is one that does not have a Piece on it and a NonEmptySquare
 *  is a Square without a Piece on it. 
 *  */
public abstract class Square {
	/*  *  Each square has a squareNo (square number) and an attribute that indicates
 *  if the square is empty or not*/
	public boolean empty;
	protected int squareNo;
	private static EmptySquare[] setEmptySquares() {
		EmptySquare[] emptySquares = new EmptySquare[64];
		for(int i = 0; i < 64; i++) {
			emptySquares[i] = new EmptySquare(i);
		}
		return emptySquares;
	};
	
	public Square(int squareNoIn) {
		squareNo = squareNoIn;
	}
	
	// returns the Piece that is on the square is it is Nonempty and returns
	// null if the Square is Empty
	public abstract Piece getPiece();

	
	public static Square makeSquare(int tileNo, Piece pieceIn) {
		if(pieceIn == null) {
			return setEmptySquares()[tileNo];
		} else {
			return new NonEmptySquare(tileNo, pieceIn);
		}
	}
	
	
}
