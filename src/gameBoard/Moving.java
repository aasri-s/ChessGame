package gameBoard;

/*
 * represents while a move is taking place and whehter the move was 
 * successful or not
 */
public class Moving {
	// the move taking place
	private final Moves move;
	// the updated board after the move takes place
	private final Board afterBoard;
	// the result of the move and whether it resulted in a 
	//1 = success, 0 = failure, 2 = checkmate
	protected int result;
	
	//constructor
	public Moving(Moves moveIn, Board afterBoardIn, int resultIn) {
		move = moveIn;
		afterBoard = afterBoardIn;
		result = resultIn;
	}
}

