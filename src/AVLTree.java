/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info.
 *
 */

public class AVLTree {

	private int height = 0;
	public AVLNode root = null;
	public AVLTree.AVLNode searchNodePointer;
	public int size = 0;

	public AVLTree(AVLNode myRoot) {
		this.root = myRoot;
		this.searchNodePointer = new AVLNode();
		this.size = 1;
	}

	public AVLTree() {
		this.root = null;
		this.searchNodePointer = null;
		this.size = 0;
	}

	/**
	 * public boolean empty()
	 *
	 * Returns true if and only if the tree is empty.
	 *
	 */
	public boolean empty() { // complexity O(1)
		if (this.root == null) {
			return true;
		}
		return false; // to be replaced by student code
	}

	/**
	 * public String search(int k)
	 *
	 * Returns the info of an item with key k if it exists in the tree. otherwise,
	 * returns null.
	 */
	public String search(int k) // complexity O(log(n))
	{
		if (this.root == null) { // empty tree
			return null;
		}
		if (this.root.getKey() == k) { // found
			searchNodePointer = this.root; /////////////////////////////////line added
			return this.root.info;
		}
		if (this.root.getKey() > k) { // go right
			if (this.root.getLeft().getHeight() == -1) {
				searchNodePointer = this.root;
				return null;
			}
			AVLTree LeftTree = new AVLTree((AVLTree.AVLNode) this.root.getLeft()); // Left sub-tree
			String result = LeftTree.search(k);
			this.searchNodePointer = LeftTree.searchNodePointer; // update end of tree to enable insertion
			return result;
		} else { // go left
			if (this.root.getRight().getHeight() == -1) {
				searchNodePointer = this.root;
				return null;
			}
			AVLTree rightTree = new AVLTree((AVLTree.AVLNode) this.root.getRight()); // right sub-tree
			String result = rightTree.search(k);
			this.searchNodePointer = rightTree.searchNodePointer; // update end of tree to enable insertion
			return result;
		}
		// to be replaced by student code
	}

	/**
	 * public int insert(int k, String i)
	 *
	 * Inserts an item with key k and info i to the AVL tree. The tree must remain
	 * valid, i.e. keep its invariants. Returns the number of re-balancing
	 * operations, or 0 if no re-balancing operations were necessary. A
	 * promotion/rotation counts as one re-balance operation, double-rotation is
	 * counted as 2. Returns -1 if an item with key k already exists in the tree.
	 */
	public int insert(int k, String i) {
		if (this.empty()) { // first insertion
			this.root = new AVLNode(k, i);
			this.root.setHeight(0);
			this.root.setRealNode();
			this.size += 1;
			return 0;
		}
		int cnt = 0;
		String searchResult = this.search(k);
		if (searchResult != null) { // already exist
			return -1;
		}
		this.size += 1; //node inserted --> size increased
		if (k > searchNodePointer.getKey()) { // new node is right child
			searchNodePointer.setRight(new AVLNode(k, i)); 
			this.searchNodePointer.getRight().setHeight(0); 
			this.searchNodePointer.getRight().setParent(this.searchNodePointer); // connect to father
			searchNodePointer.getRight().setRealNode(); //create virtual sons
		} else { //symetric case
			searchNodePointer.setLeft(new AVLNode(k, i));
			this.searchNodePointer.getLeft().setHeight(0);
			this.searchNodePointer.getLeft().setParent(this.searchNodePointer);
			searchNodePointer.getLeft().setRealNode();
		}
		if (this.searchNodePointer.getHeight() == 0) { // case A: the parent is a leaf
			this.searchNodePointer.setHeight(1 + Math.max(this.searchNodePointer.getLeft().getHeight(),
					this.searchNodePointer.getRight().getHeight())); //promote father
			cnt = this.searchNodePointer.heightCheckInsert(); //check balance
		} else { // case B: the parent is unaric
			if (this.searchNodePointer.getKey() > k) { 
				this.searchNodePointer.getRight().setHeight(0); 
				cnt = this.searchNodePointer.heightCheckInsert(); //check balance
			} else {
				this.searchNodePointer.getLeft().setHeight(0); 
				cnt = this.searchNodePointer.heightCheckInsert(); //check balance

			}
			while (this.searchNodePointer.parent != null) { // keep promoting
				this.searchNodePointer.parent
						.setHeight(1 + Math.max(this.searchNodePointer.getParent().getRight().getHeight(),
								this.searchNodePointer.getParent().getLeft().getHeight())); //father rank update
				cnt += this.searchNodePointer.parent.heightCheckInsert(); //check balance
				this.searchNodePointer = this.searchNodePointer.parent; //update pointer going up
			}
		}
		while (this.root.getParent() != null) { //adjust root if necessary
			this.root = (AVLTree.AVLNode) this.root.getParent();
		}
		return cnt; // to be replaced by student code
	}

