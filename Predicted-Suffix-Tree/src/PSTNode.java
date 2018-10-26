import java.util.*;
public class PSTNode<E> {

	private ArrayList<PSTNode<E>> children = new ArrayList<PSTNode<E>>();
	private ArrayList<E> word;
	private ArrayList <ArrayList<E>> allSuffix = new ArrayList<ArrayList<E>>();
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

	ArrayList<E> getWord(int index)
	{
		return children.get(index).word;
	}

	PSTNode<E> getNode(int index)
	{
		return children.get(index);
	}

	void addToTree(ArrayList<E> input)	//addNode function
	{
		for(int i = 1; i <= length; i++)
		{
			for(int j = 0; j < input.size()-i; j++)
			{
				//Loop through the data array and get each element from size of 0 to size of length
				ArrayList<E> temp = new ArrayList<E>(input.subList(j, j+i));
				PSTNode<E> newNode = new PSTNode(temp);

				//Following function create the level 1 nodes: abrdc
				if(newNode.word.size() == 1 && isLevel1Node(newNode) == true)
				{
					children.add(newNode); //If size == 1 (abcdr). Add to the children array
				}
				//				else if(newNode.word.size() > 1 && isSuffix(newNode.word) == true)
				else if(newNode.word.size() > 1)
				{
					isSuffix(newNode.word, this, 1);
					//isFound(this,newNode.word,0,1); //Final step, add to the node
				}
			}
		}
	}

//	boolean isFound(PSTNode node, ArrayList<E> val, int index, int loop)
//	{	
//		ArrayList<E> temp = new ArrayList<E>(val.subList(val.size()-loop, val.size()));
//		for(int i = 0; i < node.children.size(); i++)
//		{
//			if(node.getNode(i).word.equals(temp))
//			{
//				index = i;
//			}
//		}
//		while(loop < val.size()-1)
//		{
//NOTE: problem is here
			//System.out.println("Index is "+ index +" val is "+val+" temp is "+temp);
//			System.out.println("inner loop 1");
//			PSTNode<E> tempNode = node.getNode(index);
//			System.out.println("temp size = "+tempNode.children.size());
//			isFound(tempNode,val,index,loop);
			
//--------------------------------------------------------------------------
//			PSTNode<E> temp1 = node.getNode(index);
//			node.children.remove(index);
//			temp1.children.add(new PSTNode(val));
//			node.children.add(temp1);
//			loop++;
//		}
//		//After going through the loops
//		System.out.println("val 2 = "+val + " node 2 = "+node.word);
//		PSTNode<E> temp1 = node.getNode(index);
//		node.children.remove(index);
//		temp1.children.add(new PSTNode(val));
//		node.children.add(temp1);
//		return false;
//	}

	boolean ifFound(PSTNode node, int index, ArrayList<E> val)
	{
		
		PSTNode newNode = node.getNode(index);
		
		for(int i = 0; i < newNode.children.size(); i++)
		{
			if(newNode.getWord(i).size() != val.size())
			{
				return true;
			}
			
			//System.out.println("children word = " + newNode.getWord(i) + " and the value is = " + val);
			if(newNode.getWord(i).equals(val))
			{
				return true;
			}
		}
		
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
	
	
	
	void addNote(PSTNode node, ArrayList<E> val)
	{
		PSTNode<E> newChild = new PSTNode(val);
		node.children.add(newChild);
	}
	
	void print(int time)	//Print function for testing
	{
		//System.out.print("   ");
		for(int i = 0; i < children.size(); i++)
		{
			System.out.println("-->"+children.get(i).word);
			PSTNode temp = children.get(i);
			time ++;
			temp.print(time);
		}
	}

	


	boolean isSuffix(ArrayList<E> input, PSTNode currentNode, int loop)
	{
		ArrayList<E> temp = new ArrayList<E>();
		for(int i = input.size()-loop; i < input.size(); i++)
		{
			temp.add(input.get(i));
		}
		
		while(loop < input.size()-1)
		{
			for (int i = 0; i < currentNode.children.size(); i ++)
			{
				if(currentNode.getWord(i).equals(temp) == true)
				{
					index = i;
					//System.out.println("IsSuffix(): found = "+currentNode.getWord(index) + " and input is "+input); 
					PSTNode<E> newNode = currentNode.getNode(index);
					loop ++;
					isSuffix(input,newNode,loop);
				}
			}
		}
		
		for (int i = 0; i < currentNode.children.size(); i ++)
		{
			if(currentNode.getWord(i).equals(temp) == true)
			{
				ifFound(currentNode, i,input);
				return true;
			}
		}
		//System.out.println("IsSuffix(): temp Not Found = "+ input);
		return false;
	}
	
	
}
