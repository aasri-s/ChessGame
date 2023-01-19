package gameBoard;

import java.util.ArrayList;


import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

/* This class is a logical representation of the chess game. It creates holds an
 * array of the Squares(each square on a board). There squares together represent
 * the board.
 * 
 * The class alsp holds three arrays that represents all of the Pieces,
 * the white pieces and the black pieces. It also holds the white and black player
 * and a boolean representing the player whose turn it is next
 * 
 */
public class Board{
	
	// an array of all of the squares on the board
	private Square[] board;
	// holds all of the pieces (pawn, rook, etc..) on the board, both the 
	// white and black peices
	private final ArrayList<Piece> pieces = new ArrayList<Piece>(64);;
	// represents whether the white player goes next or not
	private final boolean whiteIsNext;
	// an array of pieces holding all of the white pieces
	private final ArrayList<Piece> whitePieces;
	// an array of pieces holding all of the black pieces
	private final ArrayList<Piece> blackPieces;
	// the grandmaster using the black peices
	private final Black black;
	// the grandmaster using the white peices
	private final White white;
	
	// constructer for the board
	public Board() {
		// initializes the array holding all of the peices, empty or not
		for(int i = 0; i < 64; i++) {
			pieces.add(null);
		}
		// initializes the board
		initBoard();
		// all of the white peices are added to the whitePieces array
		whitePieces = getWhitePieces();
		// all of the black peices are added to the blackPieces array
		blackPieces = getBlackPieces();	
		// all of the valid moves for the white pieces are added to the 
		// white moves array
		ArrayList<Moves> wMoves = new ArrayList<Moves>();
		for(Piece w: whitePieces) {
			wMoves.addAll(w.ValidMoves(this));
		}
		// all of the valid moves for the black pieces are added to the 
			// black moves array
		ArrayList<Moves> bMoves = new ArrayList<Moves>();
		for(Piece b: blackPieces) {
			bMoves.addAll(b.ValidMoves(this));
		}
		// white by default is first
		whiteIsNext = false;
		// the grandmaster using white pieces is initialized
		white = new White(wMoves, bMoves, this);
		// the grandmaster using black pieces is initialized
		black = new Black(wMoves, bMoves, this);
	}
	
	// a piece is moved to a certain position
	public void setPiece(Piece piece) {
		pieces.set(piece.position, piece);
	}
	
	// returns the current player
	public boolean whosPlaying() {
		return !this.whiteIsNext;
	}
	
	
	// adds all of the pieces to the correct initial positions
	public Square[] initBoard() {	
		pieces.set(0, new Rook(Type.ROOK, 0, true));
		pieces.set(7, new Rook(Type.ROOK, 7, true));
		pieces.set(56, new Rook(Type.ROOK, 56, false));
		pieces.set(63, new Rook(Type.ROOK, 63, false));
		
		pieces.set(1, new Knight(Type.KNIGHT, 1, true));
		pieces.set(6, new Knight(Type.KNIGHT, 6, true));
		pieces.set(57, new Knight(Type.KNIGHT, 57, false));
		pieces.set(62, new Knight(Type.KNIGHT, 62, false));
		
		pieces.set(2, new Bishop(Type.BISHOP, 2, true));
		pieces.set(5, new Bishop(Type.BISHOP, 5, true));
		pieces.set(58, new Bishop(Type.BISHOP, 58, false));
		pieces.set(61, new Bishop(Type.BISHOP, 61, false));
		
		pieces.set(3, new Queen(Type.QUEEN, 3, true));
		pieces.set(59, new Queen(Type.QUEEN, 59, false));
		
		pieces.set(4, new King(Type.KING, 4, true));
		pieces.set(60, new King(Type.KING, 60, false));
		
		for(int i = 0; i < 8; i++) {
			pieces.set(i+8, new Pawn(Type.PAWN, i+8, true));
			pieces.set(i+48, new Pawn(Type.PAWN, i+48, false));
		}
		
		// all of the pieces are added to their respective beggining squares
		// i.e pieces are setup at their initial positions
		board = new Square[64];
		for(int i = 0; i < 64; i++) {
			if(pieces.get(i)!=null) {
				board[i] = Square.makeSquare(i, pieces.get(i));
			} else {
				board[i] = Square.makeSquare(i, null);
			}
			
		}
		
		return board;
		
	}
	
	// white pieces on the board are returns
	public ArrayList<Piece> getWhitePieces(){
		ArrayList<Piece> whitePieceList = new ArrayList<Piece>();
		for (Square s: board) {
			if(!s.empty) {
				if(s.getPiece().isWhite) {
					whitePieceList.add(s.getPiece());
				}
			}
		}
		return whitePieceList;
	}
	// black pieces on the board are returns
	public ArrayList<Piece> getBlackPieces(){
		ArrayList<Piece> blackPieceList = new ArrayList<Piece>();
		for (Square s: board) {
			if(!s.empty) {
				if(!s.getPiece().isWhite) {
					blackPieceList.add(s.getPiece());
				}
			}
		}
		return blackPieceList;
	}
	
	
	//true if white is player and false if black is player
	public boolean setWhosNext(boolean whiteNext){
		return whiteNext;
	}
	
	
	// returns the square that is at the identified position
	public Square getSquare(int position) {
		return board[position];
	}
	
	
	// prints out the string representation of the board
	@Override
	public String toString() {
		for(int i = 0; i < 64; i++) {
			if(i%8 ==0) {
				System.out.println(" ");
			}
			if (board[i].empty) {
				System.out.print("* ");
			} else {
				if(board[i].getPiece().type==Type.PAWN) {
					if(board[i].getPiece().isWhite) {
						System.out.print("P ");
					}else {
						System.out.print("p ");
					}
				} else if (board[i].getPiece().type==Type.ROOK){
					if(board[i].getPiece().isWhite) {
						System.out.print("R ");
					}else {
						System.out.print("r ");
					}
				} else if (board[i].getPiece().type==Type.KNIGHT) {
					if(board[i].getPiece().isWhite) {
						System.out.print("H ");
					}else {
						System.out.print("h ");
					}
				} else if (board[i].getPiece().type==Type.BISHOP) {
					if(board[i].getPiece().isWhite) {
						System.out.print("B ");
					}else {
						System.out.print("b ");
					}
				} else if (board[i].getPiece().type==Type.QUEEN) {
					if(board[i].getPiece().isWhite) {
						System.out.print("Q ");
					}else {
						System.out.print("q ");
					}
				} else if (board[i].getPiece().type==Type.KING) {
					if(board[i].getPiece().isWhite) {
						System.out.print("K ");
					}else {
						System.out.print("k ");
					}
				}
				
			}
			
		}
		return null;
	}
	
	// returns the grandmaster using white pieces
	public GrandMaster getWhite() {
		return white;
	}
	// returns the grandmaster using black pieces
	public GrandMaster getBlack() {
		return black;
	}
}