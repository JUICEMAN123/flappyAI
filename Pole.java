
public class Pole {

	public final static int POLEWIDTH = 40;
	public final static int POLEGAPcentered = 70;
	public final static int POLEDELAY = 0;

	private int x = FlappyFrame.WIDTH + 50;
	private Integer gapCenter = (int) (Math.random() * (FlappyFrame.HEIGHT - 200 - POLEGAPcentered) + 100);
	
	private int topY = getGapCenter() - POLEGAPcentered;
	private int botY = getGapCenter() + POLEGAPcentered;
	private int topHeight = topY;
	private int botHeight = FlappyFrame.HEIGHT - botY;

	public Pole() {

	}

	public int getX() {
		return x;
	}

	public int getGapCenter() {
		return gapCenter;
	}
	
	public int getTopY() {
		return topY;
	}

	public int getBotY() {
		return botY;
	}

	public int getTopHeight() {
		return topHeight;
	}

	public int getBotHeight() {
		return botHeight;
	}

	public boolean update() {
		
		x -= FlappyFrame.DELTASPEED;
		return x > (-POLEDELAY);
		
	}

}
