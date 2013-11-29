package bloom;

import java.awt.Rectangle;
import java.util.Random;

public abstract class Item {

	protected int centerX, centerY;
	public static int speedY = 2;
	protected int points;

	public Rectangle r = new Rectangle(0, 0, 0, 0);

	public Item() {
		Random rand = new Random();
		centerY = 0;
		centerX = rand.nextInt(680) + 30;
	}

	public void update() {
		centerY += speedY;
		r.setRect(centerX, centerY, 50, 20);
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

	abstract public Type getVid();

}
