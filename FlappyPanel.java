import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class FlappyPanel extends JPanel implements Runnable {

	FlappyFrame frame;

	Bird[] birds = new Bird[10];
	Pole pole;

	public int score = -1;

	public FlappyPanel(FlappyFrame frame, FlappyAI ai) {
		this.frame = frame;
		setup();
	}

	private void setup() {

		for (int i = 0; i < birds.length; i++) {

			birds[i] = new Bird(this);

		}

	}
	
	public void newGen() {
		
		Bird[] newBirds = new Bird[10];
		
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 830);

		


		for(Bird bird : birds) {
			
			if(bird.end) {
				
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

					if(!birds[i].end) {
						
						birds[i].score = score;
						
					}

				}

			}
			
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
