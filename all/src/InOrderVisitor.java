

public class InOrderVisitor implements Visitor {

	@Override
	public void visit(Node node) {
		if (node == null)
			return;
		
		node.visit();
		
		visit(node.left);
		visit(node.right);

	}
	

}
