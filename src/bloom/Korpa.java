package bloom;

import java.awt.Rectangle;

public class Korpa {

	private static int centerX = 445;
	private static int centerY = 375;
	public static int speed = 15;
	public static Rectangle r = new Rectangle(0, 0, 0, 0);

	public void update() {
		r.setRect(centerX + 30, centerY + 10, 60, 50);
	}

	public void moveLeft() {
		if (centerX > 10)
			centerX -= speed;
	}

	public void moveRight() {
		if (centerX < 620)
			centerX += speed;
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

}
