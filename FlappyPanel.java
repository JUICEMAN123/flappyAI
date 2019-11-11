import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class FlappyPanel extends JPanel implements Runnable {

	FlappyFrame frame;
	FlappyAI ai;

	Bird bird = new Bird(this);
	Pole pole;
	
	public boolean end = false;
	
	public int score = -1;

	public FlappyPanel(FlappyFrame frame, FlappyAI ai) {
		this.frame = frame;
		this.ai = ai;
		setup();
	}

	private void setup() {

		ai = new FlappyAI(this);
		
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 830);

		g.setColor(Color.ORANGE);
		g.fillRect(bird.getX(), bird.getY(), Bird.RADIUS * 2, Bird.RADIUS * 2);

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

			if(end) {
				
				break;
				
			}
			
			bird.update();

			if (pole != null) {
				
				if(bird.checkCollision(pole)) {
					
					end = true;
					
				}
				
				if(!pole.update()) {
					
					pole = null;
					
				}

			}

			else if (pole == null) {
				
				pole = new Pole();
				ai.score = ++score;

			}

			ai.update();
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
