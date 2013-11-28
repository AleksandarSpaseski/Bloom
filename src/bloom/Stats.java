package bloom;

public class Stats {
	private static int totalPoints = 0;
	private static int lives = 5;
	public static int level = 1;

	public static void updatePoints(Item item) {
		totalPoints += item.getVid().getPoints();
	}

	public static int getTotalPoints() {
		return totalPoints;
	}

	public static int getLives() {
		return lives;
	}

	public static void setLives() {
		lives--;
	}

}
