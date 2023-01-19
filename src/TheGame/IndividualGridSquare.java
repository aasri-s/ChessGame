package TheGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Pieces.Piece;
import gameBoard.Board;
import gameBoard.NonEmptySquare;
import gameBoard.Square;
import gameBoard.Type;

/*
 * represents a single square on the gui
 */
public class IndividualGridSquare extends JPanel{
	//the squares unique number
	final int squareNo;
	// whether the square is white or not
	final boolean isWhite;
	
	// the individual squares are created on the GUI along with their 
	// backgrounds being set to create the checkerboard effetc
	IndividualGridSquare(Board board, int squareNoIn,GridSquare gridSquareIn, boolean isWhiteIn,
			Square here, Square there, Piece move){
		super(new GridBagLayout());
		squareNo = squareNoIn;
		this.setPreferredSize(new Dimension(10,10));
		isWhite = isWhiteIn;
		if (isWhite) {
			this.setBackground(Color.white);
		} else {
			this.setBackground(Color.pink);
		}
		addPictures(board);
	}
	
	//all of the respective images are added to the respective squares on the GUI
	public void addPictures(Board board) {
		this.removeAll();
		JLabel token = new JLabel("");
		if(!board.getSquare(squareNo).empty) {
			NonEmptySquare currSquare  = (NonEmptySquare) board.getSquare(squareNo);
			Type type = currSquare.getPiece().type;
			if(type == Type.BISHOP) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♗");
				} else {
					token = new JLabel("♝");
				}
			} else if (type == Type.KING) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♔");
				} else {
					token = new JLabel("♚");
				}
			} else if (type == Type.KNIGHT) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♘");
				} else {
					token = new JLabel("♞");
				}
			} else if (type == Type.PAWN) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♙");
				} else {
					token = new JLabel("♟");
				}
			} else if (type == Type.QUEEN) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♕");
				} else {
					token = new JLabel("♛");
				}
			} else if (type == Type.ROOK) {
				if (currSquare.getPiece().isWhite) {
					token = new JLabel("♖");
				} else {
					token = new JLabel("♜");
				}
			}
			token.setFont(new Font("Times New Roman", Font.PLAIN, 40));
			this.add(token);
		}
	}
}
