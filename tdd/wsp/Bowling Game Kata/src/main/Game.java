package main;

public class Game {

	private int rolls[] = new int[21];
	private int currentIndex = 0;

	public void roll(int pins) {
		rolls[currentIndex++] = pins;
	}

	public int score() {
		int score = 0;
		int frameIndex = 0;
		
		for (int frame = 0; frame < 10; frame++) {
			
			if (isStrike(frameIndex)) {
				
				score += 10 + strikeBonus(frameIndex);
				frameIndex += 1;
				
			} else if (isSpare(frameIndex)) {
				
				score += 10 + spareBonus(frameIndex);
				frameIndex += 2;
				
			} else {
				
				score += rolls[frameIndex] + rolls[frameIndex+1];
				frameIndex += 2;
				
			}

		}
		return score;
	}
	
	private int strikeBonus(int frameIndex) {
		return rolls[frameIndex+1] + rolls[frameIndex+2];
	}
	
	private int spareBonus(int frameIndex) {
		return rolls[frameIndex+2];
	}

	private boolean isStrike(int frameIndex) {
		return rolls[frameIndex] == 10;
	}

	private boolean isSpare(int frameIndex) {
		return rolls[frameIndex] + rolls[frameIndex+1] == 10;
	}
}
