package manyQ;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.*;

public class Gooey {
	
	JFrame frame;
	JPanel pane;
	JButton yes,no,add;
	JTextField wrong;
	JLabel guess,title;
	
	public Gooey() {
		//initialize the GUI components
		frame = new JFrame("Many Questions");
		pane = new JPanel();
		yes = new JButton("Yes");
		no = new JButton("No");
		add = new JButton("Submit");
		wrong = new JTextField();
		guess = new JLabel();
		title = new JLabel("Think of a Thing");
		
		Init();
	
	}
	
	public void Init() {
		//set up frame
		frame.setVisible(true);
		frame.setSize(900, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pane);
		
		//set up panel
		pane.setLayout(null);
		pane.add(yes);
		pane.add(no);
		pane.add(add);
		pane.add(wrong);
		pane.add(guess);
		pane.add(title);
		
		Layout();	//space everything
		
	}
	
	public void Layout() {
		
		//create custom values
		Border field = BorderFactory.createLineBorder(new java.awt.Color(252, 168, 73), 2);
		Border field2 = BorderFactory.createLineBorder(new java.awt.Color(73, 107, 150), 2);
		Border margin = new EmptyBorder(10,10,10,10);
		Font labelFont = guess.getFont();
		
		//initializes answer box properties
		guess.setBounds(150, 150, 600, 50);
		guess.setBorder(new CompoundBorder(field, margin));
		guess.setBackground(Color.WHITE);
		guess.setOpaque(true);
		guess.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
		
		//initialize the title
		title.setBounds(405, 100, 100, 50);
		
		//initializes the input for wrong guess
		wrong.setBounds(150, 330, 600, 50);
		wrong.setVisible(false);
		wrong.setBorder(new CompoundBorder(field, margin));
		wrong.setBackground(Color.WHITE);
		wrong.setOpaque(true);
		wrong.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
		
		//initialize buttons
		yes.setBounds(230, 240, 120, 50);
		yes.setBackground(new java.awt.Color(123, 173, 196));
		yes.setBorder(field2);
		
		no.setBounds(550, 240, 120, 50);
		no.setBackground(new java.awt.Color(123, 173, 196));
		no.setBorder(field2);
				
		add.setBounds(550, 420, 120, 50);
		add.setBackground(new java.awt.Color(190, 211, 221));
		add.setVisible(false);
		add.setBorder(field2);
				
	}
	
}
