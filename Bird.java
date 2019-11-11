public class Bird {

	public final static int RADIUS = 15;

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

		this.fp = fp;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void jump() {

		// y -= 5 * FlappyFrame.DELTATIME;
		jumpMomentum = jumpHeight;

	}

	public boolean checkCollision(Pole pole) {
		
		if(pole.getX() < x + RADIUS * 2 && pole.getX() + Pole.POLEWIDTH > x) {
			
			if(y <= pole.getTopY() || y + RADIUS * 2 >= pole.getBotY()) {
				
				return true;
				
			}
						
		}
		
		return false;
		
	}
	
	public boolean isAlive() {

		return y < FlappyFrame.HEIGHT && y > 0;

	}

	public void update() {

		if (isAlive()) {

			y += (FlappyFrame.DELTASPEED * fallSpeed)
					- ((jumpMomentum >= 2) ? (jumpMomentum -= jumpGravity * FlappyFrame.DELTATIME / jumpDivider) / jumpWidth
							: 0) * FlappyFrame.DELTATIME / jumpDivider;

		}

		else {

			y = FlappyFrame.HEIGHT / 2;
			fp.end = true;

		}

	}

}
