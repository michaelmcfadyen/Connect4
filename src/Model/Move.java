package Model;

/**
 * Class to represent a move.
 * 
 * @author zoltan
 */
public class Move {
	/**
	 * X coordinate of the move.
	 */
	private int x;
	/**
	 * Y coordinate of the move.
	 */
	private int y;
	/**
	 * Reference to the resulting Board object.
	 */
	private Board board;
	
	public Move(int x, int y, Board b) {
		this.x = x;
		this.y = y;
		this.board = b;
	}
	
	public int getX() { return this.x; }
	
	public void setX(int x) { this.x = x; }
	
	public int getY() { return this.y; }
	
	public void setY(int y) { this.y = y; }
	
	public Board getBoard() { return this.board; }
	
	public String toString() { return "(" + x + ", " + y + ")"; }
}
