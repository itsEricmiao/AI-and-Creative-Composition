import java.util.*;
public class PSTNode<E> {

	private ArrayList<PSTNode> children = new <PSTNode> ArrayList();
	private E value;
	
	PSTNode()
	{
		
	}

	PSTNode(E input)
	{
		ArrayList<PSTNode> children = new <PSTNode> ArrayList();
		value = input;
	}

	E getVal()
	{
		return value;
	}

	void addChild(PSTNode child)
	{
		children.add(child);
	}

	void print()
	{
		
	}

	


}
