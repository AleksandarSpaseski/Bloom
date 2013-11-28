package bloom;

import java.util.Random;

public class GoodItem extends Item {

	private Type vid;

	public GoodItem() {
		super();
		Random rand = new Random();
		int prob = rand.nextInt(100);
		if (prob < 40) {
			vid = Type.Item1;
		} else if (prob < 50) {
			vid = Type.Item2;
		} else if (prob < 70) {
			vid = Type.Item3;
		} else {
			vid = Type.Item4;
		}
	}

	public Type getVid() {
		return vid;
	}

	public int getPoints() {
		return vid.getPoints();
	}

}
