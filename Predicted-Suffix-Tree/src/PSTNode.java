import java.util.*;
public class PSTNode<E> {

	private ArrayList<PSTNode> children = new <PSTNode> ArrayList();
	private ArrayList<E> word;
	private int length;

	PSTNode(int size)	//default constructor
	{
		ArrayList<PSTNode> children = new <PSTNode> ArrayList();
		length = size;
	}

	ArrayList<E> getVal()	//getter for value of node
	{
		return word;
	}

	void addToTree(ArrayList<E> input)	//addNode function
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < input.size()-i; j++)
			{
				ArrayList<E> temp = new ArrayList<E>(input.subList(j, j+i));
				System.out.println(temp);
			}
			
		}
	}

	boolean addNote(PSTNode child)
	{
		boolean found = ifFound(child.word);
		if(found == false && (child.isSuffix(word) == true || child.word.size() == 0))
		{
			for(int i = 0; i < children.size(); i++)
			{
				found = children.get(i).addNote(child);
				return true;
			}
			
		}
		if(found == false && word.size() == child.word.size()-1)
		{
			children.add(child);
			return true;
		}
		return false;
	}
	
	void print()	//Print function for testing
	{
		
	}
	
	boolean ifFound(ArrayList<E> input)
	{
		for(int i = 0; i < children.size(); i++)
		{
			if(input == children.get(i).word)
			{
				return true;
			}
		}
		return false;
	}
	
	boolean isSuffix(ArrayList<E> input)
	{
		//compare the size first
		return false;
		
	}
}
