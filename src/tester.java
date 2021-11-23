

public class tester {
	/*AVLNode a  = new AVLNode(35,"String of 35");
	AVLNode b  = new AVLNode(9,"String of 9");
	AVLNode c  = new AVLNode(26,"String of 26");
	AVLNode d  = new AVLNode(6,"String of 6");
	AVLNode e  = new AVLNode(13,"String of 13");
	AVLNode f  = new AVLNode(21,"String of 21");
	AVLNode g  = new AVLNode(30,"String of 30");
	AVLNode h  = new AVLNode(2,"String of 2");
	AVLNode i  = new AVLNode(4,"String of 4");
	AVLNode j  = new AVLNode(10,"String of 10");
	AVLNode k  = new AVLNode(15,"String of 15");
	AVLNode l  = new AVLNode(19,"String of 19");
	AVLNode m  = new AVLNode(24,"String of 24");
	AVLNode n = new AVLNode(28,"String of 28");
	AVLNode o  = new AVLNode(33,"String of 33");
	a.parent = null;
	a.right = new AVLNode();
	a.left = new AVLNode();
	a.rank = max(a.right.rank, a.left.rank) + 1;*/
	
	public static void main(String[] args) {
		AVLTree myTree = new AVLTree();
		myTree.insert(35,"String of 35");
		myTree.insert(9,"String of 9");
		myTree.insert(26,"String of 26");
		myTree.insert(6,"String of 6");
		myTree.insert(13,"String of 13");
		myTree.insert(21,"String of 21");
		myTree.insert(30,"String of 30");
		myTree.insert(2,"String of 2");
		myTree.insert(4,"String of 4");
		myTree.insert(10,"String of 10");
		myTree.insert(15,"String of 15");
		myTree.insert(19,"String of 19");
		myTree.insert(24,"String of 24");
		myTree.insert(28,"String of 28");
		myTree.insert(33,"String of 33");
	}
}
