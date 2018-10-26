import java.util.*;
public class PSTNode<E> {

	private ArrayList<PSTNode<E>> children = new ArrayList<PSTNode<E>>();	//The children of the node
	private ArrayList<E> word; // The value of node
	private int length;//order of the PST tree
	private int index = 0;	//This is the index of next node for isSuffix() and isFound() ----> it is useless at this point but i will combine it into my functions next week

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
			for(int j = 0; j < input.size()-i; j++)
			{
				//create the temp arraylist to store the element we just get
				ArrayList<E> temp = new ArrayList<E>(input.subList(j, j+i));
				//using the temp to create a new temp node
				PSTNode<E> newNode = new PSTNode(temp);

				//Following function create the level 1 nodes: abrdc
				if(newNode.word.size() == 1 && isLevel1Node(newNode) == true)
				{
					children.add(newNode); //If size == 1 (abcdr). Add to the this.children array
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
	boolean ifFound(PSTNode node, int index, ArrayList<E> val)
	{
		
		PSTNode newNode = node.getNode(index);
		
		for(int i = 0; i < newNode.children.size(); i++)
		{
			//If the size of input node.value is different than the node.value already in the target note's children arrayList
			if(newNode.getWord(i).size() != val.size())
			{
				return true;
			}
			
			//If we find a same word from the children arraylist, we return true
			if(newNode.getWord(i).equals(val))
			{
				return true;
			}
		}
		//After node pass all those condition showing above, we move on.
		addNote(newNode,val);
		return false;
	}
	
	//This function is for level 1 of the PST tree (size of the node == 1)
		boolean isLevel1Node(PSTNode level1Node)
		{
			for (int i = 0; i < children.size(); i ++)
			{
				if(children.get(i).word.equals(level1Node.word) == true)
				{
					
					return false;
				}
			}
			return true;
		}
	
	
	//Add node to the branch
	void addNote(PSTNode node, ArrayList<E> val)
	{
		PSTNode<E> newChild = new PSTNode(val);
		node.children.add(newChild);
	}
	
	//Print function for testing
	void print(int time)	
	{
		for(int i = 0; i < children.size(); i++)
		{
			printSpace(children.get(i).word.size());
			System.out.println("-->"+children.get(i).word);
			PSTNode temp = children.get(i);
			time ++;
			temp.print(time);
		}
	}
	
	void printSpace(int times)
	{
		for(int i = 0; i < times; i ++)
		{
			System.out.print("   ");
		}
	}

	

	//This function check if the input arraylist is suffix
	boolean isSuffix(ArrayList<E> input, PSTNode currentNode, int loop)
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
		
		//When we found the desired brunch, we use ifFound to see if the node fulfill with our requirement. 
		for (int i = 0; i < currentNode.children.size(); i ++)
		{
			if(currentNode.getWord(i).equals(temp) == true)
			{
				ifFound(currentNode, i,input);
				return true;
			}
		}
		return false;
	}
	
	
}
