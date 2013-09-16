package LegalMove;

import java.awt.Point;

import Model.Board;

public class LegalMoveChecker {
	
	public Board isLegal(int player, Board board, int x, int y){
		Board copyBoard = board.clone();
		if(!board.isEmpty(x, y)){
			//System.out.println("Point is not empty");
			return null;
		}
		if(!stoneBelow(board, x,y)){
			//System.out.println("No Stone Below");
			return null;
		}
		copyBoard.put(x, y, player);
		return copyBoard;
		
	}
	private boolean stoneBelow(Board board, int x, int y){
		if(!board.inBounds(x + 1, y))
			return true;
		return !board.isEmpty(x + 1, y);
	}
	
	public static void main(String args[]){
		
		Board board = new Board();
		LegalMoveChecker lmc = new LegalMoveChecker();
		Point p = new Point(0,1);
		Board out = lmc.isLegal(board.player1(), board, p.x,p.y); 
		if(out == null){
			System.out.println("Move at  is illegal\n");
		}
		else{
			System.out.println("Move at  is legal\n");
			System.out.println(out.printSmall());

		}
		out = lmc.isLegal(board.player1(), board, p.x, p.y);
		if(out == null){
			System.out.println("Move at is illegal\n");
		}
		else{
			System.out.println("Move at is legal\n");
			System.out.println(out.printSmall());

		}
		Board b = new Board();
		b.put(5, 6, b.player1());
		for(int i=0; i < 6; i++){
			for(int j=0; j < 7 ;j++){
				
				Board b0 = lmc.isLegal(b.player1(), b, i, j);
				if(b0 == null){
					System.out.format("Move at (%d,%d) is illegal\n",i,j);
				}
				else{
					System.out.format("Move at (%d,%d) is legal\n", i,j);
					System.out.println(b0.printSmall());

				}
			}
		}
	}
	
}
