//import AVLTree.AVLNode;
//import AVLTree.IAVLNode;

/**
*
* AVLTree
*
* An implementation of a AVL Tree with
* distinct integer keys and info.
*
*/

public class AVLTree {
	
	
	private int height = 0;
	public AVLNode root = null;
	public AVLTree.AVLNode searchNodePointer;
	public AVLTree(AVLNode myRoot) {
		this.root = myRoot;
		this.searchNodePointer = new AVLNode();
	}
	public AVLTree() {
		this.root = null;
		this.searchNodePointer = null;
	}

 /**
  * public boolean empty()
  *
  * Returns true if and only if the tree is empty.
  *
  */
 public boolean empty() { //complexity O(1)
	  if (this.root == null) {
		  return true;
	  }
   return false; // to be replaced by student code
 }

/**
  * public String search(int k)
  *
  * Returns the info of an item with key k if it exists in the tree.
  * otherwise, returns null.
  */
 public String search(int k) //complexity O(log(n))
 {
	  if (this.root == null) { //empty tree
		  return null;
	  }
	  if (this.root.key == -1) { //virtual node ---> end of tree
		  this.searchNodePointer = this.root; //mark end of tree to enable insertion
		  return null;
	  }
	  if (this.root.key == k) { //found
		  return this.root.info;
	  }
	  if (this.root.key > k) { // go right
		  AVLTree rightTree = new AVLTree(this.root.right); //right sub-tree
		  String result = rightTree.search(k);
		  this.searchNodePointer = rightTree.searchNodePointer; //update end of tree to enable insertion
		  return result;
	   }else{ //go left
		  AVLTree leftTree = new AVLTree(this.root.left); //left sub-tree
		  String result = leftTree.search(k);
		  this.searchNodePointer = leftTree.searchNodePointer; //update end of tree to enable insertion
		  return result;
	  }
	 // to be replaced by student code
 }

 /**
  * public int insert(int k, String i)
  *
  * Inserts an item with key k and info i to the AVL tree.
  * The tree must remain valid, i.e. keep its invariants.
  * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
  * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
  * Returns -1 if an item with key k already exists in the tree.
  */
  public int insert(int k, String i) {
	   if (this.empty()) { //first insertion
		   this.root = new AVLNode(k,i);
		   this.root.setHeight(0);
		   this.root.isRealNode();
		   return 0;
	   }
	   int cnt = 0;
	   String searchResult = this.search(k);
	   if (searchResult != null) { //already exist
		   return -1;
	   }
	   this.searchNodePointer.key = k;
	   this.searchNodePointer.info = i;
	   this.searchNodePointer.isRealNode();
	   this.searchNodePointer.setLeft(new AVLNode());
	   this.searchNodePointer.setRight(new AVLNode());
	   this.searchNodePointer.setHeight(0);
	   if (this.searchNodePointer.parent.rank == 0) { //case A: the parent is a leaf
		   this.searchNodePointer.parent.setHeight(1 + Math.max(this.searchNodePointer.parent.left.rank , this.searchNodePointer.parent.right.rank));
		   cnt = this.searchNodePointer.parent.heightCheck();
	   }else { //case B: the parent is unaric
		   this.searchNodePointer.rank = 0;
	   }
	  return cnt;	// to be replaced by student code
  }
  

 /**
  * public int delete(int k)
  *
  * Deletes an item with key k from the binary tree, if it is there.
  * The tree must remain valid, i.e. keep its invariants.
  * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
  * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
  * Returns -1 if an item with key k was not found in the tree.
  */
  public int delete(int k)
  {
	   return 421;	// to be replaced by student code
  }

  /**
   * public String min()
   *
   * Returns the info of the item with the smallest key in the tree,
   * or null if the tree is empty.
   */
  public String min()
  {
	   return "minDefaultString"; // to be replaced by student code
  }

  /**
   * public String max()
   *
   * Returns the info of the item with the largest key in the tree,
   * or null if the tree is empty.
   */
  public String max()
  {
	   return "maxDefaultString"; // to be replaced by student code
  }

 /**
  * public int[] keysToArray()
  *
  * Returns a sorted array which contains all keys in the tree,
  * or an empty array if the tree is empty.
  */
 public int[] keysToArray()
 {
       return new int[33]; // to be replaced by student code
 }

 /**
  * public String[] infoToArray()
  *
  * Returns an array which contains all info in the tree,
  * sorted by their respective keys,
  * or an empty array if the tree is empty.
  */
 public String[] infoToArray()
 {
       return new String[55]; // to be replaced by student code
 }

  /**
   * public int size()
   *
   * Returns the number of nodes in the tree.
   */
  public int size()
  {
	   return 422; // to be replaced by student code
  }
  
  /**
   * public int getRoot()
   *
   * Returns the root AVL node, or null if the tree is empty
   */
  public IAVLNode getRoot()
  {
	   return null;
  }
  
  /**
   * public AVLTree[] split(int x)
   *
   * splits the tree into 2 trees according to the key x. 
   * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
   * 
	* precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
   * postcondition: none
   */   
  public AVLTree[] split(int x)
  {
	   return null; 
  }
  
