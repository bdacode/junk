import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;



public class LevelOrderPrint {

	static void printLevelOrder(ArrayList<Node> nodes) {

		if (nodes.isEmpty())
			return; // done

		ArrayList<Node> children = new ArrayList<Node>();
		
		for (Node n : nodes) {
			System.out.print(n.label+" ");
			if (n.left != null)
				children.add(n.left);
			if (n.right != null)
				children.add(n.right);
		}
		
		printLevelOrder(children);
		

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Node root = new Node(new Node(new Node(),new Node()),new Node(new Node(),new Node()));
		
		Node root1 = new Node("rahul",new Node("yang",new Node("michael",null,null),null),new Node("attul",new Node("amit",null,null),new Node("sri",null,null)));

		new InOrderVisitor().visit(root);
		
		ArrayList<Node> start = new ArrayList<Node>();
		start.add(root1);
		
		System.out.print("Level Order: ");
		printLevelOrder(start);
		System.out.println();

		Queue<Node> q = new ConcurrentLinkedQueue<Node>();
		q.add(root1);
		
		System.out.print("QueueLevelOrder: ");
		while (!q.isEmpty()) {
			Node n = q.remove();
			n.visit();
			if (n.left != null)
				q.add(n.left);
			if (n.right != null)
				q.add(n.right);
		}
	}

}
