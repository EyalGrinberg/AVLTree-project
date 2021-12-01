import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;









public class tester {
	
	

	
	public static void main(String[] args) {
		Random treeSize = new Random();
		int treeSize1 = treeSize.nextInt(100); //size of first tree
		int treeSize2 = treeSize.nextInt(100); //size of second tree
		AVLTree tree1 = new AVLTree();
		AVLTree tree2 = new AVLTree();
		int[] orderOfInsertions1 = new int[treeSize1];
		int[] orderOfInsertions2 = new int[treeSize2];
		int [] bank1= new int[1000];
		int [] bank2= new int[1000];
		for (int i=0; i<treeSize1; i++) { //create first tree
			int key = tester.getRandomInt1(treeSize, bank1); //get random number under 500
			String info = "String of" + key;
			tree1.insert(key, info);
			orderOfInsertions1[i] = key; //keep order of insertions for deeper checking if necessary
		}
		for (int i=0; i<treeSize2; i++) { //create second tree
			int key = tester.getRandomInt2(treeSize, bank2); //get random number above 500
			String info = "String of" + key;
			tree2.insert(key, info);
			orderOfInsertions2[i] = key; //keep order of insertions for deeper checking if necessary
		}
		int[] toCheckOrder1 = tree1.keysToArray(); //tree one result in order
		int[] toCheckOrder2 = tree2.keysToArray(); //tree two result in order
		int n = toCheckOrder1.length;
		int m = toCheckOrder2.length;
		for (int i=0; i<n; i++) {
			if ((i!=n-1)&&(toCheckOrder1[i]>toCheckOrder1[i+1])) { // let me know who is not in order
				System.out.println("node" + i + "not in order");
			}
		}
		System.out.println("---------------Tree1--------------");
		System.out.println("Tree1 order of insertions");
		System.out.println(Arrays.toString(orderOfInsertions1)); //insertion order originally
		System.out.println("Tree1 inserted:");
		System.out.println(Arrays.toString(toCheckOrder1)); //tree one in order
		if (orderOfInsertions1.length!=toCheckOrder1.length) { // if not the same length - some wasn't inserted  - error
			System.out.println((orderOfInsertions1.length - toCheckOrder1.length) + " Missing nodes weren't inserted" );
			}
		if (!tree1.empty()) {
			tree1.print(tree1.root, 0); //checking for imbalance or disconnections
		}
		System.out.println("Tree1 checked for order, connections and BF");
		
		
		
		for (int i=0; i<m; i++) { // let me know who is not in order
			if ((i!=m-1)&&(toCheckOrder2[i]>toCheckOrder2[i+1])) {
				System.out.println("node" + i + "not in order");
			}
		}
		System.out.println("---------------Tree2--------------");
		System.out.println("Tree2 order of insertions");
		System.out.println(Arrays.toString(orderOfInsertions2)); //insertion order originally
		System.out.println("Tree2 inserted:");
		System.out.println(Arrays.toString(toCheckOrder2)); //tree two in order
		if (orderOfInsertions2.length!=toCheckOrder2.length) { // if not the same length - some wasn't inserted  - error
			System.out.println((orderOfInsertions2.length - toCheckOrder2.length) +" Missing node wasn't inserted");
			}
		if (!tree2.empty()) {
			tree2.print(tree2.root, 0); //checking for imbalance or disconnections
		}
		System.out.println("Tree2 checked for order, connections and BF");
		
		int joinKey =  500;
		AVLTree jointRoot = new AVLTree();
		jointRoot.insert(joinKey, "String of" + joinKey);
		tree1.join(jointRoot.root, tree2); //join the trees with 500
		System.out.println("---------------JointTree--------------");
		tree1.print(tree1.root, 0); //check new tree for imbalance or disconnections
		int[] jointOrder = tree1.keysToArray();
		System.out.println(Arrays.toString(jointOrder)); // new tree in order
		int l = jointOrder.length;
		for (int i=0; i<l; i++) {
			if ((i!=l-1)&&(jointOrder[i]>jointOrder[i+1])) { // who is not in order
				System.out.println("nodes" + "index " + (i) + " " + jointOrder[i] + "index " + (i+1) + " " + jointOrder[i] + " not in order");
			}
		}
		if (jointOrder.length!=((toCheckOrder2.length)+(toCheckOrder1.length)+1)) { //are we missing nodes?
			System.out.println(jointOrder.length - ((toCheckOrder2.length)+(toCheckOrder1.length)) +" Missing node weren't inserted");
			}
		System.out.println("jointTree checked for order, connections and BF");
		
		
		System.out.println("---------------After Deletions--------------");
		int z = tree1.size;
		System.out.println("Original Size was " + z);
		tree1.delete(jointOrder[z-2]);
		tree1.delete(jointOrder[(z/2)]);
		tree1.delete(jointOrder[z/4]);
		tree1.delete(jointOrder[z-8]);
		tree1.delete(jointOrder[1]);
		z = tree1.size;
		System.out.println("After 5 deletions size is " + z);
		tree1.print(tree1.root, 0); //check new tree for imbalance or disconnections
		int[] jointAfterDeletions = tree1.keysToArray();
		System.out.println(Arrays.toString(jointAfterDeletions)); // new tree in order
		System.out.println("joint Tree after deletions checked for order, connections and BF");
		
		
		
	}
	
	
	
	
	
	
	
	public static int getRandomInt1(Random randomGen, int[] bank) { //only under 500
		int key = randomGen.nextInt(499);
		while (bank[key]==1) {
			key = randomGen.nextInt(499);	
	}
		bank[key]=1;
		return key;
	}
	public static int getRandomInt2(Random randomGen, int[] bank) { //only above 500
		int key = randomGen.nextInt(1000);
		while ((bank[key]==1)||(key<=500)) {
			key = randomGen.nextInt(1000);	
	}
		bank[key]=1;
		return key;
	}	
	}