  /**
   * public int join(IAVLNode x, AVLTree t)
   *
   * joins t and x with the tree. 	
   * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
	*
	* precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
   * postcondition: none
   */   
  public int join(IAVLNode x, AVLTree t)
  {
	   return -1;
  }

	/** 
	 * public interface IAVLNode
	 * ! Do not delete or modify this - otherwise all tests will fail !
	 */
	public interface IAVLNode{	
		public int getKey(); // Returns node's key (for virtual node return -1).
		public String getValue(); // Returns node's value [info], for virtual node returns null.
		public void setLeft(IAVLNode node); // Sets left child.
		public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.
		public void setRight(IAVLNode node); // Sets right child.
		public IAVLNode getRight(); // Returns right child, if there is no right child return null.
		public void setParent(IAVLNode node); // Sets parent.
		public IAVLNode getParent(); // Returns the parent, if there is no parent return null.
		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.
   	public void setHeight(int height); // Sets the height of the node.
   	public int getHeight(); // Returns the height of the node (-1 for virtual nodes).
	}

  /** 
   * public class AVLNode
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in another file. 
   * 
   * This class can and MUST be modified (It must implement IAVLNode).
   */
 public class AVLNode implements IAVLNode{
	  public String info = "\0";
	  public int key = -1;

	public AVLNode(int key , String info) {
		  this.info  = info;
		  this.key = key;
	  }
	  public AVLNode() { //virtual node
		  this.info = null;
		  this.key = -1;
		  this.rank = -1;
	  }
	  
	  
	
	  private AVLNode right = null;
	  private AVLNode left = null;
	  private AVLNode parent = null;
	  private AVLNode searchNodePointer = null;
	  private int rank = -1;
	  
		public int getKey()
		{
			return this.key; // to be replaced by student code
		}
		public String getValue()
		{
			return this.info; // to be replaced by student code
		}
		public void setLeft(IAVLNode node)
		{
			this.left = (AVLNode)node;
			return; // to be replaced by student code
		}
		public IAVLNode getLeft()
		{
			return this.left; // to be replaced by student code
		}
		public void setRight(IAVLNode node)
		{
			this.right = (AVLNode) node;
			// to be replaced by student code
		}
		public IAVLNode getRight()
		{
			return this.right; // to be replaced by student code
		}
		public void setParent(IAVLNode node)
		{
			this.parent = (AVLNode)node;
			return; // to be replaced by student code
		}
		public IAVLNode getParent()
		{
			return this.parent; // to be replaced by student code
		}
		public boolean isRealNode()
		{
			if (this.key != -1) {
				 this.right = new AVLNode();
				 this.right.parent = this;
				 this.left = new AVLNode();
				 this.left.parent = this;
				return true; // to be replaced by student code
			}else {
				return false;
			}
		}
	    public void setHeight(int height) {
	  
	    	this.rank = height;
	      return; // to be replaced by student code
	    }
	    public int getHeight()
	    {
	      return this.rank; // to be replaced by student code
	    }
	    
	    public int heightCheck() { //what error case 
	    	int cnt = 0;
	 	   int rankParent = this.rank;
	 	   int rankRight = this.right.rank;
	 	   int rankLeft = this.left.rank;
	 	   if ((rankParent-rankRight) + (rankParent-rankLeft) == 1) { // rank difference 0,1 ---> promote parent
	 		   this.setHeight(rankParent + 1);
	 	   } 
	 	   else if (this.getBF() == 2) { //left imbalance
	 		   if (this.right.getBF() == 1) { //right rotation
	 			  cnt += 1;
	 			 rotateRight(this);
	 		   }else { // left then right rotation
	 			   cnt += 2;
	 			   rotateLeft(this.right);
	 			   rotateRight(this.parent);
	 		   }
	 	   }else if (this.getBF() == -2){ //right imbalance
	 		  if (this.left.getBF() == 1) { // left rotation
	 			  cnt += 1;
	 			 rotateLeft(this);
	 		  }else {// right then left rotation
	 			  cnt += 2;
	 			  rotateRight(this.left);
	 			  rotateLeft(this.parent);
	 		  }
	 	   }else {
	 		  this.setHeight(rankParent + 1);
	 	   }
	 		  return cnt; 
	    }
	    
	    private int getBF() {
	    	return this.left.rank - this.right.rank;
	    }
	    
	    private void rotateRight(AVLNode son) {
	    	AVLNode greatGrandpa = son.parent.parent; // save top
	    	son.parent.left = son.right; // turn left sub-tree into father's right-tree 
	    	son.right.parent = son.parent; // turn former son into grandson
	    	son.right = son.parent; //turn son to father
	    	son.parent = greatGrandpa; // connect to top ---> nodes are placed
	    	son.right.setHeight(son.right.rank - 1); // demote 
 }
	    
	    private void rotateLeft(AVLNode son) {
	    	AVLNode greatGrandpa = son.parent.parent; // save top
	    	son.parent.right = son.left; // turn right sub-tree into father's left-tree
	    	son.left.parent = son.parent; // turn former son into grandson
	    	son.left = son.parent; //turn son to father
	    	son.parent = greatGrandpa; // connect to top ---> nodes are placed
	    	son.left.setHeight(son.left.rank - 1); // demote 
	    }

 }
}