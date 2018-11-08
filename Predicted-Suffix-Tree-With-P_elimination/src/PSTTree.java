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
	
	void printAll(double pmin)
	{
		root.print(1,pmin);
	}
	
	void addToTree(ArrayList<E> input)
	{
		root.addToTree(input);
	}
	
	void p_eli(double p_Num)
	{
		root.p_elemination(p_Num, root);
	}
	
	

	
}
