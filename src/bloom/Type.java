package bloom;

public enum Type {

	Item1(25, 40), Item2(42, 10), Item3(34, 20), Item4(43, 30), Item5(-20, 100);

	private final int points;
	private final int prob;

	Type(int points, int prob) {
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
