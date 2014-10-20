package homework07;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class allows the user to select a .tree file, and it will draw a visual representation of the contents
 * of the file by displaying both the nodes and edges as what we normally think of as a "tree diagram"
 * 
 * @author 	Basil Vetas
 * @date 	April 29 2014
 */
public class TreeVisualizer extends JPanel 
{
	/**
	 * Main method asks user to choose a .tree file, then constructs a JFrame and calls the TreePanel for
	 * actually drawing the tree
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		/** This if for seleting and reading in the data file*/

		// Creates the GUI to ask user to select a data file
		JFileChooser chooser;
		chooser = new JFileChooser();	
		chooser.setDialogTitle("Please Choose a Data File: ");
	
		// Prevents an error if the user cancels the dialog box
		int selection;
		selection = chooser.showOpenDialog(null);
		
		if(selection == JFileChooser.CANCEL_OPTION)
			return; 	// User canceled the chooser.
		
		// Creates a file to store the data file selected by the user
		File inputFile;
		inputFile = chooser.getSelectedFile();
		Tree root = null;
		
 		try
		{	
 			//Create the scanner and calls a helper method
 			Scanner in = new Scanner(inputFile);
			root = new Tree(in);
				//System.out.println("Tree: " + tree.toString());
		}
		catch(IOException e)
		{
			// This code runs if there is an error
			JOptionPane.showMessageDialog(null, "Could not read the file: " + inputFile);
		}
		
		//System.out.println("Tree: " + root);
		
		//JOptionPane.showMessageDialog(null, "You chose: " + inputFile);

		/** This code builds the frame for the Tree Visualizer GUI*/
		
		// Create a frame to hold the scroll pane.
        JFrame frame = new JFrame ("Tree Visualizer - File Name: " + inputFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout (new BorderLayout());

        // Create our JPanel, set its minimum and preferred size to be 10000, 10000
        TreePanel panel = new TreePanel (root);
        panel.setMinimumSize(new Dimension(10000, 10000));
        panel.setPreferredSize(new Dimension(10000, 10000));

        // Create a menu to reset the frame
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Reset");
        
        // Add the menu to the frame 
        menu.add(item);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        // Give functionality to the "reset" button by adding an action listener 
        item.addActionListener(panel);
        
        // Create a scroll pane to contain our over-sized JPanel.
        JScrollPane pane = new JScrollPane (panel);
        
        // Add it to the center of the content area.
        frame.add (pane, BorderLayout.CENTER);
        
        // Limit the size of the scroll pane.  Otherwise, the frame will try to expand to 
        // accommodate the entire size of the huge panel.
        pane.setPreferredSize(new Dimension(800, 800));
        
        // Register the pane with our panel.
        panel.setEnclosingPane(pane);
       
        // Add self mouse listeners to our panel for debugging.
        panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);  
        
        // Pack the frame and make it visible.
        frame.pack();
        frame.setVisible (true);
	}
}