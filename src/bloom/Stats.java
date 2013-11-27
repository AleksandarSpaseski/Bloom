package bloom;

public class Stats {
	private static int totalPoints = 0;
	private static int lives = 5;
	public static int level = 1;

	public static void updatePoints(Fruit points) {
		switch (points.getVid()) {
		case ANANAS:
			totalPoints += Vid.ANANAS.getPoints();
			break;
		case BANANA:
			totalPoints += Vid.BANANA.getPoints();
			break;
		case BROKULA:
			totalPoints += Vid.BROKULA.getPoints();
			break;
		case CRESHA:
			totalPoints += Vid.CRESHA.getPoints();
			break;
		case JABOLKO:
			totalPoints += Vid.JABOLKO.getPoints();
			break;
		case KROMID:
			totalPoints += Vid.KROMID.getPoints();
			break;
		case KRUSHA:
			totalPoints += Vid.KRUSHA.getPoints();
			break;
		case LUK:
			totalPoints += Vid.LUK.getPoints();
			break;
		case PORTOKAL:
			totalPoints += Vid.PORTOKAL.getPoints();
			break;
		default:
			break;
		}

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
