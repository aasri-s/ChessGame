package gameBoard;

import Pieces.Piece;

/*
 * this class represents the different types of moves that can be made.
 * a move holds the piece being moves, where it is being moved to, 
 * and the board the move is occurring on
 */

public abstract class Moves {

	// represents the peice being moved
	final Piece currPiece;
	// represents the position of where the piece is being moved to
	public final int there;
	// represents the board the move is taking place on
	Board board;
	
	// constructor
	public Moves(Piece currPieceIn, int thereIn, Board boardIn) {
		currPiece = currPieceIn;
		there = thereIn;
		board = boardIn;
	}
	
	/*
	 * this method allows the move to take place
	 */
	public Board completeMove() {
		// represents the board after the move takes place
		Board newBoard = new Board();
		// if a white piece is being moved
		if(newBoard.whosPlaying()) {
			//all pieces other than the piece being moved of the white pieces
			// are being added to the new pieces
			for(Piece piece: newBoard.getWhitePieces()) {
				if(this.currPiece.equals(piece) == false) {
					newBoard.setPiece(piece);
				}
			}
			// all of the black pieces are being added to the new board
			// in the spots they were already at
			for(Piece piece: newBoard.getBlackPieces()) {
				newBoard.setPiece(piece);
			}
			// the piece being moved is moved to its new spot
			newBoard.setPiece(currPiece.changePiece(this, newBoard.whosPlaying()));
			// the next move should be of a black piece
			newBoard.setWhosNext(false);
		// if a black peice is being moved
		} else {
			// all black pieces other than the one being moved
			// is added to the new board
			for(Piece piece: newBoard.getBlackPieces()) {
				if(this.currPiece.equals(piece) == false) {
					newBoard.setPiece(piece);
				}
			}
			// all white pieces are added to the new board
			for(Piece piece: newBoard.getWhitePieces()) {
				newBoard.setPiece(piece);
			}
			// the black piece being moved is set to its new position
			newBoard.setPiece(currPiece.changePiece(this, newBoard.whosPlaying()));
			// the next piece moved should be a white piece
			newBoard.setWhosNext(true);
		}
		// the new board with the updated peice moves is returned
		return newBoard;
	}
	
	/*
	 * this class represents a normal move where no overtaking of an
	 * opponents piece is taking place, so the position the current piece
	 * is trying to move is empty
	 */
	public static class NormalMove extends Moves{

		public NormalMove(Piece currPieceIn, int thereIn, Board boardIn) {
			super(currPieceIn, thereIn, boardIn);
			
		}
		
		
	}
	
	/*
	 * this class represents a move where one piece overtakes a piece of the 
	 * opposing team. 
	 * 
	 * this class has one extra parameter which represents the piece of the 
	 * opponenets team that is being overtaken by the current piece
	 */
	public static class KillMove extends Moves{

		Piece beingKilled;
		
		public KillMove(Piece currPieceIn, int thereIn, Board boardIn, Piece beingKilledIn) {
			super(currPieceIn, thereIn, boardIn);
			beingKilled = beingKilledIn;
		}
		
		// the move takes place
		public Board completeMove() {
			return completeMove();
		}
		
	}

}
