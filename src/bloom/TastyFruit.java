package bloom;

import java.util.Random;

public class TastyFruit extends Fruit {

	private Vid vid;

	public TastyFruit() {
		super();
		Random rand = new Random();
		int prob = rand.nextInt(100);
		if (prob < 25) {
			vid = Vid.JABOLKO;
		} else if (prob < 47) {
			vid = Vid.KRUSHA;
		} else if (prob < 59) {
			vid = Vid.BANANA;
		} else if (prob < 67) {
			vid = Vid.ANANAS;
		} else if (prob < 83) {
			vid = Vid.PORTOKAL;
		} else {
			vid = Vid.CRESHA;
		}
	}

	public Vid getVid() {
		return vid;
	}

	public int getPoints() {
		return vid.getPoints();
	}

}
