package MoveGenerator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import LegalMove.LegalMoveChecker;
import Model.Board;
import Model.Move;

public class MoveGen {
	
	private ArrayList<Point> valid = new ArrayList<Point>();
	private LegalMoveChecker lmc;
	
	public MoveGen(Board b, LegalMoveChecker l){
		lmc = l;
		validMoves(b);
	}
	
	private void validMoves(Board b){
		for(int i = 0; i < b.row(); i ++){
			for (int j = 0 ; j < b.column(); j++){
				Point p = new Point(i,j);
				valid.add(p);
			}
		}
	}
	public List<Move> generate(Board b, int player){
		List<Move> legalMoves = new ArrayList<Move>();
		
		for(Point p : valid){
			Board b0 = lmc.isLegal(player, b, p.x, p.y);
			
			if(b0 != null){
				//System.out.format("Legal Move (%d,%d)\n", p.x,p.y);
				Move m = new Move(p.x,p.y, b0);
				legalMoves.add(m);
			}
		}
		return legalMoves;
	}
}
