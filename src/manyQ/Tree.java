package manyQ;

public class Tree {
	
	TreeNode root;	//first node
	TreeNode curr;	//tracker child
	TreeNode prev;	//tracker
	int swing;		//child's direction from parent
	int state;		//state machine tracker
	int empty;		//new tree or developed tree
	String next;	//tracker of the new nodes value
	
	public Tree() {
		
		root = new TreeNode();
		empty = 0;
		state = 0;
		swing = 0;
		
	}
	
}
