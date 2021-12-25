package manyQ;

public class TreeNode {
	
	String val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode() {
		left = null;
		right = null;
		
	}
	
	public boolean isAnswer() {	//returns true if node is an answer node
		if(right == null && left == null) {
			return(true);
		}else {
			return(false);
		}
		
	}
	
}
