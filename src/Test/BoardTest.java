package Test;

import static org.junit.Assert.*;
import Model.Board;
import org.junit.Test;

public class BoardTest {

	@Test
	public void switchPlayer(){
		Board b = new Board();
		int player = b.switchPlayer(b.player1());
		assertEquals(b.player2(),player);
	}
	@Test
	public void switchPlayer2(){
		Board b = new Board();
		int player = b.switchPlayer(b.player2());
		assertEquals(b.player1(),player);
	}
	@Test
	public void switchPlayer3(){
		Board b = new Board();
		int player = b.switchPlayer(b.player2());
		player = b.switchPlayer(player);
		assertEquals(b.player2(),player);
	}
	@Test
	public void put(){
		Board b = new Board();
		b.put(5, 5, b.player1());
		assertEquals(b.player1(),b.peek(5, 5));
	}
	@Test
	public void isEmpty(){
		Board b = new Board();
		for(int i = 0 ; i < b.row(); i++){
			for(int j = 0 ; j < b.column(); j++){
				assertEquals(true,b.isEmpty(i, j));
			}
		}
	}

}
