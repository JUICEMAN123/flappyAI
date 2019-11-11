
public class FlappyAI {

	AINode[] inputNodes = new AINode[3];
	AINode[][] hiddenNodes = new AINode[1][4];
	AINode[] outputNodes = new AINode[1];

	
	int[] rawData;
	double[] data = new double[3];
	double[][] hiddenLayers = new double[2][data.length];
	double output = 0.5;
	
	FlappyPanel fp;

	public FlappyAI(FlappyPanel fp) {
		
		this.fp = fp;
		update();
		
	}
	
	public void update() {
		
		rawData[1] = fp.pole.getX();
		rawData[2] = fp.bird.getY();

	}
	
	public void compute() {
		
		
		
	}
	
}
