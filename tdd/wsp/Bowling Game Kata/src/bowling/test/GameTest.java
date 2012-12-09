package bowling.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bowling.main.Game;

public class GameTest {
	
	private Game game;

	private void rollMany(int pins, int count) {
		for (int i = 0; i < count; i++) {
			game.roll(pins);
		}
	}

	private void rollSpare() {
		game.roll(3);
		game.roll(7);
	}

	private void rollStrike() {
		game.roll(10);
	}
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void testGutterGame() throws Exception {
		rollMany(0, 20);
		assertEquals(0, game.score());
	}
	
	@Test
	public void testAllOneGame() throws Exception {
		rollMany(1, 20);
		assertEquals(20, game.score());
	}

	@Test
	public void testSpare() throws Exception {
		
		rollSpare();
		game.roll(5);
		rollMany(0, 17);
		
		assertEquals(20, game.score());
	}
	
	@Test
	public void testStrike() throws Exception {
		
		rollStrike();
		game.roll(3);
		game.roll(5);
		rollMany(0, 17);
		assertEquals(26, game.score());
	}
	
	@Test
	public void testPerfect() throws Exception {
		rollMany(10, 12);
		assertEquals(300, game.score());
	}

}
