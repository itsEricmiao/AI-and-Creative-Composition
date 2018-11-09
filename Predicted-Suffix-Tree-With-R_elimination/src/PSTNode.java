import java.util.*;
import java.lang.Object;


public class PSTNode<E> {

	private ArrayList<PSTNode<E>> children = new ArrayList<PSTNode<E>>();	//The children of the node
	private ArrayList<E> word; // The value of node
	private ArrayList<E> xVal; //The value of x in r-elimination
	
	private int length;//order of the PST tree
	private int index = 0;	//This is the index of next node for isSuffix() and isFound() ----> it is useless at this point but i will combine it into my functions next week
	private int countOfNode = 0;
	private double probOfNode = 1;
	private double rValOfNode = 1;
	private int dataSize = 0;	

	
	//Constructors
	PSTNode() {}
	
	PSTNode(int order) 
	{
		length = order;
	}

	PSTNode(ArrayList<E> input) 
	{
		word = input;
	}
	
	void setDataSize(int size)
	{
		dataSize = size;
	}

	
	//Getter for getting the value of the node
	ArrayList<E> getWord(int index)
	{
		return children.get(index).word;
	}

	//Getter for getting a specific node from the children arrayList
	PSTNode<E> getNode(int index)
	{
		return children.get(index);
	}

	//Mainly for parsing files, this function will take the entie input data set and add each element from size of 1-length to the tree
	 void addToTree(ArrayList<E> input)	//addNode function
	{
		//Get the word from size of 1 to size of length
		for(int i = 1; i <= length; i++)
		{
			//Get the word from first index to the last-i index
			for(int j = 0; j <= input.size()-i; j++)
			{
				//create the temp arraylist to store the element we just get
				ArrayList<E> temp = new ArrayList<E>(input.subList(j, j+i));
				//using the temp to create a new temp node
				PSTNode<E> newNode = new PSTNode<E>(temp);
				
				//Following function create the level 1 nodes: abrdc
				if(newNode.word.size() == 1 && isLevel1Node(newNode) == true)
				{
					//If size == 1 (abcdr). Add to the this.children array
					children.add(newNode); 
				} 
			
				//if the size > 1, we test if it isSuffix and add to the tree
				else if(newNode.word.size() > 1)
				{
					isSuffix(newNode.word, this, 1);
				}
			}
		}
	}
	
	//ifFound check if the input node value already exist in the tree, if not, we add it to the branch.
	boolean ifFound(PSTNode<E> node, int index, ArrayList<E> val)
	{
		PSTNode<E> newNode = node.getNode(index);
		
		for(int i = 0; i < newNode.children.size(); i++)
		{
			
			//If we find a same word from the children arraylist, we add 1 to the count of this node and return true
			if(newNode.getWord(i).equals(val))
			{
				addCount(newNode.getNode(i));
				return true;
			}
		}
		//After node pass all those condition showing above, we move on.
		addNote(newNode,val);
		return false;
	}
	
	
	//This function is for level 1 of the PST tree (size of the node == 1)
		boolean isLevel1Node(PSTNode<E> level1Node)
		{
			for (int i = 0; i < children.size(); i ++)
			{
				if(children.get(i).word.equals(level1Node.word) == true)
				{
					//Add count to level 1 note that are already exist
					addCount(children.get(i));
					return false;
				}
			}
			addCount(level1Node);
			return true;
		}
	
		
	//Add node to the branch
	void addNote(PSTNode<E> node, ArrayList<E> val)
	{
		PSTNode<E> newChild = new PSTNode<E>(val);
		addCount(newChild);
		node.children.add(newChild);
	}

	
	void addCount(PSTNode<E> node)
	{
		node.countOfNode = node.countOfNode + 1;
		//System.out.println("The node is " + node.word + " The count is "+ node.countOfNode);
	}
	
	
	//Print function for testing
	void print(int time, double pmin, double rmin)	
	{
		
		for(int i = 0; i < children.size(); i++)
		{
			if( children.get(i).probOfNode < pmin)
			{
				PSTNode<E> temp = children.get(i);
				children.remove(temp);
			}
			else if( children.get(i).probOfNode >= pmin)
			{
				printSpace(children.get(i).word.size());
				System.out.println("-->"+children.get(i).word);
				time ++;	
				PSTNode<E> temp = children.get(i);
				temp.print(time, pmin, rmin);
			}
			
		}
	}
	
	
	//create the probability and eliminate elemenets that have p > pmin
	void p_elimination(double pNum, PSTNode<E> motherNode)
	{
		countProb(motherNode, pNum);
		createProb(motherNode, pNum);
	}

	void r_elimination(double rNum, PSTNode<E> motherNode, ArrayList<E> data)
	{
		createRprob(rNum, motherNode, data);
	}