	/**
	 * public int delete(int k)
	 *
	 * Deletes an item with key k from the binary tree, if it is there. The tree
	 * must remain valid, i.e. keep its invariants. Returns the number of
	 * re-balancing operations, or 0 if no re-balancing operations were necessary. A
	 * promotion/rotation counts as one re-balance operation, double-rotation is
	 * counted as 2. Returns -1 if an item with key k was not found in the tree.
	 */
	public int delete(int k) {
		int cnt = 0; 
		String searchResult = this.search(k);
		if (searchResult == null) { // already exist
			return -1;
		}
		if (!this.searchNodePointer.left.isRealNode()) {//this is unaric or leaf
			if (this.searchNodePointer.parent.key > this.searchNodePointer.key) { //this is left child
				this.searchNodePointer.parent.left = this.searchNodePointer.right;
				this.searchNodePointer.right.parent = this.searchNodePointer.parent;
			}else { //this is right child
				this.searchNodePointer.parent.right = this.searchNodePointer.right;
				this.searchNodePointer.right.parent = this.searchNodePointer.parent;
			}
		this.searchNodePointer = this.searchNodePointer.parent;
		while (this.searchNodePointer != null) { //check all the way up
			this.searchNodePointer.rank = 1 + Math.max(this.searchNodePointer.left.rank , this.searchNodePointer.right.rank);
			cnt += this.searchNodePointer.heightCheckDelete();
			if (this.searchNodePointer.parent == null) {
				this.root = this.searchNodePointer;
			}
			this.searchNodePointer = this.searchNodePointer.parent;
		}
		this.size -= 1;
		return cnt;
		}else { //this is internal node
			AVLNode predecessor = this.searchNodePointer.getPredecessor();
			int newKey = this.searchNodePointer.getPredecessor().getKey(); 
			String newInfo = this.searchNodePointer.getPredecessor().getValue(); //keep predecessor fields
			cnt += this.delete(newKey); // delete predecessor and check way up
			String originalPointer = this.search(k); // point to the original node
			this.searchNodePointer.key = newKey;
			this.searchNodePointer.info = newInfo; // replace current values with predecessor's values	
		}
		return cnt; // to be replaced by student code
	}

	/**
	 * public String min()
	 *
	 * Returns the info of the item with the smallest key in the tree, or null if
	 * the tree is empty.
	 */
	public String min() { // complexity O(log(n))
		
		if (this.empty()) {
			return null;
		}
		if (this.root.getLeft().getHeight() == -1) {
			return this.root.getValue();
		}
		AVLTree LeftTree = new AVLTree((AVLTree.AVLNode) this.root.getLeft()); // Left sub-tree
		return LeftTree.min(); // to be replaced by student code
	}

	/**
	 * public String max()
	 *
	 * Returns the info of the item with the largest key in the tree, or null if the
	 * tree is empty.
	 */
	public String max() { // complexity O(log(n))
		if (this.empty()) {
			return null;
		}
		if (this.root.getRight().getHeight() == -1) {
			return this.root.getValue();
		}
		AVLTree rightTree = new AVLTree((AVLTree.AVLNode) this.root.getRight()); // right sub-tree
		return rightTree.max(); // to be replaced by student code
	}

