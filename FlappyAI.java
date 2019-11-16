import java.util.Iterator;

public class FlappyAI {

	public static final	int BIRDCARRY = (int)(0.5 * FlappyPanel.BIRDCOUNT);
	public static final	double VARIANCE = 0.5;
	
	AINode[] inputNodes = new AINode[2];
	AINode[][] hiddenNodes = new AINode[3][4];
	AINode[] outputNodes = new AINode[1];
	
	FlappyPanel fp;

	public FlappyAI(FlappyPanel fp) {

		this.fp = fp;
		setup();

	}

	public FlappyAI(FlappyPanel fp, FlappyAI aiO) {

		this.fp = fp;
		setup();
		copyAI(aiO);

	}
	
	public void setup() {

		for (int i = 0; i < inputNodes.length; i++) {

			inputNodes[i] = new AINode(0);

		}

		for (int l = 0; l < hiddenNodes.length; l++) {
			for (int i = 0; i < hiddenNodes[l].length; i++) {

				hiddenNodes[l][i] = new AINode((l == 0) ? inputNodes.length : hiddenNodes[l - 1].length);

			}
		}

		for (int i = 0; i < outputNodes.length; i++) {

			outputNodes[i] = new AINode(hiddenNodes[hiddenNodes.length - 1].length);

		}

	}

	public void interpolate(FlappyAI aiO, int weight) {

		for (int i = 0; i < hiddenNodes.length; i++) {
			
			for (int j = 0; j < hiddenNodes[i].length; j++) {

				double[] other = aiO.hiddenNodes[i][j].inWeights;
				double[] self = hiddenNodes[i][j].inWeights;
				
				for (int k = 0; k < other.length; k++) {
					
					self[k] += (other[k] / weight) * (Math.random() * 2 - 1);
					
				}
				
			}

		}

		for (int j = 0; j < outputNodes.length; j++) {

			double[] other = aiO.outputNodes[j].inWeights;
			double[] self = outputNodes[j].inWeights;
			
			for (int k = 0; k < other.length; k++) {
				
				self[k] += (other[k] / weight) * (Math.random() * 2 - 1);
				
			}
			
		}
		
	}
	
	private void copyAI(FlappyAI aiO) {
		
		for (int i = 0; i < hiddenNodes.length; i++) {
			
			for (int j = 0; j < hiddenNodes[i].length; j++) {

				double[] other = aiO.hiddenNodes[i][j].inWeights;
				double[] self = hiddenNodes[i][j].inWeights;
				
				for (int k = 0; k < other.length; k++) {
					
					self[k] = other[k];
					
				}
				
			}

		}

		for (int j = 0; j < outputNodes.length; j++) {

			double[] other = aiO.outputNodes[j].inWeights;
			double[] self = outputNodes[j].inWeights;
			
			for (int k = 0; k < other.length; k++) {
				
				self[k] = other[k];
				
			}
			
		}

	}

	public double compute() {

		for (int j = 0; j < hiddenNodes[0].length; j++) {

			hiddenNodes[0][j].activation(inputNodes);

		}

		for (int i = 1; i < hiddenNodes.length; i++) {

			for (int j = 0; j < hiddenNodes[i].length; j++) {

				hiddenNodes[i][j].activation(hiddenNodes[i - 1]);

			}

		}

		for (int k = 0; k < outputNodes.length; k++) {

			outputNodes[k].activation(hiddenNodes[hiddenNodes.length - 1]);

		}

		return outputNodes[0].value;

	}

	public void genNew(FlappyAI old) {

	}

}
