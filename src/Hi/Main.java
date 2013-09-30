package Hi;
import ai.Best;
import ai.Minimax;
import Evaluator.BoardEvaluator;
import LegalMove.LegalMoveChecker;
import Model.Board;
import Model.Move;
import MoveGenerator.MoveGen;


public class Main {
	public Board board;
	public LegalMoveChecker lmc;
	public MoveGen mg;
	public BoardEvaluator ev;
	public static int num_of_moves;
	public int maxdepth;
	
	public Main(int md){
		board = new Board();
		lmc = new LegalMoveChecker();
		mg = new MoveGen(board, lmc);
		ev = new BoardEvaluator();
		num_of_moves = 0;
		maxdepth = md;
	}
	
	public void playerMove(int player){
		while((board.gameOver() == -1) && !board.draw(this.board)){
			Best move_me;
			Best move_opponent;
			Best best_move;
			if(num_of_moves == 0 && board.isEmpty(5, 3)){
				best_move = board.firstMove(player);
			}
			else{
				move_me = Minimax.chooseMove(mg, ev, board, player, 3);
				//Best move for opponent is best move for me
				move_opponent = Minimax.chooseMove(mg, ev, board, board.switchPlayer(player),3);
				move_opponent.getMove().getBoard().put(move_opponent.getMove().getX(), move_opponent.getMove().getY(), player);
				
				if (move_me.getScore() >= move_opponent.getScore())
					best_move = move_me;
				else
					best_move = move_opponent;
			}
			
			System.out.printf("%s, %d\n",best_move.getMove(), best_move.getScore());
			board = best_move.getMove().getBoard();
			player = board.switchPlayer(player);
			System.out.println(board.printSmall());
			
			num_of_moves++;
		}
	}
	
	public static void main(String args[]){
		Main main = new Main(14);
		main.board.put(5, 5, main.board.player1());
		main.board.put(5, 4, main.board.player1());
		main.board.put(5, 3, main.board.player1());
		main.board.put(4, 5, main.board.player1());
		main.board.put(3, 5, main.board.player1());

		//main.board.put(2,2, main.board.player1());
		//boolean win = main.board.win(main.board, main.board.player1());
		//System.out.println(main.board.printSmall());
		//System.out.println(win);
		main.playerMove(main.board.player2());
	}
}
