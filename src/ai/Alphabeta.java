package ai;

import java.util.Iterator;
import java.util.List;

import Evaluator.BoardEvaluator;
import Model.Board;
import Model.Move;
import MoveGenerator.MoveGen;


/**
 * Static class to perform alpha-beta pruning.
 *  
 * @author zoltan
 */
public class Alphabeta {
	/**
	 * If true, it prints diagnostic messages.
	 */
	private static boolean VERBOSE = false;
	private static final int INFINITY = Integer.MAX_VALUE;
		
	enum Player { MAX, MIN };
	
	/**
	 * Public method to perform the algorithm.
	 * 
	 * @param	mvgen		move generator
	 * @param	brdeval		board evaluator
	 * @param	board		current board
	 * @param	player		player to choose a move for
	 * @param	maxdepth	depth of moves to look at
	 * @return				the best move for the given player
	 */
	public static Best chooseMove(MoveGen mvgen, BoardEvaluator brdeval,
			Board board, int player, int maxdepth) {
		if (player == board.player1()) {	// player1 => MAX
			if (VERBOSE)
				System.err.println("Alpha-Beta: choosing a move for MAX\n");
			return chooseMove(mvgen, brdeval, board, Player.MAX, maxdepth, -INFINITY, INFINITY);
		} else {	// 	player2 => MIN
			if (VERBOSE)
				System.err.println("AlphaBeta: choosing a move for MIN\n");
			return chooseMove(mvgen, brdeval, board, Player.MIN, maxdepth, -INFINITY, INFINITY);
		}
	}
	
	private static Best chooseMove(MoveGen mvgen, BoardEvaluator brdeval,
			Board board, Player player, int maxdepth, int alpha, int beta) {
		Best myBest = new Best();
		/////////////////////////////////////////
		if (player == Player.MAX) {	// if it is MAX turn
			myBest.setScore(-INFINITY);
			List<Move> moves = mvgen.generate(board, board.player1());
			if (moves.isEmpty()) {	// if no legal move for MAX at this depth; return null
				if (VERBOSE)
				System.err.println("MAX LEVEL " + maxdepth + " (no legal move)");
				return null;
			}
			Iterator<Move> itr = moves.iterator();
			while (itr.hasNext()) {
				Move move = itr.next();
				if (VERBOSE)
					System.err.println("MAX LEVEL " + maxdepth + " " + move.toString());
				if (maxdepth == 0) {	// if it is the maximum depth
					int score = brdeval.evaluate(move.getBoard(),move, maxdepth);	// score the board
					if (VERBOSE)
						System.err.println("\t[" + score + "]");
					if (score > myBest.getScore()) {
						myBest.setMove(move);
						myBest.setScore(score);
						if (score >= beta) {	// time to prune, i.e. exit from recursive call
							if (VERBOSE)
								System.err.println("\nAlpha-Beta decision:\n\tMAX LEVEL " + maxdepth + " {" + myBest.getMove().toString() + "} [" + myBest.getScore() + "]\n" +
										"\tRest of the moves pruned!\n");
							return myBest;
						}
					}	
				} else {	// otherwise, look one deeper
					Best reply = chooseMove(mvgen, brdeval, move.getBoard(), Player.MIN, maxdepth-1, alpha, beta);
					if (reply == null) {	// if no legal move for MIN to reply
						int score = brdeval.evaluate(move.getBoard(), move, maxdepth);	// evaluate the resulting board of the current move
						if (VERBOSE)
							System.err.println("\t[" + score + "]");
						if (score > myBest.getScore()) { // if it is a better move for MAX than the best move found this level
							myBest.setMove(move);	// update best move to this move
							myBest.setScore(score);
						}							
					} 
					else if (reply.getScore() > myBest.getScore()) {
						myBest.setMove(move);
						myBest.setScore(reply.getScore());
						alpha = reply.getScore();
					}
				}
			}
			if (VERBOSE)
				System.err.println("\nAlpha-Beta decision:\n\tMAX LEVEL " + maxdepth + " {" + myBest.getMove().toString() + "} [" + myBest.getScore() + "]\n");
			return myBest;
		/////////////////////////////////////////
		} else {	// if it is MIN turn
			myBest.setScore(INFINITY);
			List<Move> moves = mvgen.generate(board, board.player2());
			if (moves.isEmpty()) {	// if no legal move for MIN at this level; return null
				if (VERBOSE)
					System.err.println("MIN LEVEL " + maxdepth + " (no legal move)");
				return null;
			}
			Iterator<Move> itr = moves.iterator();
			while (itr.hasNext()) {
				Move move = itr.next();
				if (VERBOSE)
					System.err.println("MIN LEVEL " + maxdepth + " " + move.toString());
				if (maxdepth == 0) {	// if it is the maximum depth
					int score = brdeval.evaluate(move.getBoard(),move, maxdepth);	// score the board
					if (VERBOSE)
						System.err.println("\t[" + score + "]");
					if (score < myBest.getScore()) {
						myBest.setMove(move);
						myBest.setScore(score);
						if (score <= alpha) {	// time to prune, i.e. exit from recursive call
							if (VERBOSE)
								System.err.println("\nAlpha-Beta decision:\n\tMIN LEVEL " + maxdepth + " {" + myBest.getMove().toString() + "} [" + myBest.getScore() + "]\n" +
										"\tRest of the moves pruned!\n");
							return myBest;
						}
					}
				} else {	// otherwise, look one deeper
					Best reply = chooseMove(mvgen, brdeval, move.getBoard(), Player.MAX, maxdepth-1, alpha, beta);
					if (reply == null) {	// if no legal move for MAX to reply
						int score = brdeval.evaluate(move.getBoard(),move, maxdepth);	// evaluate the resulting board of the current move
						if (VERBOSE)
							System.err.println("\t[" + score + "]");
						if (score < myBest.getScore()) {	// if it is a better move for MIN than the best move found this level
							myBest.setMove(move);	// update best move to this move
							myBest.setScore(score);							
						}
					}					
					else if (reply.getScore() < myBest.getScore()) {
						myBest.setMove(move);
						myBest.setScore(reply.getScore());
						beta = reply.getScore();
					}					
				}
			}
			if (VERBOSE)
				System.err.println("\nAlpha-Beta decision:\n\tMIN LEVEL " + maxdepth + " {" + myBest.getMove().toString() + "} [" + myBest.getScore() + "]\n");
			return myBest;			
		}
	}
}
