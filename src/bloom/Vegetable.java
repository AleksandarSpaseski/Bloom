package bloom;

import java.util.Random;

public class Vegetable extends Fruit {
	private Vid vid;

	public Vegetable() {
		super();
		Random rand = new Random();
		int prob = rand.nextInt(100);

		if (prob < 25) {
			vid = Vid.KROMID;
		} else if (prob < 40) {
			vid = Vid.LUK;
		} else {
			vid = Vid.BROKULA;
		}

	}

	public Vid getVid() {
		return vid;
	}

	public int getPoints() {
		return vid.getPoints();
	}

}
