
public class AINode {

	double bias;
	double[] inWeights;
	
	double value;
	
	public AINode(int inNodeCount) {

		inWeights = new double[inNodeCount];
		
		for (int i = 0; i < inWeights.length; i++) {
			
			inWeights[i] = Math.random();
			
		}
		
		bias = Math.random();
		
	}
	
	public void activation(AINode[] prevLayer) {
		
		for (int i = 0; i < prevLayer.length; i++) {

			value += prevLayer[i].value * inWeights[i];
			
		}
		
		value += bias;
		
		value = (Math.tanh(value) + 1) / 2;
		
	}
	
	public void change() {
		
		
		
	}
	
}
