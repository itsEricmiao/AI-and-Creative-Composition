import java.util.ArrayList;
import java.util.List;

//Markov process is a stochastic process whose future is independent of its past given its present state.
public class MarkovOrderOfM<E> 
{
	//Initialize three ArrayList
	ArrayList<ArrayList<E>> alphabet = new ArrayList<ArrayList<E>>();	//This ArrayList contains each unique order of M elements in the midi file
	ArrayList<E> data = new ArrayList<E>(); //This ArrayList contains each unique note (size of 1) in the midi file
	ArrayList<Integer> instances = new ArrayList<Integer>();	//This ArrayList contains occurrence of each element
	ArrayList<E> generatedMarkov = new ArrayList<E>(); //The notes we gonna generate based on the algorithm provided
	
	int[][] transitionTable; // The Transition Table
	float[][] probabilitiesTable; // The Transition Table
	
	//MarkovOrderOfM() {}
	
	void train(ArrayList<E> members, Integer order) 
	{
//Create the alphabet array
		for (int i = 0; i < members.size()-order; i++) {
			ArrayList<E>temp = new ArrayList<E>(members.subList(i, i+order));
			if(!alphabet.contains(temp))
			{
				alphabet.add(temp);
			}
		}
		
//Creating the data array
		for (int i = 0; i < members.size(); i++) 
		{
			while(data.contains(members.get(i)) != true)
			{
				data.add(members.get(i));
			}
		}
		
		
//Creating transition table
		transitionTable = new int[alphabet.size()][data.size()];
		for(int i = 0; i < members.size()-order; i++)
		{
			ArrayList<E> tempRow = new ArrayList<E>(members.subList(i, i+order));
			int row = alphabet.indexOf(tempRow);
			int col = data.indexOf(members.get(i+order));
			int val = transitionTable[row][col];
			val++;
			transitionTable[row][col] = val;
		}
		
//calculate probabilities
		probabilitiesTable = new float[alphabet.size()][data.size()];
		for(int x = 0; x < alphabet.size(); x ++)
		{
			int lineTotal = 0;
			for(int y = 0; y < data.size(); y++)	
			{
				lineTotal = lineTotal + transitionTable[x][y];
			}
			//System.out.println("LineTotal = "+lineTotal);
			for(int m = 0; m < data.size(); m++)
			{
				probabilitiesTable[x][m] = (float)transitionTable[x][m]/lineTotal;
			}
		}
	}
	
	void printTransitionTable(int order)
	{
		for(int i = 0; i < order; i ++)
		{
			System.out.print("    ");
		}
		
		for(int i = 0; i < data.size(); i++)
		{
			System.out.print("       "+data.get(i));
		}
		System.out.println("");
		for(int i = 0; i < alphabet.size(); i++)
		{
			
			System.out.print(alphabet.get(i));
			for (int j = 0; j < data.size(); j++)
			{
				System.out.print("       " + transitionTable[i][j]);
			}
			System.out.println("");
		}
	}
	
	
	void printProbabilitiesTable(int order)
	{
		for(int i = 0; i < order; i ++)
		{
			System.out.print("     ");
		}
		
		for(int i = 0; i < data.size(); i++)
		{
			System.out.print("      ["+data.get(i)+"]");
		}
		
		System.out.println("");
		
		for(int i = 0; i < alphabet.size(); i++)
		{
			System.out.print(alphabet.get(i));
			for (int j = 0; j < data.size(); j++)
			{
				System.out.print("       " + probabilitiesTable[i][j]);
			}
			System.out.println("");
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


