import java.util.ArrayList;
import java.util.List;

//Markov process is a stochastic process whose future is independent of its past given its present state.
public class MarkovOrderOfM<E> {
	//Initialize three ArrayList
	ArrayList<ArrayList<E>> alphabet = new ArrayList<ArrayList<E>>();	//This ArrayList contains each unique order of M elements in the midi file
	ArrayList<E> data = new ArrayList<E>(); //This ArrayList contains each unique note (size of 1) in the midi file
	ArrayList<Integer> instances = new ArrayList<Integer>();	//This ArrayList contains occurrence of each element
	ArrayList<E> generatedMarkov = new ArrayList<E>(); //The notes we gonna generate based on the algorithm provided
	int[][] transitionTable; // The Transition Table
	float[][] probabilitiesTable; // The Transition Table
	
		
	MarkovOrderOfM() {}
	

	void train(ArrayList<E> members, Integer order) 
	{
		//Create the alphabet array
		for (int i = 0; i < members.size()-order; i++) {
			ArrayList<E>temp = new ArrayList<E>(members.subList(i, i+order));
			if(alphabet.contains(temp))
			{

			}
			else 
			{
				alphabet.add(temp);
			}
		}
		
		//Create the data array
		for (int i = 0; i < members.size(); i++) {
			while(data.contains(members.get(i)) != true)
			{
				data.add(members.get(i));
			}
		}
		
		
//Create transition table
		
		transitionTable = new int[data.size()][alphabet.size()];
		
		for(int i = 0; i < members.size()-order; i++)
		{
			ArrayList<E> tempRow = new ArrayList<E>(members.subList(i, i+order));
			
			int col = alphabet.indexOf(tempRow);
			int row = data.indexOf(members.get(i+order));

			if(row != -1 && col != -1)
			{
				int val = transitionTable[row][col];
				val++;
				transitionTable[row][col] = val;
			}
		}
		
//calculate probabilities
		probabilitiesTable = new float[data.size()][alphabet.size()];
		
		
		for(int x = 0; x < alphabet.size(); x ++)
		{
			int lineTotal = 0;
			for(int y = 0; y < data.size(); y++)	
			{
				lineTotal = lineTotal + transitionTable[y][x];
				
			}
			for(int m = 0; m < data.size(); m++)
			{
				probabilitiesTable[m][x] = (float)transitionTable[m][x]/lineTotal;
			}
		}
		
	}
	
	void printTransitionTable()
	{
		for(int i = 0; i < alphabet.size(); i++)
		{
			for (int j = 0; j < data.size(); j++)
			{
				System.out.println(alphabet.get(i)+" -> ["+data.get(j)+"]          Instance = "+transitionTable[j][i]);
			}
				
		}
	}
	
	
	void printProbabilitiesTable()
	{
		for(int i = 0; i < alphabet.size(); i++)
		{
			for (int j = 0; j < data.size(); j++)
			{
				System.out.println(alphabet.get(i)+" -> ["+data.get(j)+"]          Probabilities = "+probabilitiesTable[j][i]);
			}
				
		}
	}
	
}
//	void generateMarkov(E key) {
//		prob = 0;
//		double numMarkov = Math.random();
//		
//		if (data.contains(key)) {
//			for (int j = 0; j < data.size(); j++) 
//			{
//				if (prob < numMarkov && numMarkov <= (prob + mainProbs[data.indexOf(key)][j])) 
//				{
//					generatedMarkov.add(data.get(j));
//				}
//				
//				prob = prob + mainProbs[data.indexOf(key)][j];
//			} 
//		}
//	}


