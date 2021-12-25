package manyQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Fileio {
	
	public static Tree PlantTree(Tree tree, Gooey gui) {	//grows the tree from the file
		
		Scanner scanner;	//sets scanner
		File seed = new File("resources/seed.txt");		//sets file
		
		try {	//checks for file
			scanner = new Scanner(seed);
			
		} catch(Exception e) {
			gui.guess.setText("Error:" + e);
			return(null); //no file = null tree
		}
		
		if(scanner.hasNextLine()) {	//if file has content
		Reach(scanner, tree.root, tree);	//recursive method to grow tree
		
		}
		
		scanner.close();	//close scanner ... duh
		return(tree);		//returns the tree
		
	}
	
	public static void Reach(Scanner scanner, TreeNode tn, Tree tree) {	//recursive tree growing
		String bit = scanner.nextLine();	
		if(bit.charAt(0) == 'Q') {	//if next line is question
			tn.val = scanner.nextLine();
			tree.empty = 1;
			tn.left = new TreeNode();
			Reach(scanner,tn.left,tree);
			tn.right = new TreeNode();
			Reach(scanner,tn.right,tree);
			
		}else {		//next line is answer
			tn.val = scanner.nextLine();
			
		}
	}
	
	public static void ChopTree(Tree tree) {	//chops tree back into file format and prints it
		File seed = new File("resources/seed.txt");
		PrintWriter printer = null;
		
		try {
			printer = new PrintWriter(seed);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		printer.print(Printer(tree.root));	//recursive tree nav
		printer.close();
	}
	
	public static String Printer(TreeNode tn) {		//recursive file builder
		if(tn.isAnswer()) {		//node is answer
			return("A:\n" + tn.val);
			
		}else {		//node is question
			return("Q:\n" + tn.val + "\n" + Printer(tn.left) + "\n" + Printer(tn.right));
			
		}
		
	}
	
}
