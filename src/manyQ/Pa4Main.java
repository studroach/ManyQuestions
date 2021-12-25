/////////////////////////////////////////////////////////////////
//Jake Castedo
//CSE 223
//PA3
//May 16, 2019
//This program is like the game 20 questions but without any
//limit to the size of the search tree.	You think of an item or
//thing and answer the questions to see if the computer guesses
//it. Upon a correct guess the program closes with no update,
//but upon failure it will ask what you thought of and a way
//to distinguish it from the thing guessed. After this the 
//program will update and close.

package manyQ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.AbstractButton;

public class Pa4Main {

	public static void main(String[] args) {
		Gooey gui = new Gooey();	//sets gui elements
		Tree tree = new Tree();		//sets the data structure
		Fileio.PlantTree(tree, gui);	//builds the tree from the file
		tree.curr = tree.root;		//sets a pointer in the tree to where we are working
		
		ActionListener step = new ActionListener() {	//on the press of a button
			public void actionPerformed(ActionEvent e) {
				Play(tree, ((AbstractButton) e.getSource()).getText(), gui);	//makes stuff happen
			}
		};
		
		//making buttons work
		gui.yes.addActionListener(step);
		gui.no.addActionListener(step);
		gui.add.addActionListener(step);
		gui.guess.setText(tree.root.val);		//sets initial question
	}

	public static void Play(Tree tree, String envoker, Gooey gui) {	//this is what actually makes the program do anything
		if(envoker.contentEquals("Yes")) {		//yes button
			if(tree.state == 0) {		//if it is in navigation mode
				tree.prev = tree.curr;		//keep track of place in tree
				tree.curr = tree.curr.left;		//keep track of place in tree
				tree.swing = 0;					//keeping track of left or right for prev's child
				if(tree.curr.isAnswer()) {	//if the next line in the tree is an answer
					gui.guess.setText("Were you thinking of a(n) " + tree.curr.val);	//makes a guess at what you were thinking
					tree.state = 1;		//changes the state machine so it can accept if it is wrong or right
					
				}else {		//if the next line in the tree was a question
					gui.guess.setText(tree.curr.val);	//populates the label with the next question
					
				}
				
			}else if(tree.state == 1){	//if it is post guess mode
				gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));	//successful guess no need to update anything.
				//EXITING
			}
			
		}else if(envoker.contentEquals("No")) {	//no button
			if(tree.state == 0) {	//if in navigation mode
				tree.prev = tree.curr;		//tracker
				tree.curr = tree.curr.right;	//tracker
				tree.swing = 1;				//child tracker
				if(tree.curr.isAnswer()) {	//answer line?
					gui.guess.setText("Were you thinking of a(n) " + tree.curr.val);	//makes guess
					tree.state = 1;	//feedback mode
					
				}else {	//was question
					gui.guess.setText(tree.curr.val); //next question
					
				}
				
			}else if(tree.state == 1){	//feedback mode
				gui.guess.setText("Oh no! Enter what you were actually thinking of.");	//asks that you really thought of
				tree.state = 2;	//sets machine to accept input mode
				gui.wrong.setVisible(true);		//unhides input field
				gui.add.setVisible(true);		//unhides input button
			}
			
		}else if(envoker.contentEquals("Submit")) {		//input button
			if(tree.state == 2) {		//accept input mode
				gui.guess.setText("What is a question that a(n) " + gui.wrong.getText() + " is true for and a(n) " + tree.curr.val + " is false for?"); //tells you to formulate a question
				tree.next = gui.wrong.getText();	//tracks answer
				gui.wrong.setText("");		//resets input field
				tree.state = 3;		//sets to update tree mode
				
			}else if(tree.state == 3) {	//update mode
				if(tree.swing == 0) {	//child of prev tracker is left
					//updates tree
					TreeNode temp = tree.curr;
					tree.prev.left = new TreeNode();
					tree.prev.left.val = gui.wrong.getText();
					tree.prev.left.left = new TreeNode();
					tree.prev.left.left.val = tree.next;
					tree.prev.left.right = temp;
					
				}else {					//child of prev tracker is right
					//updates tree
					TreeNode temp = tree.curr;
					tree.prev.right = new TreeNode();
					tree.prev.right.val = gui.wrong.getText();
					tree.prev.right.left = new TreeNode();
					tree.prev.right.left.val = tree.next;
					tree.prev.right.right = temp;
					
				}
				
				Fileio.ChopTree(tree);		//puts the tree back into the file
				gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));	//EXITING
				
			}
			
		}
		
	}
}
