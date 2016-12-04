
public class OR_Gate {
	private double[][][] data = {
			{{0, 0}, {0}},
			{{0, 1}, {1}},
			{{1, 0}, {1}},
			{{1, 1}, {1}}
			};
	private double[] percentErrorArray = {1, 1, 1, 1};
	public double weights[] = {Math.random(), Math.random()};
	public boolean error = true;

	public int or(int input1, int input2, double learningRate) {
		double percentError = 1;
		double weightedSum = 0;
		double output;
		
		int pass = 0;
		while (error) {
			if (percentErrorArray[0]  == 0 && percentErrorArray[1] == 0 && percentErrorArray[2] == 0 && percentErrorArray[3] == 0) error = false;
			else {
				for (int i = 0; i < data.length; i ++){
					for (int x = 0; x < weights.length; x++) {
						weightedSum += data[i][0][x] * weights[x];
					}
					output = (weightedSum > 1 ? 1 : 0);
					percentError = data[i][1][0] - output;
					percentErrorArray[i] = percentError;
					for (int x = 0; x < weights.length; x++) {
						weights[x] += (learningRate * percentError * data[i][0][x]);
					}
					weightedSum = 0;
					System.out.println("OR- Epoch#: " + pass + " Data#: " + i + " %Error: "+ percentError + " W1: " + weights[0] + " W2: " + weights[1]);
				}
				pass++;
			}
		}
		return input1*weights[0] + input2*weights[1] > 1 ? 1 : 0;
	}
}
