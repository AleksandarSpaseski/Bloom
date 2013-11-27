package bloom;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	//Ovde pocnuva the movie of the year n-tiot grad.

	private int period = 0;
	private static int frequency = 80;
	private Korpa korpa;
	private ArrayList<Fruit> ovosja;

	private URL base;
	private Graphics second;
	private Image image, character, background, ananas, apple, banana, brokula,
			cherry, garlic, onion, orange, pear, spash, crno;

	private AudioClip audio, audio2, audio3, audio4;
	private Font font;
	private int scale = 500;
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

		character = getImage(base, "data/characterNew.png");
		background = getImage(base, "data/background.png");
		ananas = getImage(base, "data/ananas.png");
		apple = getImage(base, "data/apple.png");
		banana = getImage(base, "data/banana.png");
		brokula = getImage(base, "data/brokula.png");
		cherry = getImage(base, "data/cherry.png");
		garlic = getImage(base, "data/garlic.png");
		onion = getImage(base, "data/onion.png");
		orange = getImage(base, "data/orange.png");
		pear = getImage(base, "data/pear.png");
		spash = getImage(base, "data/spash.png");
		crno = getImage(base, "data/crno_pa_se_ne_vidi.png");

		audio = getAudioClip(base, "data/backgroundMusic.wav");
		audio2 = getAudioClip(base, "data/audio3.wav");
		audio3 = getAudioClip(base, "data/audio2.wav");
		audio4 = getAudioClip(base, "data/audio4.wav");

		try {
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					new FileInputStream("data/font.ttf"));
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
		ovosja = new ArrayList<Fruit>();
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

	@Override
	public void run() {
		while (true && !lost) {
			korpa.update();

			if (period % frequency == 0) {

				Fruit novoOvosje;

				Random rand = new Random();
				int prob = rand.nextInt(10);
				if (prob < 3)
					novoOvosje = new Vegetable();
				else
					novoOvosje = new TastyFruit();

				ovosja.add(novoOvosje);
			}

			for (Fruit f : ovosja) {
				f.update();
			}

			for (int i = 0; i < ovosja.size(); i++) {
				if (ovosja.get(i).r.intersects(korpa.r)) {
					Stats.updatePoints(ovosja.get(i));
					if (ovosja.get(i) instanceof TastyFruit) {
						audio2.play();
					} else
						audio3.play();
					ovosja.remove(i);
				} else if (ovosja.get(i).r.y > 420) {
					if (ovosja.get(i) instanceof TastyFruit) {
						Stats.setLives();
					}
					audio4.play();
					ovosja.remove(i);
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

				Fruit.speedY += 2;
				if (Fruit.speedY >= 10)
					Fruit.speedY = 10;

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

	@Override
	public void paint(Graphics g) {
		if (!lost) {
			g.drawImage(background, 0, 0, this);

			g.setFont(font);
			g.setColor(Color.BLUE);
			g.drawString("Score: " + Stats.getTotalPoints(), 5, 20);
			g.drawString("Lives: " + Stats.getLives(), 5, 40);
			g.drawString("Level: " + Stats.level, 5, 60);

			for (Fruit f : ovosja) {
				if (f.centerY >= 420) {
					g.drawImage(spash, f.centerX, f.centerY, this);
				} else if (f.getVid().equals(Vid.ANANAS)) {
					g.drawImage(ananas, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.BANANA)) {
					g.drawImage(banana, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.BROKULA)) {
					g.drawImage(brokula, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.CRESHA)) {
					g.drawImage(cherry, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.JABOLKO)) {
					g.drawImage(apple, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.KROMID)) {
					g.drawImage(onion, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.KRUSHA)) {
					g.drawImage(pear, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.LUK)) {
					g.drawImage(garlic, f.centerX, f.centerY, this);
				}

				else if (f.getVid().equals(Vid.PORTOKAL)) {
					g.drawImage(orange, f.centerX, f.centerY, this);
				}
			}

			g.drawImage(character, korpa.getCenterX(), korpa.getCenterY(), this);
		} else {
			g.setFont(font.deriveFont(50.0F));
			g.drawImage(crno, 0, 0, this);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 240, 220);

			g.setFont(font.deriveFont(30.0F));
			g.drawString("Your Score is " + Stats.getTotalPoints(), 240, 260);
			audio.stop();
		}
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
