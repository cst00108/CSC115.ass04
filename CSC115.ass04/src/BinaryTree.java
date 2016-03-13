/*
 * Name:      Jason Donald
 * ID:        V00861539
 * Date:      2006-03-13
 * Filename:  BinaryTree.java
 * Details:   CSC115 Assignment 4
 */
 
public class BinaryTree<E> {
	protected TreeNode<E> root;
	protected int size = 0;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}

	/**
	 * 
	 * @return The height of the tree. The textbook's definition of the height 
	 * is the maximum number of nodes from the root to a leaf node. The height 
	 * of an empty tree is therefore equal to 0.
	 */
	public int height(){
		if(isEmpty()){
			return 0;
		}
		
		return getDepth(root);
	}

	//recurse to get the height
	private int getDepth(TreeNode<E> node){
		int leftDepth = 0;
		int rightDepth = 0;
				
		if(node.left != null){
				leftDepth = getDepth(node.left);
		}
		
		if(node.right != null){
			rightDepth = getDepth(node.right);
		}
		
		if(leftDepth > rightDepth){
			return ++leftDepth;
		}
		
		return ++rightDepth;
	}
	
	/**
	 * 
	 * @return True if the tree is empty.
	 */
	public boolean isEmpty(){
		return root == null;
	}
	
	/**
	 * Removes all the nodes from the tree, making it empty.
	 */
	public void makeEmpty(){
		root = null;
		size = 0;
	}
}

	
