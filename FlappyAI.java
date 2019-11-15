import java.util.Iterator;

public class FlappyAI {

	AINode[] inputNodes = new AINode[2];
	AINode[][] hiddenNodes = new AINode[1][4];
	AINode[] outputNodes = new AINode[1];

	FlappyPanel fp;

	public FlappyAI(FlappyPanel fp) {

		this.fp = fp;
		setup();

	}
	
	public void setup() {
		
		for (int i = 0; i < inputNodes.length; i++) {

			inputNodes[i] = new AINode(0);

		}

		for (int l = 0; l < hiddenNodes.length; l++) {
			for (int i = 0; i < hiddenNodes[l].length; i++) {

				hiddenNodes[l][i] = new AINode(inputNodes.length);

			}
		}
		
		for (int i = 0; i < outputNodes.length; i++) {

			outputNodes[i] = new AINode(hiddenNodes[hiddenNodes.length - 1].length);

		}
		
	}

	public double compute() {

		for (int j = 0; j < hiddenNodes[0].length; j++) {
			
			hiddenNodes[0][j].activation(inputNodes);
			
		}
		
		for (int i = 1; i < hiddenNodes.length; i++) {
			
			for (int j = 0; j < hiddenNodes[i].length; j++) {
				
				hiddenNodes[i][j].activation(hiddenNodes[i-1]);
				
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
