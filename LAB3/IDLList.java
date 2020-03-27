//***********************************************************//
//Name : Nidhi Chovatiya
//Subject : CS-570
//lab3
//CWID : 10457344
//**********************************************************//



package lab3;

import java.util.ArrayList;

public class IDLList<E> {

	private Node<E> tail;
	private Node<E> head;
	private int size;
	private ArrayList<Node<E>> indices = new ArrayList<>();

	private static class Node<E> {

		E data;
		
		private Node<E> next = null;
		
		private Node<E> prev = null;

		Node(E elem) {
			
			this.data = elem;
			
		}

		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	// method for creating an empty double-linked list
	public IDLList() {
		this.tail = null;
		this.head = null;
		size = 0;
	}

	//method which add an element at specified index
	public boolean add(int index, E elem) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else {
		Node<E> a1 = indices.get(index);
		Node<E> newList = new Node<E>(elem, a1.prev, a1);
		a1.prev.next = newList;
		a1.prev = newList;
		indices.add(index, newList);
		size++;
		return true;
		}
	}

//	method which add an element at the top
	public boolean add(E elem) {
		if (head == null) {
			Node<E> newList = new Node<E>(elem);
			newList.data = elem;
			head = newList;
			tail = newList;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> newList = new Node<E>(elem);
			newList.next = head;
			head.prev = newList;
			head = newList;
			indices.add(0, newList);
			size++;
			return true;
		}
	}

	// method which add an element at the last
	public boolean append(E elem) {
		if (head == null) {
			Node<E> a1 = new Node<E>(elem);
			a1.data = elem;
			head = a1;
			tail = a1;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> newList = new Node<E>(elem);
			tail.next = newList;
			newList.prev = tail;
			tail = newList;
			indices.add(tail);
			size++;
			return true;
		}
	}
	
	// Method which returns the size of the List
		public int size() {
			int a = indices.size();
			return a;
		}

	// method which returns the element at specified index
	public E get(int index) {
		Node<E> getElement = indices.get(index);
		return getElement.data;
	}

	// method which returns the First element 
	public E getHead() {
		Node<E> getElement = indices.get(0);
		return getElement.data;
	}

	//  method which returns the Last element 
	public E getLast() {
		Node<E> getElement = tail;
		return getElement.data;
	}

	// Method which eliminate and return the First element 
	public E remove() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (tail == head) {
			Node<E> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		else {
		Node<E> n1 = head;
		head = head.next;
		head.prev = null;
		indices.remove(n1);
		size--;
		return n1.data;
		}
	}

	// Method which eliminate and return the Last element
	public E removeLast() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (tail == head) {
			Node<E> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		else {
		Node<E> n1 = tail;
		tail = tail.prev;
		tail.next = null;
		indices.remove(n1);
		size--;
		return n1.data;
		}
	}

	// Method which eliminate and return the element at given index
	public E removeAt(int index) {
		if (index < 0 || index >= size ) {
			throw new IndexOutOfBoundsException();
		}
		
		else if (index == 0) {
			E temp = this.remove();
			return temp;
		}
		else {
			Node<E> temp = indices.get(index);
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			indices.remove(temp);
			size--;
			return temp.data;
		}
	}

	// Method which removes the first occurrence of in the list and return true
	
	public boolean remove(E elem) {
		Node<E> temp = head;
		int i = 0;
			while (temp.next != null) {
				if (temp.data.equals(elem)) {
					if (i == 0) {
						head = head.next;
						size--;
						indices.remove(0);
						return true;
					}
						temp.next.prev = temp.prev;
						temp.prev.next = temp.next;
						size--;
						indices.remove(i);
						return true;
				}
				temp = temp.next;
				i++;
			}
			return false;
		}

	//Method which gives string of all the elements in the list
	
	public String toString() {
		Node<E> ref_elem = head;
		StringBuilder Final = new StringBuilder();
		
			while (ref_elem != null) {
				Final.append(ref_elem.data);
				
					if (ref_elem.next != null) {
						Final.append(" , ");
						}
					
					ref_elem = ref_elem.next;
				}
			
			return Final.toString();
	}
}
