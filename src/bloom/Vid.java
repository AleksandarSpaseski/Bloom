package bloom;

public enum Vid {

	JABOLKO(25, 25), KRUSHA(28, 22), BANANA(38, 12), ANANAS(42, 8), PORTOKAL(
			34, 16), CRESHA(43, 17), KROMID(-30, 25), LUK(-20, 40), BROKULA(
			-10, 35);

	private final int points;
	private final int prob;

	Vid(int points, int prob) {
		this.points = points;
		this.prob = prob;
	}

	public int getPoints() {
		return points;
	}

	public int getProb() {
		return prob;
	}
}
