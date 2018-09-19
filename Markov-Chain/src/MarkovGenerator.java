import java.util.ArrayList;

//Markov process is a stochastic process whose future is independent of its past given its present state.
public class MarkovGenerator<E> {
	ArrayList<E> alphabet = new ArrayList<E>();
	ArrayList<Integer> instances = new ArrayList<Integer>();
	ArrayList<E> generatedMarkov = new ArrayList<E>();

	double prob = 0.0;
	int size = 0;
	
	float mainProbs[][] = new float[100][100];
		
	MarkovGenerator() {}
	
	void markovCalcs(ArrayList<E> elements) {
		//create an alphabet with all possible notes
		for (int i = 0; i < elements.size(); i++) {
			if (alphabet.contains(elements.get(i))) {
				//System.out.println("Element containes in alphabet");
				instances.set(alphabet.indexOf(elements.get(i)), (instances.get(alphabet.indexOf(elements.get(i))) + 1));
			} else {
				//System.out.println("Element adds to alphabet");
				alphabet.add(elements.get(i));
				instances.add(1);
			}
		}
		
		System.out.println("alphabet: " + alphabet);
		System.out.println("instances: " + instances);
		
//Create transition table
		int[][] transitionTable = new int[alphabet.size()][alphabet.size()];
		int number = 0; 
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0;  j < alphabet.size(); j++) {
				for (int a = 0; a < elements.size() - 1; a++) {
					if (elements.get(a) == alphabet.get(i) && elements.get(a+1) == alphabet.get(j)) {
						number++;
					} else {
					}
				}
				transitionTable[i][j] = number;
				number = 0;
			}
		}
		
//		for (int i = 0; i < alphabet.size(); i++) {
//			for (int j = 0;  j < alphabet.size(); j++) {
//				System.out.println("Transition table:  " + transitionTable[i][j]);
//			}
//		}
	
		float[][] probabilities = new float[alphabet.size()][alphabet.size()];
		
//calculate probabilities
		int lineTotal = 0; 
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0; j < alphabet.size(); j++) {
				lineTotal = lineTotal + transitionTable[i][j];
				//System.out.println("i = " + i + " j = " + j + " total = " + lineTotal);
			}
			for (int j = 0; j < alphabet.size(); j++) {
				if (lineTotal == 0) {
				} else {
					probabilities[i][j] = ((float)transitionTable[i][j] / lineTotal);
					System.out.println("i = " + i + " j = " + j + " Probabilities " + probabilities[i][j]);
				}
			}
			lineTotal = 0;
		}
		
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0; j < alphabet.size(); j++) {
				mainProbs[i][j] = probabilities[i][j];
			}
		}
		
		for (int i = 0; i < alphabet.size(); i++) {
			for (int j = 0; j < alphabet.size(); j++) {
				System.out.println("Probabilities: " + mainProbs[i][j]);
			}
		}
	}

	void generateMarkov(E seed) {
		prob = 0;
		double numMarkov = Math.random();
		
		if (alphabet.contains(seed)) {
			for (int j = 0; j < alphabet.size(); j++) {
				if (prob < numMarkov && numMarkov <= (prob + mainProbs[alphabet.indexOf(seed)][j])) {
					generatedMarkov.add(alphabet.get(j));
				} else {}
				prob = prob + mainProbs[alphabet.indexOf(seed)][j];
			}
		}
	}
}