	/**
	 * public int[] keysToArray()
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty array
	 * if the tree is empty.
	 */
	public int[] keysToArray() { // complexity O(n) ---> no edge is visited more then twice
		if (this.empty()) {
			return new int[0];
		}
		int[] resultArray = new int[this.size];
		AVLNode hiker = this.root; //pointer
		while (hiker.left != null) { //get min
			hiker = hiker.left;
		}
		hiker = hiker.parent;
		for (int i=0; i<this.size; i++) { // tree traverse
			resultArray[i] = hiker.key;
			if (i==this.size-1) {
				break;
			}
			hiker = hiker.getSuccessor();
		}
		
		return resultArray; // to be replaced by student code
	}

	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty.
	 */
	public String[] infoToArray() { // complexity O(n) ---> no edge is visited more then twice
		if (this.empty()) {
			return new String[0];
		}
		String[] resultArray = new String[this.size];
		AVLNode hiker = this.root; //pointer
		while (hiker.left != null) { //get min
			hiker = hiker.left;
		}
		hiker = hiker.parent;
		for (int i=0; i<this.size; i++) { // tree traverse
			resultArray[i] = hiker.getValue();
			if (i==this.size-1) {
				break;
			}
			hiker = hiker.getSuccessor();
		}
		
		return resultArray; // to be replaced by student code
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 */
	public int size() {
		return this.size; // to be replaced by student code
	}

	public void print(IAVLNode node, int level) {
		System.out.println("Level: " + level + " , " + node.getKey() + " , " + node.getValue());
		if (node.getLeft() != null)
			print(node.getLeft(), level + 1);
		if (node.getRight() != null)
			print(node.getRight(), level + 1);
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty
	 */
	public IAVLNode getRoot() {
		if (this.empty()) {
			return null;
		}
		return this.root;
	}

	/**
	 * public AVLTree[] split(int x)
	 *
	 * splits the tree into 2 trees according to the key x. Returns an array [t1,
	 * t2] with two AVL trees. keys(t1) < x < keys(t2).
	 * 
	 * precondition: search(x) != null (i.e. you can also assume that the tree is
	 * not empty) postcondition: none
	 */
	public AVLTree[] split(int x) {
		return null;
	}

	/**
	 * public int join(IAVLNode x, AVLTree t)
	 *
	 * joins t and x with the tree. Returns the complexity of the operation
	 * (|tree.rank - t.rank| + 1).
	 *
	 * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be
	 * empty (rank = -1). postcondition: none
	 */
	
	//AUXILIARY function
	IAVLNode x = new AVLNode(25,"joiner");////////for testing
	public void printerJoin(AVLTree otherTree) {
		this.join(x, otherTree);
	}

	public int join(IAVLNode x, AVLTree t) {
		if (this.empty() && t.empty()) { //both trees are empty
			this.insert(x.getKey(),x.getValue());
			return 0;
		}
		AVLNode newX = ((AVLTree.AVLNode) x);
		if (this.empty()) {
			int result = t.root.rank + 1;
			t.insert(x.getKey(),x.getValue());
			return result;
		}
		if (t.empty()) {
			int result = this.root.rank + 1;
			this.insert(x.getKey(),x.getValue());
			return result;
		}
		
		AVLNode highTreeNode = t.root;
		AVLNode shortTreeNode = this.root;
		if (t.root.rank < this.root.rank) { //myTree tree is higher
			highTreeNode = this.root;
			shortTreeNode = t.root;
		} 
		int result = highTreeNode.rank - shortTreeNode.rank + 1;
		while (highTreeNode.rank > shortTreeNode.rank) {
			if (highTreeNode.key > newX.key) { //go down right
				highTreeNode = highTreeNode.left;
			}else { //go down left
				highTreeNode = highTreeNode.right;
			}
		} 
		if(highTreeNode.parent==null) {//both trees have same rank
			if (highTreeNode.key>newX.key) {
				newX.right = highTreeNode;
				newX.left = shortTreeNode;
			}else {
				newX.left = highTreeNode;
				newX.right= shortTreeNode;
			}
		}
		else if (highTreeNode.parent.key > newX.key) { //high tree has higher keys
			highTreeNode.parent.left = newX;
			newX.parent = highTreeNode.parent;
			newX.right = highTreeNode;
			highTreeNode.parent = newX;
			newX.left = shortTreeNode;
			newX.rank = 1 + Math.max(newX.left.rank , newX.right.rank);
			shortTreeNode.parent = newX; // connections done
		} else { //short tree has higher keys
			highTreeNode.parent.right = newX;
			newX.parent = highTreeNode.parent;
			newX.left = highTreeNode;
			highTreeNode.parent = newX;
			newX.right = shortTreeNode;
			newX.rank = 1 + Math.max(newX.left.rank , newX.right.rank);
			shortTreeNode.parent = newX; // connections done
		}
		AVLNode climber = new AVLNode();
		if (newX.parent!=null){ //check if rotations needed
			climber = newX.parent;
			while (climber != null) {
				climber.heightCheckInsert();
				if (climber.parent == null) {
					break; //climber is root
				}
				climber = climber.parent;
		}
		}else { //newX will be root
			climber = newX;
		}
		this.root = climber;
		print(climber, 0);//////for testing
		return result;
	}

	/**
	 * public interface IAVLNode ! Do not delete or modify this - otherwise all
	 * tests will fail !
	 */
	public interface IAVLNode {
		public int getKey(); // Returns node's key (for virtual node return -1).

		public String getValue(); // Returns node's value [info], for virtual node returns null.

		public void setLeft(IAVLNode node); // Sets left child.

		public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.

		public void setRight(IAVLNode node); // Sets right child.

		public IAVLNode getRight(); // Returns right child, if there is no right child return null.

		public void setParent(IAVLNode node); // Sets parent.

		public IAVLNode getParent(); // Returns the parent, if there is no parent return null.

		public boolean setRealNode(); // Returns True if this is a non-virtual AVL node.

		public void setHeight(int height); // Sets the height of the node.

		public int getHeight(); // Returns the height of the node (-1 for virtual nodes).
	}

	/**
	 * public class AVLNode
	 *
	 * If you wish to implement classes other than AVLTree (for example AVLNode), do
	 * it in this file, not in another file.
	 * 
	 * This class can and MUST be modified (It must implement IAVLNode).
	 */
	public class AVLNode implements IAVLNode {
		public String info = "\0";
		public int key = -1;

		public AVLNode(int key, String info) {
			this.info = info;
			this.key = key;
		}

		public AVLNode() { // virtual node
			this.info = null;
			this.key = -1;
			this.rank = -1;
		}

		private AVLNode right = null;
		private AVLNode left = null;
		private AVLNode parent = null;
		private AVLNode searchNodePointer = null;
		private int rank = -1;

		public int getKey() {
			return this.key; // to be replaced by student code
		}

		public String getValue() {
			return this.info; // to be replaced by student code
		}

		public void setLeft(IAVLNode node) {
			this.left = (AVLNode) node;
			return; // to be replaced by student code
		}

		public IAVLNode getLeft() {
			return this.left; // to be replaced by student code
		}

		public void setRight(IAVLNode node) {
			this.right = (AVLNode) node;
			// to be replaced by student code
		}

		public IAVLNode getRight() {
			return this.right; // to be replaced by student code
		}

		public void setParent(IAVLNode node) {
			this.parent = (AVLNode) node;
			return; // to be replaced by student code
		}

		public IAVLNode getParent() {
			return this.parent; // to be replaced by student code
		}

		public boolean setRealNode() {
			if (this.rank != -1) {
				this.right = new AVLNode();
				this.right.parent = this;
				this.left = new AVLNode();
				this.left.parent = this;
				return true; // to be replaced by student code
			} else {
				return false;
			}
		}
		
		public boolean isRealNode() {
			if (this.rank != -1) {
				return true;
			}
			return false;
		}

		public void setHeight(int height) {

			this.rank = height;
			return; // to be replaced by student code
		}

		public int getHeight() {
			return this.rank; // to be replaced by student code
		}

		public int heightCheckInsert() { // what error case
			int cnt = 0;
			int rankCurr = this.rank;
			int BF = this.getBF();
			if (((BF == 1) || (BF == -1)) && (this.parent != null)) { // rank difference 0,1 --->
																							// promote parent
				this.parent.setHeight(rankCurr + 1);
				cnt += this.parent.heightCheckInsert();
			} else if (BF == 2) { // left imbalance
				if (this.left.getBF() == -1) { // left then right rotation
					cnt += 2;
					rotateLeft(this.left.right);
					rotateRight(this.left);
				}
				else { // right rotation
					cnt += 1;
					rotateRight(this.left);

				}
			} else if (BF == -2) { // right imbalance
				if ((this.right.getBF() == 1)) {// right then left rotation
					cnt += 2;
					rotateRight(this.right.left);
					rotateLeft(this.right);
				}
				else { // left rotation
					cnt += 1;
					rotateLeft(this.right);
				}
			}
			return cnt;
		}
		
		public int heightCheckDelete() {
			int cnt = 0;
			int rankCurr = this.rank;
			int BF = this.getBF();
			if (BF == 2) { //left imbalance
				if (this.left.getBF() == -1) { //left then right
					rotateLeft(this.left.right);
					rotateRight(this.left);
					cnt += 2;
				}else { //BF=0 or BF=1 --> only right rotation
					rotateRight(this.left);
					cnt += 1;
				}
				return cnt;
			}else if (BF == -2) {// right imbalance
				if (this.right.getBF() == 1) { // right then left
					rotateRight(this.right.left);
					rotateLeft(this.right);
					cnt += 2;
				}else {//BF=0 or BF=-1 --> only left rotation
					rotateLeft(this.right);
					cnt += 1;
				}
				return cnt;
				}else { //continue checking upwards
					return cnt;
				}
			}

		private int getBF() {
			if (this.rank == -1) {
				return -3;
			}
			return this.left.rank - this.right.rank;
		}

		private void rotateRight(AVLNode son) {
			AVLNode greatGrandpa = son.parent.parent; // save top
				
			son.parent.left = son.right; // turn left sub-tree into father's right-tree
			son.right.parent = son.parent; // turn former son into grandson
			son.right = son.parent; // turn son to father
			son.right.parent = son;// turn father to son
			son.parent = greatGrandpa; // connect to top ---> nodes are placed
			son.right.setHeight(1 + Math.max(son.right.right.rank, son.right.left.rank)); // demote
			if (greatGrandpa != null) { 
				if (greatGrandpa.key>son.key)
					greatGrandpa.left = son;
				else
					greatGrandpa.right = son;

			}
		}

		private void rotateLeft(AVLNode son) {
			AVLNode greatGrandpa = son.parent.parent; // save top
			son.parent.right = son.left; // turn left sub-tree into father's left-tree
			son.left.parent = son.parent; // turn former son into grandson
			son.left = son.parent; // turn son to father
			son.left.parent = son;// turn father to son
			son.parent = greatGrandpa; // connect to top ---> nodes are placed
			son.left.setHeight(1 + Math.max(son.left.right.rank, son.left.left.rank)); // demote
			if (greatGrandpa != null) { 
				if (greatGrandpa.key>son.key)
					greatGrandpa.left = son;
				else
					greatGrandpa.right = son;
			}
		}
		
		private AVLNode getPredecessor() {
			AVLNode tmp = new AVLNode(this.getKey(),"");
			tmp.parent = this.parent;
			tmp.left = this.left;
			if (tmp.getLeft() == null) {
				while (tmp.parent.key > tmp.key) { // this is left child --> predecessor is up
					tmp = tmp.parent;
				}
				return tmp.parent;
			}else { //this has a left child --> predecessor is down 
				tmp = tmp.left;
				while (tmp.right.key != -1) {
					tmp = tmp.right;
				}
				return tmp;
			}
		}
		
		private AVLNode getSuccessor() {
			AVLNode tmp = new AVLNode(this.getKey(),"");
			tmp.parent = this.parent;
			tmp.right = this.right;
			if (tmp.parent==null) {
				tmp=tmp.right;
				while (tmp.left.rank!=-1) {
					tmp=tmp.left;
				}
				return tmp;
			}
			if (tmp.getRight().getHeight() == -1) {
				while (tmp.parent.key < tmp.key) { // this is right child --> successor is up
					tmp = tmp.parent;
				}
				return tmp.parent;
			}else { //this has a right child --> successor is down 
				tmp = tmp.right;
				while (tmp.left.key != -1) {
					tmp = tmp.left;
				}
				return tmp;
			}
		}
	
	
	}
	
}