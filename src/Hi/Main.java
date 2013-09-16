package Hi;
import ai.Alphabeta;
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
	
	public Main(){
		board = new Board();
		lmc = new LegalMoveChecker();
		mg = new MoveGen(board, lmc);
		ev = new BoardEvaluator();
		num_of_moves = 0;
	}
	
	public void playerMove(int player){
		while((board.gameOver(this.board) == -1) && !board.draw(this.board)){
			Best move;
			if(num_of_moves == 0 && board.isEmpty(5, 3)){
				move = board.firstMove(player);
			}
			else
				move = Alphabeta.chooseMove(mg, ev, board, player,2);
			
			System.out.printf("%s, %d\n",move.getMove(), move.getScore());
			board = move.getMove().getBoard();
			player = board.switchPlayer(player);
			System.out.println(board.printSmall());
			
			num_of_moves++;
		}
	}
	
	public static void main(String args[]){
		Main main = new Main();
		main.board.put(5, 2, main.board.player1());
		main.board.put(5, 1, main.board.player1());
		main.board.put(5, 3, main.board.player1());
		main.board.put(4, 1, main.board.player2());
		main.board.put(4, 2, main.board.player1());
		main.board.put(3, 2, main.board.player2());
		//main.board.put(2,2, main.board.player1());
		//boolean win = main.board.win(main.board, main.board.player1());
		//System.out.println(main.board.printSmall());
		//System.out.println(win);
		main.playerMove(main.board.player1());
	}
}
