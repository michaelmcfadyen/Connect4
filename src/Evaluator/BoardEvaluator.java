package Evaluator;

import Model.Board;
import Model.Move;

public class BoardEvaluator {

	public int evaluate(Board board, Move m, int maxdepth){
		Board b = board;
		System.out.println(maxdepth+"\n");
		int winner = b.gameOver();
		if(winner == board.player1()){
			//System.out.println(board.printSmall()+"\nWinning board for "+player);
			return 1000*maxdepth;
		}
		else if(winner == -1){
			//System.out.println(board.printSmall()+"\nGame not over");
			return -500;
		}
		else
			//System.out.println(board.printSmall()+"Losing board for "+player);
			return -1000;
		
	}
	public static void main(String args[]){
		Board b =  new Board();
		b.put(5, 2, b.player1());
		b.put(5, 1, b.player1());
		b.put(5, 3, b.player1());
		b.put(4, 1, b.player2());
		b.put(4, 2, b.player1());
		b.put(3, 2, b.player2());
		BoardEvaluator be = new BoardEvaluator();
		int result = be.evaluate(b, new Move(2,3, b), 0);
		System.out.println(result);
	}
}