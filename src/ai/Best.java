package ai;

import Model.Move;

/**
 * Class to represent a best move during game tree search.
 * 
 * @author zoltan
 */
public class Best {
	/**
	 * A reference to the move itself.
	 */
	private Move move;
	/**
	 * Its score.
	 */
	private int score;
	
	public Best() {
		this.move = null;
		this.score = 0;
	}
	
	public Best(Move move, int score) {
		this.move = move;
		this.score = score;
	}
	
	public Move getMove() { return this.move; }
	
	public void setMove(Move move) { this.move = move; }
	
	public int getScore() { return this.score; }
	
	public void setScore(int score) { this.score = score; }
}
