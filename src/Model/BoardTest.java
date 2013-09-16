package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void drawTrue() {
		Board b = new Board();
		for(int x = 0; x < b.row() ; x++){
			for(int y = 0; y < b.column();y++){
				b.put(x, y, b.player1());
			}
		}
		assertEquals();
		fail("Not yet implemented");
	}

}
