/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason Donald
 */
public class TempDelete {
	/** Delete k from the tree rooted at t (if there)
	* and return the root of the resulting tree. */
	private static <T extends Comparable<? super T>> BSTNode<T> delete(
			BSTNode<T> t, T k) {

		if (t == null) { // k not in tree; do nothing.
		} else if (k.compareTo(t.key) < 0) {
			t.left = delete(t.left, k);
		} else if (k.compareTo(t.key) > 0) {
			t.right = delete(t.right, k);
		} else { // Found it; now delete it.
			if (t.right == null) {
				// t has at most one child, on the left.
				t = t.left;
			} else {
				// t has a right child. Replace t’s value
				// with its successor value.
				T successor = min(t.right);
				t.key = successor;
				// Delete that successor.
				t.right = delete(t.right, successor);
			}
		}
		return t;
	}
}
