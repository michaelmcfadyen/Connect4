package Evaluator;

import Model.Board;
import Model.Move;

public class BoardEvaluator {

	public int evaluate(Board board, Move m, int maxdepth, int player){
		Board b = board;
		int winner = b.gameOver();
		if(winner == board.player1()){
			//System.out.println(board.printSmall()+"\nWinning board for "+player);
			return 1000*maxdepth;
		}
		else if(winner == -1){
			//System.out.println(board.printSmall()+"\nGame not over");
			int count = board.maxInRow(m);
			return count*100;
		}
		else
			//System.out.println(board.printSmall()+"Losing board for "+player);
			return -1000;
		
	}
	public static void main(String args[]){
		
	}
}