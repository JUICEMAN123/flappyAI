import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FlappyPanel extends JPanel implements Runnable {

	public static final Font font = new Font("monospace", Font.BOLD, 40);
	public static final int BIRDCOUNT = 1000;

	FlappyFrame frame;

	Bird[] birds = new Bird[BIRDCOUNT];
	Bird[] newBirds = new Bird[birds.length];

	Pole pole;

	public int score = -1;
	public int bestScore = 0;
	public int genTracker = 0;
	public int gen = 1;
	public int bestGen = 1;

	public FlappyPanel(FlappyFrame frame) {
		this.frame = frame;
		setup();
	}

	private void setup() {

		for (int i = 0; i < birds.length; i++) {

			birds[i] = new Bird(this);

		}

	}

	public void newGen() {

		score = 0;
		pole = null;
		genTracker = 0;
		gen++;

		for (int i = 0; i < FlappyAI.BIRDCARRY; i++) {

			newBirds[i].reset();

		}

		for (int i = FlappyAI.BIRDCARRY; i < newBirds.length - BIRDCOUNT * 0.1; i++) {

			newBirds[i] = generateBird(birds);

		}
		
		sortBirdsByBestScore();
		for (int i = (int) (newBirds.length - BIRDCOUNT * 0.1); i < newBirds.length; i++) {

			if(contains(newBirds, birds[i])) {
				
				newBirds[i] = generateBird(birds);
				
			}
			
			else {
			
				newBirds[i] = birds[i];
			
			}

		}

		birds = newBirds;
		
		newBirds = new Bird[birds.length];

	}

	public boolean contains(Bird[] bs, Bird b) {

		for (int i = 0; i < bs.length; i++) {

			if (birds[i] == b) {

				return true;

			}

		}

		return false;

	}

	public Bird generateBird(Bird[] basic) {

		Bird b = new Bird(this);
		b.ai = new FlappyAI(this, newBirds[0].ai);

		for (int i = 0; i < FlappyAI.BIRDCARRY / 3; i++) {

			b.ai.interpolate(newBirds[i].ai, i + 1);

		}

		return b;

	}

	public void addBirdToNextGen(Bird bird) {

		if (genTracker > BIRDCOUNT - FlappyAI.BIRDCARRY) {

			newBirds[BIRDCOUNT - genTracker] = bird;

		}

	}

	public void sortBirdsByBestScore() {

		for (int i = 0; i < birds.length; i++) {

			int s = -1;
			int bi = i;
			for (int j = i; j < birds.length; j++) {

				if (birds[j].bestScore > s) {

					s = birds[j].bestScore;
					bi = j;

				}

			}

			Bird temp = birds[i];
			birds[i] = birds[bi];
			birds[bi] = temp;

		}

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 830);

		for (Bird bird : birds) {

			if (bird.end) {

				continue;

			}

			g.setColor(bird.color);
			g.fillRect(bird.getX(), bird.getY(), Bird.RADIUS * 2, Bird.RADIUS * 2);

		}

		if (pole != null) {
			g.setColor(Color.GREEN);
			g.fillRect(pole.getX(), 0, Pole.POLEWIDTH, pole.getTopHeight());
			g.fillRect(pole.getX(), pole.getBotY(), Pole.POLEWIDTH, pole.getBotHeight());
		}

		g.setColor(Color.WHITE);
		g.setFont(font);
		String line1 = "Best: " + bestScore + " - " + bestGen;
		String line2 = "Now : " + score + " - " + gen;
		g.drawString(line1, 520, 75);
		g.drawString(line2, 520, 125);
		g.drawString(Integer.toString(genTracker), 520, 175);

	}

	@Override
	public void run() {

		int frameCount = 0;

		while (true) {

			for (int i = 0; i < birds.length; i++) {

				birds[i].update();

			}

			if (pole != null) {

				if (!pole.update()) {

					pole = null;

				}

			}

			else if (pole == null) {

				pole = new Pole();

				++score;

				for (int i = 0; i < birds.length; i++) {

					if (!birds[i].end) {

						birds[i].score = score;

					}

				}

			}

			if (score > bestScore) {

				bestScore = score;
				bestGen = gen;

			}

			if (genTracker == birds.length) {

				newGen();

			}

			// System.out.println(genTracker);

			repaint();

			try {
				Thread.sleep(FlappyFrame.DELTATIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			frameCount++;

		}

	}

}
