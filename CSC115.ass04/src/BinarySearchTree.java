/*
 * The shell of the class, to be completed as part of CSC115 Assignment 4 : Patient Location.
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/*
 * NOTE TO STUDENT:
 * Complete this class as per the BinarySearchTree.html specification document.
 * Fill in any of the parts that have the comment:
 *	/******* COMPLETE *******
 * Do not change method headers or code that has been supplied.
 * Write as many methods as you need to make the code simple and easy to understand.
 * All methods must be properly commented.
 * Delete all messages to you, including this one, before submitting.
 */

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	private E tempDelete = null;
	private static final int TEMP_TRIES = 1000;  //to check if delete(E) works
	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Places all the items in the tree into a sorted list.
	 * @return the sorted list.
	 */
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		
		if(!this.isEmpty()){
			collectInOrder(list,root);
		}
		
		return list;
	}
	
	private void inOrderRec(ArrayList<E> list, TreeNode<E> parent){
		if(parent.left != null){
			inOrderRec(list, parent.left);
		}
		
		list.add(parent.item);
		
		if(parent.right != null){
			inOrderRec(list, parent.right);
		}
	}

	public void delete(E key){
		tempDelete = key;
		try{
			if(root != null){
				root = deleteRec(root, key);
			} else {
				//tree has no elements
			}
		} catch(NullPointerException x){
			//key wasnt found
		}
	}
	
	public TreeNode<E> deleteRec(TreeNode<E> branch, E key) 
			throws NullPointerException{
		
		int compareTo = key.compareTo(branch.item);
		
		if(compareTo < 0){
			branch.left = deleteRec(branch.left, key);
		} else if(compareTo > 0){
			branch.right = deleteRec(branch.right, key);
		} else if(compareTo == 0) { //if it's the one to erase,
			size--;

			if(isFull(branch)){
				return removeFromFull(branch);
			} else {
				return removeFromPart(branch);
			}
		}
		
		return branch;
	}
	
	//removes branch with 0 or 1 children
	private TreeNode<E> removeFromPart(TreeNode<E> remove){

		//dont really give a crap if 'remove' has no children
		if(remove.left != null){
			return remove.left;
		} else { 
			return remove.right;
		}
	}

	private boolean isFull(TreeNode<E> node){
		if(node.left != null && node.right != null) return true;
		
		return false;
	}
	
	//if both branches are full
	private TreeNode<E> removeFromFull(TreeNode<E> remove){
		TreeNode<E>  parent = remove;
		TreeNode<E>  move = parent.right;
		
		while(move.left != null){
			parent = move;
			move = move.left;
		}
		
		if(parent != remove){
			parent.left = move.right; //remove 'move' and re-attach tree if there is more of a tree there			

			move.right = remove.right;
		}
		
		//replace removed branch
		move.left = remove.left;

		
		
		
		return move;
	}

/*
 * NOTE TO STUDENT.
 * This recursive method is the one that does the work
 * of traversing the tree in order and placing items
 * into the list.
 */
	private void collectInOrder(ArrayList<E> list, TreeNode<E> node) {
		if(node.left != null){
			collectInOrder(list, node.left);
		}
		
		list.add(node.item);
		
		if(node.right != null){
			collectInOrder(list, node.right);
		}
 	}

	public void insert(E item){
		if(root == null){
			root = new TreeNode(item);
			size++;
		} else {
			insert(item, root);
		}
	}
	
	private void insert(Comparable item, TreeNode parent){
		int parentCompare = item.compareTo(parent.item);
		
		if(parentCompare < 0){
			if(parent.left == null){
				parent.left = new TreeNode(item);
				size++;
			} else{
				insert(item, parent.left);
			}
		} else if(parentCompare > 0){
			if(parent.right == null){
				parent.right = new TreeNode(item);
				size++;
			} else{
				insert(item, parent.right);
			}
		}
		
		//otherwise, get out of here.  its the same.
	}
	
	
	public E retrieve(E key){
		return null;
	}
	
	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		BinarySearchTree<PatientLocation> tree = new BinarySearchTree<PatientLocation>();
		PatientLocation p1 = new PatientLocation("Duck", "Donald", 338);
		PatientLocation p2 = new PatientLocation("Mouse", "Minnie",116);
		PatientLocation p3 = new PatientLocation("Dog", "Goofie",422);
		PatientLocation p4 = new PatientLocation("Newman","Alfred",607);
		tree.insert(p1);
		tree.insert(p4);
		tree.insert(p3);
		tree.insert(p2);
