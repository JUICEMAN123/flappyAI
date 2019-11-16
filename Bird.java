import java.awt.Color;

public class Bird {

	public final static int RADIUS = 15;

	private static int jumpHeight = 25 * (800/FlappyFrame.WIDTH);
	private static double jumpGravity = 0.2 / (800/FlappyFrame.WIDTH);
	
	private static double fallSpeed = 1.5 * (800/FlappyFrame.WIDTH);
	private static double globalGravity = 0.01 * (800/FlappyFrame.WIDTH);
	
	FlappyPanel fp;
	FlappyAI ai;
	
	Color color;
	int score;
	int bestScore;

	public boolean end = false;

	private int x = 150;
	private int y = 400;

	private int jumpMomentum = 0;

	public Bird(FlappyPanel fp) {

		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		this.fp = fp;
		this.ai = new FlappyAI(fp);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void jump() {

		// y -= 5 * FlappyFrame.DELTATIME;
		//System.out.println("fff");
		jumpMomentum = jumpHeight;
		fallSpeed = 1.5 * (800/FlappyFrame.WIDTH);

	}

	public boolean checkCollision(Pole pole) {

		if (pole.getX() < x + RADIUS * 2 && pole.getX() + Pole.POLEWIDTH > x) {

			if (y <= pole.getTopY() || y + RADIUS * 2 >= pole.getBotY()) {

				return true;

			}

		}

		return false;

	}

	public boolean isAlive() {

		return (y < FlappyFrame.HEIGHT) && (y > 0) && ((fp.pole != null) ? !checkCollision(fp.pole) : true);

	}

	public void update() {

		//System.out.println(y + " " + (y > 0) + fp.genTracker);
		
		if (end) {

			return;

		}
		
		if (isAlive()) {

			y += (FlappyFrame.DELTASPEED * fallSpeed) - ((jumpMomentum >= 2) ? (jumpMomentum -= jumpGravity * FlappyFrame.DELTATIME / jumpMomentum) : 0);
			fallSpeed += globalGravity;
			
		}

		else {

			y = FlappyFrame.HEIGHT / 2;
			end = true;
			fp.genTracker++;
			fp.addBirdToNextGen(this);

		}

		if (fp.pole != null) {
			
			updateAI();
			
		}
		
		if(ai.compute() > 0.95) {
			
			jump();
			
		}
		
	}

	public void updateAI() {

		ai.inputNodes[0].value = getY() - fp.pole.getGapCenter();
		ai.inputNodes[1].value = getX() - fp.pole.getX();

	}
	
	public void reset() {
		
		if(score > bestScore) {
			
			bestScore = score;
			
		}
		
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		score = 0;

		end = false;

		x = 150;
		y = 400;

		jumpMomentum = 0;
		
	}

}
