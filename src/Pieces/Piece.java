package Pieces;

import java.util.ArrayList;
import java.util.List;

import gameBoard.Board;
import gameBoard.Moves;
import gameBoard.Type;

/*
 * abstract class that represents the structure of the different
 * pieces that may be on the board
 */
public abstract class Piece {
	// represents the type of peice
	public Type type;
	// where the piece currently is on the board
	public int position;
	// whether the peice has been killed or not (true = alive)
	boolean alive;
	// whether the peice is on the white time or not
	public boolean isWhite;
	
	public Piece(Type typeIn, int positionIn, boolean isWhiteIn) {
		this.type = typeIn;
		int position = positionIn;
		isWhite = isWhiteIn;
		this.alive = true;
	}
	
	// returns if two pieces are equal or not
	public boolean equals(Piece otherPiece) {
		if (this == otherPiece) {
			return true;
		}
		if(this.getClass().equals(otherPiece.getClass())) {
			if(this.type.equals(otherPiece.type) && 
					this.position == otherPiece.position && 
					this.isWhite == otherPiece.isWhite &&
					this.alive == otherPiece.alive
					) {
				return true;
			} else {
				return false;
			}
				
		} else {
			return false;
		}
	}
	
	// returns the hashcode of a piece
	public int hashcode() {
		return this.position;
	}
	
	// returns a list of the available moves for a piece to make
	public List<Moves> ValidMoves(Board board) {
		return new ArrayList<Moves>();
	}
	
	// returns true if a position is on the board or not
	public boolean isValid(int position) {
		return position < 64 && position > 0;
	}	
	
	// returns the piece after its type is changed
	public abstract Piece changePiece(Moves changingPiece, boolean isWhite);
	
}
