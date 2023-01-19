package TheGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Pieces.Piece;
import gameBoard.Board;
import gameBoard.Square;

/*
 * represents the collection of squares on te board GUI
 */
public class GridSquare extends JPanel{
	//list of all of the squares on the board
	final ArrayList<IndividualGridSquare> gridSquares;

	public GridSquare(Board board, Square hereIn, Square thereIn, Piece movingPieceIn) {
		super(new GridLayout(8,8));
		gridSquares = new ArrayList<IndividualGridSquare>();
		boolean isWhite = true;
		//allows the checkerboard coloring to be created
		for (int i = 0; i < 64; i++) {
			if(i%8 == 0) {
				
			} else {
				isWhite = !isWhite;
			} 
			//each individual square is created and added to the grid
			IndividualGridSquare gridSquare = new IndividualGridSquare(board, i,this, isWhite, hereIn, thereIn, movingPieceIn);
			add(gridSquare);
			this.gridSquares.add(gridSquare);
			
		}
		//size of the board is set
		this.setPreferredSize(new Dimension(400,350));
		validate();
	}
}
