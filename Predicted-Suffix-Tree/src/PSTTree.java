
public class PSTTree<E> extends PSTNode<E> {

	private PSTNode<E> root = new PSTNode<E>();
	
	
	PSTTree(){}
	
	PSTTree(PSTNode<E> inputNode)
	{
		root = inputNode;
	}

	
}
