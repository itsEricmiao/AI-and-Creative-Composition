import java.util.ArrayList;

//Markov process is a stochastic process whose future is independent of its past given its present state.
public class MarkovGenerator<E> {
	//Initialize three ArrayList
	ArrayList<E> data = new ArrayList<E>();	//This ArrayList contains all the elements in the midi file
	ArrayList<Integer> instances = new ArrayList<Integer>();	//This ArrayList contains occurrence of each element
	ArrayList<E> generatedMarkov = new ArrayList<E>(); //The notes we gonna generate based on the algorithm provided
	int[][] transitionTable; // The Transition Table
	
	double prob = 0.0;
	int size = 0;

	float mainProbs[][] = new float[1000][1000];
		
	MarkovGenerator() {}
	
//create a data ArrayList to store with all possible notes
	void markovCalcs(ArrayList<E> members, Integer order) 
	{
		
		for (int i = 0; i < members.size()-order+1; i++) 
		{
			
			if (data.contains(members.get(i))) 
			{
				//Case that  the member is already in the data ArrayList
				instances.set(data.indexOf(members.get(i)), (instances.get(data.indexOf(members.get(i))) + 1));
			} else 
			{
				//Case that the member isn't in the data ArrayList
				data.add(members.get(i));
				instances.add(1);
			}
		}	
		
//		System.out.println("Note: " + members);
//		System.out.println("instances: " + instances);
//		Above steps are fine
		
//Create transition table
		transitionTable = new int[data.size()][data.size()];
		int index = 0; 
		System.out.println("Data size = "+ data.size());
		System.out.println("Member size = "+ members.size());
		for (int i = 0; i < data.size(); i++) 
		{
			for (int j = 0;  j < data.size(); j++) 
			{
				for (int x = 0; x < members.size() - 1; x++) 
				{
					if (members.get(x) == data.get(i) && members.get(x+1) == data.get(j)) 
					{
						index++;
					}
				}
				transitionTable[i][j] = index;
				index = 0;
			}
		}
		float[][] probabilities = new float[data.size()][data.size()];
		
//calculate probabilities
		int lineTotal = 0; 
		for (int i = 0; i < data.size(); i++) 
		{
			for (int j = 0; j < data.size(); j++) 
			{
				lineTotal = lineTotal + transitionTable[i][j];
//				System.out.println("i = " + i + " j = " + j + " total = " + lineTotal);
//				System.out.println("i = " + i + " j = " + j + " total = " + lineTotal);
			}
			
			for (int j = 0; j < data.size(); j++) 
			{
				if (lineTotal != 0) 
				{
					probabilities[i][j] = ((float)transitionTable[i][j] / lineTotal);
					System.out.println(members.get(i) + " - " + members.get(j) + " Probabilities: [" + probabilities[i][j]+"]");
				}
			}
			System.out.println("");
			lineTotal = 0;
		}
		
		for (int i = 0; i < data.size(); i++) 
		{
			for (int j = 0; j < data.size(); j++) 
			{
				mainProbs[i][j] = probabilities[i][j];
			}
		}
	}

	void generateMarkov(E key) {
		prob = 0;
		double numMarkov = Math.random();
		
		if (data.contains(key)) {
			for (int j = 0; j < data.size(); j++) 
			{
				if (prob < numMarkov && numMarkov <= (prob + mainProbs[data.indexOf(key)][j])) 
				{
					generatedMarkov.add(data.get(j));
				}
				
				prob = prob + mainProbs[data.indexOf(key)][j];
			} 
		}
	}

	
	//For Unit test#1
	void printTable(String name)
	{
//		for (int i = 0; i < data.size(); i++) 
//		{
//			for (int j = 0;  j < data.size(); j++) 
//			{
//				System.out.println("Transition table " +name+":  " + transitionTable[i][j]);
//			}
//		}
	}
	
	
	//For Unit test#2
	void printProbabilities(String name)
	{
//		for (int i = 0; i < data.size(); i++) 
//		{
//
//			for (int j = 0; j < data.size(); j++) 
//			{
//				System.out.println("Probabilities " + name + " : " + mainProbs[i][j]);
//			}
//		}
	}
}
