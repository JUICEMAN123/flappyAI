import java.util.Iterator;

public class FlappyAI {

	AINode[] inputNodes = new AINode[3];
	AINode[][] hiddenNodes = new AINode[1][4];
	AINode[] outputNodes = new AINode[1];

	double output = 0.5;

	int score;

	FlappyPanel fp;

	public FlappyAI(FlappyPanel fp) {

		this.fp = fp;

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

	public void update() {

		inputNodes[0].value = fp.pole.getGapCenter();
		inputNodes[1].value = fp.pole.getX();
		inputNodes[2].value = fp.bird.getY();

	}

	public void compute() {

	}

}
