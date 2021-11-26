import java.util.Arrays;




public class tester {
	
	public static void main(String[] args) {
		AVLTree myTree = new AVLTree();
		/*myTree.insert(35,"String of 35");
		myTree.insert(9,"String of 9");
		myTree.insert(26,"String of 26");
		myTree.insert(6,"String of 6");
		myTree.insert(13,"String of 13");
		myTree.insert(21,"String of 21");
		myTree.insert(30,"String of 30");
		myTree.insert(4,"String of 4");
		myTree.insert(10,"String of 10");
		myTree.insert(15,"String of 15");
		myTree.insert(19,"String of 19");
		myTree.insert(24,"String of 24");
		myTree.insert(28,"String of 28");
		myTree.insert(33,"String of 33");
		myTree.print(myTree.root, 0);
		System.out.println(Arrays.toString(myTree.keysToArray()));
		System.out.println(Arrays.toString(myTree.infoToArray()));
		System.out.println("-----------");	
		myTree.delete(30);
		myTree.print(myTree.root, 0);
		System.out.println(Arrays.toString(myTree.keysToArray()));
		System.out.println(Arrays.toString(myTree.infoToArray()));
		System.out.println("-----------");*/
		
		myTree.insert(2,"String of 2");
		myTree.insert(5,"String of 5");
		myTree.insert(23,"String of 23");
		myTree.insert(14,"String of 14");
		myTree.insert(17,"String of 17");
		myTree.insert(12,"String of 12");
		myTree.insert(8,"String of 8");
		myTree.insert(21,"String of 21");
		myTree.insert(3,"String of 3");
		myTree.insert(18,"String of 18");
		myTree.insert(7,"String of 7");
		myTree.insert(19,"String of 19");
		myTree.insert(11,"String of 11");
		myTree.insert(10,"String of 10");
		myTree.insert(6,"String of 6");
		myTree.insert(50,"String of 50");
		myTree.insert(55,"String of 55");
		myTree.insert(70,"String of 70");
		myTree.insert(60,"String of 60");
		myTree.insert(80,"String of 80");
		myTree.insert(90,"String of 90");
		myTree.insert(95,"String of 95");
		myTree.insert(100,"String of 100");
		myTree.insert(110,"String of 110");
		myTree.insert(120,"String of 120");
		myTree.insert(130,"String of 130");
		myTree.insert(115,"String of 115");
		myTree.insert(135,"String of 135");
		myTree.insert(125,"String of 125");
		myTree.print(myTree.root, 0);
		System.out.println("-----------");
		
		/*AVLTree otherTree = new AVLTree();
		otherTree.insert(33,"String of 33");
		otherTree.insert(51,"String of 51");
		otherTree.insert(42,"String of 42");
		otherTree.insert(29,"String of 29");
		otherTree.insert(49,"String of 49");
		otherTree.insert(34,"String of 34");
		otherTree.insert(56,"String of 56");
		otherTree.insert(32,"String of 32");
		otherTree.insert(38,"String of 38");
		otherTree.insert(44,"String of 44");
		otherTree.insert(41,"String of 41");
		otherTree.insert(39,"String of 39");
		otherTree.print(otherTree.root, 0);
		System.out.println("-----------");	
		//myTree.printerJoin(otherTree);*/
		
		AVLTree otherTree2 = new AVLTree();
		otherTree2.insert(200,"String of 200");
		otherTree2.insert(210,"String of 210");
		otherTree2.insert(190,"String of 190");
		otherTree2.insert(220,"String of 220");
		myTree.printerJoin(otherTree2);
		
		//attempt 1
		IAVLNode x = new AVLNode(25,"joiner");
		myTree.join(x, otherTree2);
		
		
		/*/attempt 2
		IAVLNode y = IAVLNode(26,"joiner2");
		myTree.join(y, otherTree);
		
		//successful attempt
		myTree.printerJoin(otherTree);*/
		
		
	}
}
