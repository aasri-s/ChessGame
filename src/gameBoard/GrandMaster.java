package gameBoard;

import java.util.ArrayList;
import java.util.List;

import Pieces.King;
/* This class represents the components of one of the members of the game.
 * This is an abstract class extended by the White class and the Black class
 */
public abstract class GrandMaster {
	protected King myKing;
	protected ArrayList<Moves> myMoves;
	protected ArrayList<Moves> otherMoves;
	protected Board board;
	protected GrandMaster other;
	protected boolean checked;
	protected boolean isWhite;

	public GrandMaster(ArrayList<Moves> myMovesIn,
			ArrayList<Moves> otherMovesIn, Board boardIn) {
		myMoves = myMovesIn;
		otherMoves = otherMovesIn;
		board = boardIn;
	}

	/*
	 * determines if a move is allowed
	 * 
	 * true is returned if the move can occur and false is returned if the move 
	 * cannot occur
	 */
	public boolean isValidMove(Moves move) {
		return myMoves.contains(move);
	}

	/*
	 * determines if the king is in danger and if the danger is escapable.
	 * 
	 * true is returned if checkmate (king may be killed in next round) and 
	 * there is a scenario where the king can escape death 
	 */
	public boolean getCheckmated() {
		if (this.checked && canGetOut()) {
			return false;
		}
		return true;
	}

	/*
	 * this method returns true is a piece is able to successfully escape the 
	 * danger it faces via a move and false if it cannot escape being
	 * killed by the opponent
	 */
	private boolean canGetOut() {
		for (Moves move : this.myMoves) {
			Moving moving = movePiece(move);
			if (moving.result == 1) {
				return true;
			}
		}
		return false;
	}

	/*
	 * returns true if the king will be killed in the next round and if the 
	 * king cannot escape the kill
	 */
	public boolean getChecked() {
		return checked;
	}

	/* returns a Moving object(represents the state between moves)
	 * 
	 * this method allows a piece to be moved
	 * 
	 */
	public Moving movePiece(Moves move) {
		// if the move is not valid, then a failed move is returned
		if(!isValidMove(move)) {
			return new Moving(move, board, 0);
		}
		
		// a new board will be created to represent the board after the 
		// move is completed
		Board afterMove = move.completeMove();
		// if it is a white piece being moves
		if(afterMove.whosPlaying()) {
			// we check if the move endangers the king and if so we create
			// a checked moving object if not a successful moving object
			// is created
			if (GrandMaster.hitsAgainstSquare(afterMove.getBlack().myKing.position, afterMove.getBlack().myMoves).isEmpty()) {
				return new Moving(move, board,1);
			} else {
				return new Moving(move, afterMove, 2);
			}
		} else {
			// the same occurs but this is for if a black piece is being moved
			if (GrandMaster.hitsAgainstSquare(afterMove.getWhite().myKing.position, afterMove.getWhite().myMoves).isEmpty()) {
				return new Moving(move, board,1);
			} else {
				return new Moving(move, afterMove, 2);
			}
		}
		
		
		
	}

	// returns a list of the potential attacks that could be made against a 
	// specific square on the board
	protected static List<Moves> hitsAgainstSquare(int position,
			List<Moves> otherMoves) {
		ArrayList<Moves> hits = new ArrayList<Moves>();
		for (Moves move : otherMoves) {
			if (move.currPiece.position == position) {
				hits.add(move);
			}
		}
		return hits;
	}
}
