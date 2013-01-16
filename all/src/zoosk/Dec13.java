package zoosk;

public class Dec13 {

	boolean isOdd(int i) {
		return (i & 1) > 0;
	}

	public static class Node {
		Node left;
		Node right;
		int value;
	}

	public boolean isBST(Node root) {
		return isBSTMinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBSTMinMax(Node root, int minAllowed, int maxAllowed) {
		if (root != null && root.value < minAllowed)
			return false;
		if (root != null && root.value > maxAllowed)
			return false;
		return (root == null)
				|| (!((root.left != null && root.left.value >= root.value) || (root.right != null && root.right.value <= root.value))
						&& isBSTMinMax(root.left, minAllowed, root.value - 1) && isBSTMinMax(
							root.right, root.value + 1, maxAllowed));
	}

	boolean isBinarySearchTree(Integer node) {
		return false;
	}

	public static void main(String[] args) {
		System.out.println(-1 % 2);
	}

}
