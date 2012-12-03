package test;

import static org.junit.Assert.*;

import main.Game;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BowlingGameTest {
	
	private Game game;

	private void rollMany(int n, int pins) {
		for (int i = 0; i < n; i++) {
			game.roll(pins);
		}
	}

	private void rollSpare() {
		game.roll(5);
		game.roll(5);
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
		rollMany(20, 0);
		assertEquals(0, game.score());
	}
	
	@Test
	public void testAllOnes() throws Exception {
		rollMany(20, 1);
		assertEquals(20, game.score());
	}
	
	@Test
	public void testOneSpare() throws Exception {
		rollSpare();
		game.roll(3);
		rollMany(17, 0);
		assertEquals(16, game.score());
	}

	@Test
	public void testStrike() throws Exception {
		rollStrike();
		game.roll(4);
		game.roll(5);
		assertEquals(28, game.score());
	}
	
	@Test
	public void testPerfectGame() throws Exception {
		rollMany(12, 10);
		assertEquals(300, game.score());
	}


}
