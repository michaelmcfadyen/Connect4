package Model;

import java.awt.Point;

import ai.Best;

public class Board {
	private final static int EMPTY = 1;
	private final static int BLACK = 2;
	private final static int WHITE = 3;
	private final static int ROWS = 6;
	private final static int COLUMNS = 7;
	
	//should be thought as grid[rows][columns]
	private final int[][] grid;
	
	public Board(){
		this.grid = new int[ROWS][COLUMNS];
		for(int i = 0 ; i < ROWS; i++){
			for(int j = 0 ; j < COLUMNS; j++){
				this.grid[i][j] = EMPTY;
			}
		}
	}
	public Board(Board b){
		int[][] other = b.getGrid();
		int[][] copy = new int[ROWS][COLUMNS];
		for(int i = 0 ; i < ROWS; i++){
			for(int j = 0 ; j < COLUMNS; j++){
				copy[i][j] = other[i][j];
			}
		}
		this.grid = copy;
	}
	
	public Board clone(){
		return new Board(this);
	}
	public int player1(){
		return BLACK;
	}
	public int player2(){
		return WHITE;
	}
	public int switchPlayer(int player){
		return (player == player1()) ? player2() : player1();
	}
	public int[][] getGrid(){
		return grid;
	}
	public int row(){
		return ROWS;
	}
	public int column(){
		return COLUMNS;
	}
	public void put(int x, int y, int player){
		if(inBounds(x,y)){
			grid[x][y] = player;
		}
		else
			System.out.println("Position is out of bounds");
	}
	public boolean inBounds(int x, int y){
		return x >= 0 && x < ROWS && y >= 0 && y < COLUMNS;
	}
	public boolean isEmpty(int x,int y){
		return grid[x][y] == EMPTY;
	}
	public int peek(int x, int y){
		return grid[x][y];
	}
	public Best firstMove(int player){
		Move m = new Move(5 ,3, this);
		if(peek(m.getX(),m.getY()) == EMPTY){
			this.put(m.getX(), m.getY(), player);
			return new Best(m, 501);
		}
		return null;
	}
	public String printSmall() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS ; j++) {
				int c = this.grid[i][j];
				switch (c) {
				case BLACK:
					buffer.append("x ");
					break;
				case WHITE:
					buffer.append("0 ");
					break;

				default:
					buffer.append(". ");
					break;
				}
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	public int gameOver(){
		//System.out.println("Check for end game\n" + board.printSmall());
		for(int x = 0; x < ROWS; x++){
			for(int y = 0; y < COLUMNS; y++){
				if(this.peek(x,y) != EMPTY){
					for(Direction dir : Direction.values()){
						int winner = searchDir(dir.fromPos(x, y), dir, this.peek(x,y));
						if( winner != -1)
							//System.out.println("True");
							return winner;
					}
				}
			}
		}
		//System.out.println("False");
		return -1;
	}
	
	public boolean draw(Board board){
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				if(peek(i,j) == EMPTY)
					return false;
			}
		}
		return true;
	}
	private int searchDir(Point p, Direction d ,int player){
		int i = 1;
		while(i < 4){
			if(inBounds(p.x,p.y) && peek(p.x,p.y) == player){
				p = d.fromPos(p.x, p.y);
				i++;
			}
			else
				return -1;
		}
		return player;
	}
	
	public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;

	//Generates new point in accordance to what direction you are going
	public Point fromPos(int x, int y) {
		switch (this) {
		case NORTH:
			return new Point(x - 1, y);
		case SOUTH:
			return new Point(x + 1, y);
		case WEST:
			return new Point(x, y - 1);
		case NORTHEAST:
			return new Point(x - 1, y + 1);
		case NORTHWEST:
			return new Point(x - 1, y - 1);
		case SOUTHEAST:
			return new Point(x + 1, y + 1);
		case SOUTHWEST:
			return new Point(x + 1, y - 1);
		default:
			return new Point(x, y + 1);
		}
	}

    }
	
	public static void main(String args[]){
		Board b = new Board();
		for(int i = 0; i < 4; i++){
			b.put(i, 7, b.player2());
		}
		System.out.println(b.gameOver());
	}

}
