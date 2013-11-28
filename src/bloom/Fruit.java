package bloom;

import java.awt.Rectangle;
import java.util.Random;

//promena vo ffruit;

public abstract class Fruit {

	protected int centerX, centerY;
	public static int speedY = 2;
	protected int points;

	public Rectangle r = new Rectangle(0, 0, 0, 0);

	public Fruit() {
		Random rand = new Random();
		centerY = 0;
		centerX = rand.nextInt(680) + 40;
	}

	public void update() {
		centerY += speedY;
		r.setRect(centerX, centerY, 50, 30);
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerX = centerY;
	}

	abstract public Vid getVid();

}
