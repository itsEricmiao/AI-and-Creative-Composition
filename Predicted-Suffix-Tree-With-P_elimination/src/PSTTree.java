import java.util.*;
public class PSTTree<E> {
	private PSTNode<E> root;
	
	//default constructor 
	PSTTree(){}
	
	void setRoot(PSTNode<E> rootNode)
	{
		root = rootNode;
	}
	
	PSTNode<E> getRoot()
	{
		return root;
	}
	
	void printAll()
	{
		root.print(1);
	}
	
	void addToTree(ArrayList<E> input)
	{
		root.addToTree(input);
	}
	
	
	

	
}
