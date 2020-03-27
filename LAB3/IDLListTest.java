//java file for implements all methods 

package lab3;

public class IDLListTest {

	public static void main(String[] args) {
		
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(111);
		list.add(109);
		list.add(107);
		list.add(105);
		list.add(103);
		list.add(101);
		System.out.println("Elements of List are:" + "\n" + list.toString() + "\n");
		
		list.append(113);
		System.out.println("Add new element at last:" + "\n" + list.toString() + "\n");
		
		list.add(3, 106);
//		list.add(8, 106);    // invalid input
		System.out.println("Add new element at given index:" + "\n" + list.toString() + "\n");
		
		System.out.println("first element=" + list.remove());
		System.out.println("Remove first element:" + "\n" + list.toString() + "\n");
		
		System.out.println("Last element=" + list.removeLast());
		System.out.println("Remove last element:" + "\n" + list.toString() + "\n");
		
		System.out.println("element at given index=" + list.removeAt(3));
		System.out.println("Remove element from given index:" + "\n" + list.toString() + "\n");
		
		System.out.println("operation=" + list.remove(105));
		System.out.println("Remove the specified element:" + "\n" + list.toString());


	}

}
