package bloom;

import java.awt.Rectangle;

public class Korpa {

	private static int centerX = 445;
	private static int centerY = 375;
	public static int speed = 15;
	public static Rectangle r = new Rectangle(0, 0, 0, 0);

	public void update() {
		if (StartingClass.gameMode.equals("halloween"))
			r.setRect(centerX + 55, centerY + 10, 60, 50);
		else
			r.setRect(centerX + 30, centerY + 10, 60, 50);
	}

	public void moveLeft() {
		if (centerX > 10)
			centerX -= speed;
	}

	public void moveRight() {
		if (StartingClass.gameMode.equals("halloween")) {
			if (centerX < 610)
				centerX += speed;
		} else if (centerX < 650)
			centerX += speed;
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

}