	void createRprob(double rNum, PSTNode<E> motherNode, ArrayList<E> data)
	{
		if(motherNode.children.size() != 0)
		{
			for(int i = 0; i < motherNode.children.size(); i++)
			{				
				PSTNode<E> childrenNode = motherNode.getNode(i);
				System.out.println("input node = " + childrenNode.word + "|  X = " + getXval(childrenNode, data));
				System.out.println();
				createRprob(rNum,childrenNode,data);
			}
		}
	}
	
	
	
	//recursive function to find the probability of each note of a branch
	void createProb(PSTNode<E> motherNode, double pNum)
	{
		if(motherNode.children.size() != 0)
		{
			for(int i = 0; i < motherNode.children.size(); i++)
			{
				PSTNode<E> tempMotherNode = motherNode.getNode(i);
				countProb(tempMotherNode, pNum);
				createProb(tempMotherNode, pNum);
			}
		}
		
	}
	
	
	E getXval(PSTNode<E> node, ArrayList<E> data)
	{
		ArrayList<Pair<E>> allXs = new ArrayList<Pair<E>>();
		ArrayList<E> childWord = node.word;
		for(int i = 0; i < data.size()-childWord.size()-1; i++)
		{
			ArrayList<E> temp = new ArrayList<E>(data.subList(i, i+childWord.size()));
			if(temp.equals(childWord))
			{
				
				E x = data.get(i+childWord.size());
				int check = 0;
				
				//check if the pair already exist
				for(int j = 0; j < allXs.size(); j++)
				{
					if(allXs.get(j).getFirst().equals(x))
					{
						allXs.get(j).addCount();
						check = 1;
					}
				}
				
				//we add Pair to arrayList if it is not found
				if(check == 0)
				{
					Pair<E> newX = new Pair<E>(x,1);
					allXs.add(newX);
				}
			}
		}
		
		
		Pair<E> tempx = allXs.get(0);
		int max = allXs.get(0).getSecond();
		
		for(int count = 0; count < allXs.size(); count++)
		{
			
			
			if(allXs.get(count).getSecond() > max)
			{
				max = allXs.get(count).getSecond();
				tempx = allXs.get(count);
			}
		}
		
		E x = tempx.getFirst();
		return x;
		
	}
	
	ArrayList<E> getParentWord(ArrayList<E> childWord)
	{
		ArrayList<E> parentWord = new ArrayList<E>();
		for(int i = 1; i < childWord.size(); i++)
		{
			parentWord.add(childWord.get(i));
		}
		return parentWord;
	}
	
	void countProb(PSTNode<E> motherNode, double pNum)
	{
		//total count = total word in the data array - current word length
		int sumOfCount = 0;
		if(motherNode.word != null)
		{
			 sumOfCount =  dataSize - motherNode.word.size();
		}
		else
		{
			//for the root empty array
			 sumOfCount = dataSize;
		}
		//probability = counts/sumOfCount
		for(int i = 0; i < motherNode.children.size(); i++)
		{
			//System.out.println("Sum of Count = "+sumOfCount + " node is " + motherNode.children.get(i).word );
			double prob = ((double)motherNode.children.get(i).countOfNode / sumOfCount);
			setProbNode(prob,i,motherNode);
		}
	}
	
	
	

	
	//set the probability for each node
	void setProbNode(double val,int index, PSTNode<E> motherNode)
	{
		motherNode.children.get(index).probOfNode = val;
	}
	
	
	void printSpace(int times)
	{
		for(int i = 0; i < times; i ++)
		{
			System.out.print("   ");
		}
	}

	

	//This function check if the input arraylist is suffix
	boolean isSuffix(ArrayList<E> input, PSTNode<E> currentNode, int loop)
	{
		
		ArrayList<E> temp = new ArrayList<E>();
		for(int i = input.size()-loop; i < input.size(); i++)
		{
			temp.add(input.get(i));
		}

		//Recursive from size of 1 to size of m-1 (from root to desired brunch)
		while(loop < input.size()-1)
		{
			for (int i = 0; i < currentNode.children.size(); i ++)
			{
				if(currentNode.getWord(i).equals(temp) == true)
				{
					index = i;
					PSTNode<E> newNode = currentNode.getNode(index);
					loop ++;
					isSuffix(input,newNode,loop);
				}
			}
		}
		
		if(temp.size() == input.size()-1)
		{
			//When we found the desired brunch, we use ifFound to see if the node fulfill with our requirement. 
			for (int i = 0; i < currentNode.children.size(); i ++)
			{
				if(currentNode.getWord(i).equals(temp) == true)
				{
//					System.out.println(" Input = "+input);
					ifFound(currentNode, i,input);
					return true;
				}
			}
		}
		return false;
	}
	
	int findX(ArrayList<E> input)
	{
		int target = 0;
		//     (counts of x appear after children / counts of children appear)
		//r = ------------------------------------------------------------------
		//     (counts of x appear after parents/ counts of parents)
		
		
		return target;
	}

	
	
	
}