//		ArrayList<PatientLocation> list  = tree.inOrder();
//		System.out.println(list);

		// draw the tree in its current state:
//		DrawableBTree<PatientLocation> dbt = new DrawableBTree<PatientLocation>(tree);
//		dbt.showFrame();
		tree.delete(p4);
		
		ArrayList<PatientLocation> ordered = tree.inOrder();

		System.out.println("Patients:");
		for(PatientLocation patient : ordered){
			System.out.println("\t" + patient);
		}

		tree.delete(p4); //attempt deleting something that was already removed
		tree.delete(p3);

		ordered = tree.inOrder();

		System.out.println("Patients:");
		for(PatientLocation patient : ordered){
			System.out.println("\t" + patient);
		}

		//delete all nodes
		tree.delete(p1);
		tree.delete(p2);

		ordered = tree.inOrder();

		System.out.println("Patients:");
		for(PatientLocation patient : ordered){
			System.out.println("\t" + patient);
		}		
		
		//check a lot of times to check if deleting one node works		
		for(int index=0; index<1000; index++){
			if(!isDeleted()){
				System.out.println("deletion in isdeleted() didn't check out.");
				break;
			}
		}
		
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
		int[] ints = getInts();

		int delete = mixInts(new ArrayList<>(), ints); 

		for(int x : ints){
			tree2.insert(x);
		}

		tree2.delete(delete);
		
		DrawableBTree<Integer> dbt = new DrawableBTree<Integer>(tree2);
		dbt.showFrame();

	}
	
	private static boolean isDeleted(){
		ArrayList<Integer> arrayList = new ArrayList<>();
		Integer delete = mixInts(arrayList, getInts());
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		for(Integer integer : arrayList){
			tree.insert(integer);
		}
//DrawableBTree<PatientLocation> dbt = new DrawableBTree<PatientLocation>(tree);
//dbt.showFrame();

		tree.delete(delete); //delete the random entry
//DrawableBTree<PatientLocation> dbt2 = new DrawableBTree<PatientLocation>(tree);
//dbt2.showFrame();
		
		ArrayList<Integer> numsList = tree.inOrder();
		
		if(numsList.size() != TEMP_TRIES-1){
			return false;
		}
		
		//check out if deleted 
		Iterator<Integer> resultIterator = numsList.iterator();
		
		for(int index=0; resultIterator.hasNext(); index++){
			int resultInteger = resultIterator.next().intValue(); 

			//if integers arent the same and deleted isnt the same
			if(resultInteger != index && resultInteger != ++index){ 
				System.out.println("isDeleted() - not found - deleted = " 
						+ delete + ", index = " + index);

				return false;
			}
		}
		
		return true;
	}
	
	private static int[] getInts(){
		int[] ints = new int[TEMP_TRIES];
		for(int index=0; index<ints.length; index++){
			ints[index] = index;
		}
		
		return ints;
	}
	
	private static Integer mixInts(ArrayList<Integer> empty, int[] ints){
		Random random = new Random();
		
		//randomize the numbers
		for(int index=0; index<ints.length; index++){
			int swap = random.nextInt(ints.length);
			int temp = ints[swap];
			ints[swap] = ints[index];
			ints[index] = temp;
		}

		//add mixed up numbers into 'empty'
		for(int index=0; index<ints.length; index++){
			empty.add(new Integer(ints[index]));
		}

		//return new number to search for
		return new Integer(random.nextInt(ints.length));
	}	
}
