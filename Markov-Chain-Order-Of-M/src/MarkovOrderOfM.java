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
	
	//For the generateNote function, user provide the init ArrayList and the function will return the predicted next note
	E generateNote(ArrayList<E> key)
	{
		float chain[] = new float [data.size()];
		
		int index;
		int val = 0;
		//check if alphabet arrayList contains the key 
		if(alphabet.contains(key))
		{
			//Index has the index of the key from the alphabet arraylist
			index = alphabet.indexOf(key);
			double randomNum = Math.random();
			chain[0] = 0;
			
			//Make the chain array
			for(int i = 1; i < data.size(); i++)
			{
				chain[i] = chain[i-1] + probabilitiesTable[index][i-1];
			}
			//Find the area in the chain array
			for(int left = 0; left < data.size()-1; left++)
			{
				if(chain[left] < randomNum && chain[left+1] > randomNum)
				{
					val = left;
				}
			}
		}
		else if(!alphabet.contains(key))
		{
			val = 2;
		}
		return data.get(val);
	}
	
	//For the generate function, user provide the init ArrayList and the size of output array
	ArrayList<E> generate(ArrayList<E>init, int size, int order)
	{
		//outputArr stores all the generated melodies
		ArrayList<E> outputArr = new ArrayList<E>();
		outputArr.addAll(init);
		E nextNote;
		//The function will generate until it reaches the size we want
		while(outputArr.size() < size)
		{
			//trainArr stores all the melodies (size of M) that are ready to use for generation
			ArrayList<E> trainArr = new ArrayList<E>();
			//We use the last M number of outputArr to generate the next melodies 
			for(int i = outputArr.size()-order; i < outputArr.size(); i++)
			{
				trainArr.add(outputArr.get(i));
			}
				nextNote = generateNote(trainArr);
				outputArr.add(nextNote);
				//Clean the trainArr each time we generate a new melody
				trainArr.clear();
		}
		return outputArr;
	}
}


