package bloom;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class StartingClass extends Applet implements Runnable, KeyListener {

	private int period = 0;
	private static int frequency = 80;
	private Korpa korpa;
	private ArrayList<Item> items;
	private URL base;
	private Graphics second;
	private Image image, character, item1, item2, item3, item4, item5, spash,
			mainBg, startBg, endBg;
	private AudioClip audio, audioInGood, audioInBad, audioSplash;
	private Font font;
	private int scale = 500;
	private String gameMode = "standard";
	private boolean lost = false;

	@Override
	public void init() {
		setSize(800, 500);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Bloom");
		frame.setLocation(100, 100);
		frame.setResizable(false);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
		}

		setImagesAndAudio(gameMode);

		try {
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					new FileInputStream(gameMode+"/font.ttf"));
			font = font.deriveFont(20.0F);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void start() {
		korpa = new Korpa();
		items = new ArrayList<Item>();
		audio.loop();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (true && !lost) {
			korpa.update();

			if (period % frequency == 0) {
				Item newItem;
				Random rand = new Random();
				int prob = rand.nextInt(10);

				if (prob < 3)
					newItem = new BadItem();
				else
					newItem = new GoodItem();

				items.add(newItem);
			}

			for (Item i : items) {
				i.update();
			}

			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).r.intersects(korpa.r)) {
					Stats.updatePoints(items.get(i));
					if (items.get(i) instanceof GoodItem) {
						audioInGood.play();
					} else
						audioInBad.play();
					items.remove(i);
				} else if (items.get(i).r.y > 420) {
					if (items.get(i) instanceof GoodItem) {
						Stats.setLives();
					}
					audioSplash.play();
					items.remove(i);
				}
			}

			if (Stats.getLives() == 0)
				lost = true;

			if (Stats.getTotalPoints() >= scale) {
				if (frequency - 30 <= 0)
					frequency = 30;
				frequency -= 10;

				Korpa.speed += 5;
				if (Korpa.speed >= 40)
					Korpa.speed = 40;

				Item.speedY += 2;
				if (Item.speedY >= 10)
					Item.speedY = 10;

				Stats.level += 1;
				if (Stats.level >= 5)
					Stats.level = 5;
				scale += 500;
			}

			repaint();
			period++;

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@SuppressWarnings("static-access")
	@Override
	public void paint(Graphics g) {
		if (!lost) {
			g.drawImage(mainBg, 0, 0, this);

			g.setFont(font);
			g.setColor(Color.BLUE);
			g.drawString("Score: " + Stats.getTotalPoints(), 5, 20);
			g.drawString("Lives: " + Stats.getLives(), 5, 40);
			g.drawString("Level: " + Stats.level, 5, 60);

			for (Item i : items) {
				if (i.centerY >= 420) {
					g.drawImage(spash, i.centerX, i.centerY, this);
				} else if (i.getVid().equals(Type.Item1)) {
					g.drawImage(item1, i.centerX, i.centerY, this);
				}

				else if (i.getVid().equals(Type.Item2)) {
					g.drawImage(item2, i.centerX, i.centerY, this);
				}

				else if (i.getVid().equals(Type.Item3)) {
					g.drawImage(item3, i.centerX, i.centerY, this);
				}

				else if (i.getVid().equals(Type.Item4)) {
					g.drawImage(item4, i.centerX, i.centerY, this);
				}

				else if (i.getVid().equals(Type.Item5)) {
					g.drawImage(item5, i.centerX, i.centerY, this);
				}
			}

			g.drawImage(character, korpa.getCenterX(), korpa.getCenterY(), this);
		} else {
			g.setFont(font.deriveFont(50.0F));
			g.drawImage(endBg, 0, 0, this);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 240, 220);

			g.setFont(font.deriveFont(23.0F));
			g.drawString("Your Score is " + Stats.getTotalPoints(), 240, 260);
			audio.stop();
		}
	}

	public void setImagesAndAudio(String gameMode) {
		character = getImage(base, gameMode + "/character.png");
		mainBg = getImage(base, gameMode + "/mainBg.png");
		item1 = getImage(base, gameMode + "/item1.png");
		item2 = getImage(base, gameMode + "/item2.png");
		item3 = getImage(base, gameMode + "/item3.png");
		item4 = getImage(base, gameMode + "/item4.png");
		item5 = getImage(base, gameMode + "/item5.png");
		spash = getImage(base, gameMode + "/spash.png");
		startBg = getImage(base, gameMode + "/startBg.png");
		endBg = getImage(base, gameMode + "/endBg.png");
		audio = getAudioClip(base, gameMode + "/audio.wav");
		audioInGood = getAudioClip(base, gameMode + "/audioInGood.wav");
		audioInBad = getAudioClip(base, gameMode + "/audioInBad.wav");
		audioSplash = getAudioClip(base, gameMode + "/audioSplash.wav");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			korpa.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			korpa.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
