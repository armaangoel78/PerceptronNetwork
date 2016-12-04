import java.util.Scanner;

public class XOR_Gate {
	private static AND_Gate and = new AND_Gate();
	private static OR_Gate or = new OR_Gate();

	private static double[] percentErrorArray = {1, 1, 1};
	private static double learningRate;
	private static double weights[] = {Math.random(), Math.random()};
	
	private static double[][][] data = {
			{{0, 0}, {0}},
			{{1, 0}, {1}},
			{{1, 1}, {0}}
			};
	
	public static void main(String[] args) {
		boolean error = true;
		while (true) {
			Scanner s = new Scanner(System.in);
			
			if (or.error == true && and.error == true && error == true) {
				System.out.println("Enter Learning Rate: ");
				learningRate = s.nextDouble();
			}
			
			System.out.print("Enter inputs: ");
			int input1 = s.nextInt(), input2 = s.nextInt();
			
			long startTime = System.currentTimeMillis();
			
			if (or.error == true) System.out.println("\nTraininng OR");
			int outputOR = or.or(input1, input2, learningRate);
			
			if (and.error == true) System.out.println("\nTraining AND");
			int outputAND = and.and(input1, input2, learningRate);
			
			if (error == true) System.out.println("\nTraining XOR");		
	
			double percentError = 1;
			double weightedSum = 0;
			double output;
				
			int pass = 0;
			
			while (error) {
				if (percentErrorArray[0]  == 0 && percentErrorArray[1] == 0 && percentErrorArray[2] == 0) error = false;
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
						System.out.println("XOR- Epoch#: " + pass + " Data#: " + i + " %Error: "+ percentError + " W1: " + weights[0] + " W2: " + weights[1]);
					}
					pass++;
				}
			}
			
			System.out.println("\nTraining time: " + (System.currentTimeMillis() - startTime) + " Milliseconds");
			
			System.out.println("\nFinal OR w1: " + or.weights[0] + " w2: " + or.weights[1]);
			System.out.println("Final AND w1: " + and.weights[0] + " w2: " + and.weights[1]);
			System.out.println("Final XOR w1: " + weights[0] + " w2: " + weights[1]);

			
			System.out.println("\nOutput OR: " + outputOR);
			System.out.println("Output AND: " + outputAND);
			System.out.println(outputOR * weights[0] + outputAND * weights[1] > 1? "Output XOR: " + 1 : "Output XOR: " + 0);
			System.out.println("\n--------------------------------------------------------\n");
		}	
	}
}
