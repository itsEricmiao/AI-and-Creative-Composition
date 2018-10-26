import java.util.*;
public class PSTNode<E> {

	private ArrayList<PSTNode<E>> children = new ArrayList<PSTNode<E>>();
	private ArrayList<E> word;
	private int length;
	private int index = 0;
	
	PSTNode() 
	{
        
    }
	
	PSTNode(int order) 
	{
        length = order;
    }
	
	PSTNode(ArrayList<E> input) 
	{
        word = input;
    }

	void addToTree(ArrayList<E> input)	//addNode function
	{
		for(int i = 0; i <= length; i++)
		{
			for(int j = 0; j < input.size()-i; j++)
			{
				ArrayList<E> temp = new ArrayList<E>(input.subList(j, j+i));
				PSTNode<E> newNode = new PSTNode(temp);
				
				//Create the level 1 nodes: abrdc
				if(newNode.word.size() == 1 && isLevel1Node(newNode) == true)
				{
					System.out.println(newNode.word);
					children.add(newNode);
				}
				else if(newNode.word.size() > 1 && isSuffix(newNode.word) == true)
				{
					
					addToNode(newNode);
					
				}
			}
		}
	}

	void addToNode(PSTNode child)
	{
		System.out.println("Child is "+ child.word + " Index is = " + index);
		PSTNode temp = children.get(index);
		temp.children.add(child);
		children.add(temp);
	}
	
	void print()	//Print function for testing
	{
		for(int i = 0; i < children.size(); i++)
		{
			//System.out.println(children.get(i).word);
		}
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

	
	boolean isSuffix(ArrayList<E> input)
	{
		ArrayList<E> temp = new ArrayList<E>();
		//System.out.println(input);
		for(int i = input.size()-length+1; i < input.size(); i++)
		{
			temp.add(input.get(i));
		}
		
		
		for (int i = 0; i < children.size(); i ++)
		{
			if(children.get(i).word.equals(temp) == true)
			{
				index = i;
				return true;
			}
		}
		return false;
		
	}
}
