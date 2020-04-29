package lab5;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	/**
	 * Node Class
	 * @author Nidhi
	 * @param <E>
	 */

	private class Node<E> {
		/**
		 * Data fields
		 */
		public E data;
		public int priority;
		public Node<E> right;
		public Node<E> left;
		
		/** 
		 * Constructor
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if (data != null) {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			} else {
				throw new IllegalArgumentException();
			}
		}

		/**
		 * Rotate the Treap Right
		 * @return
		 */
		public Node<E> rotateRight() {
			Node<E> t1 = this.left;
			Node<E> left = t1.right;
			t1.right = this;
			this.left = left;
			return t1;
		}
	
		/**
		 * Rotate the Treap Left 
		 * @return
		 */
		public Node<E> rotateLeft() {
			Node<E> t1 = this.right;
			Node<E> right = t1.left;
			t1.left = this;
			this.right = right;
			return t1;
		}

		public String toString() {
			return this.data.toString();
		}
	}

      /**
       * Data fields for Treap class
       */
	    private Random priorityGenerator;
	    private Node<E> root;

      /**
       * Constuctor
       * empty treap
       */
	    public Treap() {
		  priorityGenerator = new Random();
		  root = null;
	    }

	    /**
		 * Constructor
		 * @param seed
		 */
		public Treap(long seed) {
			priorityGenerator = new Random(seed);
			root = null;
		}

		// Methods 
		/**
		 * @param stk
		 * @param present
		 */
	private void reheap(Stack<Node<E>> stk, Node<E> present) {
		if (stk.isEmpty()) {
			root = present;
		} else if (present.priority < stk.peek().priority) {
			return;
		} else {
			if (stk.peek().left == present) {
				Node<E> removed = stk.pop();
				removed.rotateRight();
				if (!stk.isEmpty()) {
					if (stk.peek().left == removed) {
						stk.peek().left = present;
					} else {
						stk.peek().right = present;
					}
				}
			} else {
				Node<E> removed = stk.pop();
				removed.rotateLeft();
				if (!stk.isEmpty()) {
					if (stk.peek().left == removed) {
						stk.peek().left = present;
					} else {
						stk.peek().right = present;
					}
				}
			}
			reheap(stk, present);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	boolean add(E key) {
		int priority =  priorityGenerator.nextInt();
		return add(key, priority);
	}

	/**
	 * inserting list of pairs (key,priority)
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		}

		Node<E> t1 = root;
		boolean add = false;
		Stack<Node<E>> stk = new Stack<Node<E>>();

		while (add == false) {
			if (key.compareTo(t1.data) == 0) {
				break;
			}

			if (key.compareTo(t1.data) > 0) {
				stk.push(t1);
				if (t1.right != null) {
					t1 = t1.right;
				} else {
					t1.right = new Node<E>(key, priority);
					t1 = t1.right;
					add = true;
				}
				
			} else {
				stk.push(t1);
				if (t1.left != null) {
					t1 = t1.left;
				} else {
					t1.left = new Node<E>(key, priority);
					t1 = t1.left;
					add = true;
				}
			}
		}

		reheap(stk, t1);
		return add;
	}

	/**
	 * Return True if given key is present else False
	 * @param key
	 * @return
	 */
	boolean delete(E key) {
		if (find(key) == false || root == null) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
	}

	/**
	 * If the key was not found, the method does not modify the treap and returns false else return modified treap
	 * @param key
	 * @param root
	 * @return
	 */
	private Node<E> delete(Node<E> node, E key) {
		if (node != null) {
				int match = key.compareTo(node.data);
				if (match < 0) {
					node.left = delete(node.left, key);
				} else if (match == 0) {
					if (node.left == null) {
						return node.right;
					} else if (node.right == null) {
						return node.left;
					} else {
						Node<E> t1 = node.right;
						while (t1.left != null) {
							t1 = t1.left;
						}
						node.data = t1.data;
						node.right = delete(node.right, node.data);
					}
				} else {
					node.right = delete(node.right, key);
				}
		}
		return node;
	}

	/**
	 * Return True if given key is present in a tree else return false
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		} else if (key.compareTo(root.data) < 0) {
			return find(root.left, key);
		} else if (key.compareTo(root.data) > 0) {
			return find(root.right, key);
		} else {
			return true;
		}
	}

	/**
	 * Find function
	 * @param key
	 * @return
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * @param node
	 * @param depth
	 * @return
	 */
	private String traverse (Node<E> node, int depth) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			str.append(" ");
		}
		if (node != null) {
			str.append("(key = " + node.data + ", priority = " + node.priority + ")" + "\n");
			str.append(traverse(node.left, depth + 1));
			str.append(traverse(node.right, depth + 1));
			
		} else {
			str.append("null" + "\n");
		}
		return str.toString();

	}

	/**
	 * @param sb
	 * @return
	 */
	public String toString() {
		return traverse(root, 1);
	}

	public static void main(String[] args) {
		Treap<Integer> check = new Treap<Integer>();
			System.out.println("Original Treap : " + "\n");
			check.add(3, 12);
			check.add(2, 31);
			check.add(6, 70);
			check.add(1, 84);
			check.add(5, 83);
			check.add(7, 26);
			System.out.println(check.toString());
			
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-" + "\n");
			System.out.println("Is there a node with the given key in the treap(FIND operation) : " + check.find(4) + "\n");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
			
			System.out.println("Treap after ADD operation : " + "\n");
			check.add(4, 19);
			System.out.println(check.toString());
			
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-" + "\n");
			System.out.println("the node with the given key from the treap is deleted? : " + check.delete(8) + "\n");
			System.out.println("Treap after DELETE operation : " + "\n");
			System.out.println(check.toString());
}

}

