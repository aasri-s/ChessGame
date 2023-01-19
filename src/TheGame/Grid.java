package TheGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Pieces.Piece;
import gameBoard.Board;
import gameBoard.Square;
/*
 * represent the visual creation of the game
 */
public class Grid {
	private JFrame chessFrame;
	private GridSquare allGridSquares;
	Board board;
	Square here;
	Square there;
	Piece movingPiece;
	
	public Grid() {
		// create the JFrame for the grid
		chessFrame = new JFrame("Welcome fellow GM");
		// sets the dimensions for the board
		chessFrame.setSize(new Dimension(600, 600));
		// creates the board the game will be played on
		board = new Board();
		//creates all of the individual squares on the grid and added them
		// to the grid
		allGridSquares = new GridSquare(board, here, there, movingPiece);
		chessFrame.add(allGridSquares, BorderLayout.CENTER);
		chessFrame.setVisible(true);
		chessFrame.setLayout(new BorderLayout());
		
	}
	

	
}

