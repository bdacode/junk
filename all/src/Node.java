

public class Node {

	String label;
	Node left;
	Node right;
	
	static int counter = 0;
	
	public Node() {
		this.label = String.valueOf(counter++);		
	}

	public Node(Node left,Node right) {
		this.label = String.valueOf(counter++);
		this.left = left;
		this.right = right;
	}

	public Node(String label,Node left,Node right) {
		this.label = label;
		this.left = left;
		this.right = right;
	}
	
	public void visit() {
		System.out.println("visit: "+label);
	}
	
}
