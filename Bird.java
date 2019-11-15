import java.awt.Color;

public class Bird {

	public final static int RADIUS = 15;

	Color color;
	int score;
	
	FlappyAI ai;
	public boolean end = false;

	private int x = 150;
	private int y = 400;

	private int jumpMomentum = 0;
	private int jumpHeight = 60;
	private double jumpGravity = 0.1;
	private double jumpWidth = 0.1;
	private int jumpDivider = 650;

	private double fallSpeed = 1.5;

	FlappyPanel fp;

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

		return y < FlappyFrame.HEIGHT && y > 0 && (fp.pole != null) ? !checkCollision(fp.pole) : true;

	}

	public void update() {

		if (end) {

			return;

		}
		
		if (isAlive()) {

			y += (FlappyFrame.DELTASPEED * fallSpeed) - ((jumpMomentum >= 2)
					? (jumpMomentum -= jumpGravity * FlappyFrame.DELTATIME / jumpDivider) / jumpWidth
					: 0) * FlappyFrame.DELTATIME / jumpDivider;

		}

		else {

			y = FlappyFrame.HEIGHT / 2;
			end = true;

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
		
		
		
	}

}
