package homework07;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

public class Tree
{
	public Node root;
	
	/**
	 * Constructor reads in the data from the scanner parameter and builds the tree into 'root'
	 * 
	 * @param treeData	a scanner parameter
	 */
	public Tree(Scanner treeData)
	{
		if(!treeData.hasNext())
			return;
		String firstString = treeData.next();			// get the first opening tag string
		String openingTag = firstString.substring(1);	// removes the "<" and doesn't include data value
		root = new Node(treeData, null, openingTag);
	}

	/**
	 * This method computes the width of the entire tree by recursively computing the width of each node
	 * 
	 * @param g  graphics object
	 * @return int the width of the tree
	 */
	public int getWidth(Graphics g)
	{
		return root.getWidth(g);			// gets the width of the root node (including children)
	}
	
	/**
	 * This method computes the height of the entire tree by recursively computing the height of each node
	 * 
	 * @param g	 graphics object
	 * @return int the height of the tree
	 */
	public int getHeight(Graphics g)
	{
		return root.getHeight(g);			// gets the height of the root node (including children)
	}
	
	/**
	 * Draws the tree nodes
	 * 
	 * @param g Graphics object
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 */
	public void drawNodes(Graphics g, int x, int y)
	{
		root.drawNodes(g, x, y);	// calls the drawNodes method from Node class
	}
	
	/**
	 * Draws the tree edges
	 * 
	 * @param g Graphics object
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 */
	public void drawEdges(Graphics g, int x, int y)
	{
		root.drawEdges(g, x, y);	// calls the drawEdges method from Node class
	}

	/**
	 * Node class to represent tree nodes in our tree object
	 * 
	 * @author Basil Vetas
	 * @date April 29 2014
	 */
	public class Node
	{
		Node parent;				// parent node passed in when node is created
		String tag;					// the opening tagged passed in when node is created
		String data;				// the data corresponding to the particular opening tag 
		int nodeWidth;				// this width that this node must be with subtrees
		int nodeHeight;				// this height that this node must be with subtrees
		final int edgeHeight;		// the vertical height of the edges connecting this node to its children
		ArrayList<Node> children;	// array list of children nodes of 'this' node
		
		/**
		 * Constructor to initialize variables and create the tree by recursively adding the nodes of subtrees
		 * 
		 * @param dataSet	the scanner we use to scan in the data
		 * @param parent	the parent node of this node
		 * @param openTag	the tag associated with this node & data value
		 */
		public Node(Scanner dataSet, Node parent, String openTag)
		{
			// initialize variables
			this.parent = parent;						// parent of this node
			this.tag = openTag;							// gets the opening tag
				//System.out.println("tag: " + tag);
			String dataVal = dataSet.next();		// iterate to next string (should be a data value)
			this.data = getDataValue(dataVal);			// get the value associated with that opening tag
				//System.out.println("data: " + data);
			this.children = new ArrayList<Node>();

			addChildren(dataSet);					// read in data and recursively get children subtrees
			//catch(StackOverflowError e){}
			//catch(IndexOutOfBoundsException e){}
			
			// give the edges a random height, for fun
			double random = Math.random()*100;
			int randomHeight = (int) random + 20; 
			this.edgeHeight = randomHeight;
			
			//System.out.println("Children: " + children.toString());
		}
		
		/**
		 * This method computes the width of a given node plus the widths of nodes below it
		 * 
		 * @param g	graphics object
		 * @return int	the width of the data value and children
		 */
		public int getWidth(Graphics g)
		{
			for(int i = 0; i < children.size(); i++) 	// for each child node in subtree of 'this' node
				children.get(i).getWidth(g);			// recursively calculate the width down to bottom
			
			int dataWidth = g.getFontMetrics().stringWidth(data) + 40;	// length of the string plus a buffer (in pixels)
			int subTreeWidth = 0;										// width of all the nodes in the subtree
			
			for(int i = 0; i < children.size(); i++)			// for each child, get the width
				subTreeWidth += children.get(i).nodeWidth;		// add child width to subtree width
			
			nodeWidth = Math.max(dataWidth, subTreeWidth);		// width is whichever is longer set as width

			return nodeWidth; 	// return the total width
		}
		
		/**
		 * This method computes the height of this node by adding up the heights of its children below it
		 * 
		 * @param g graphics object
		 * @return int the height of the data value and children
		 */
		public int getHeight(Graphics g)
		{	
			for(int i = 0; i < children.size(); i++)	// for each child node in subtree of 'this' node
				children.get(i).getHeight(g);			// recursively calculate the height down to bottom

			int dataHeight = g.getFontMetrics().getHeight();	// height of the font of the string
			
			nodeHeight = dataHeight + edgeHeight;				// set height of node to height of data plus edge
			for(int i = 0; i < children.size(); i++)			// for each child, get the height
			{
				int newHeight = dataHeight + children.get(i).nodeHeight + edgeHeight; // get the height of this child plus the edge
			 	nodeHeight = Math.max(newHeight, nodeHeight);						  // set node height to whichever is higher
			}

			return nodeHeight;	// return the total height
		}
		
		/**
		 * This class draws a node and its subtree of children nodes. It takes a graphics object
		 * as a parameter, and also takes two int parameters that represent the initial x and y coordinates
		 * relative to where we want to begin drawing the nodes. Note: For this class I loosely referenced 
		 * code design from a similar tree visualizer from a CS 2420 Summer 2010 example class. 
		 * 
		 * @param g	Graphics object
		 * @param x the starting x coordinate (the left wall of the node)
		 * @param y	the starting y coordinate (the top wall of the node) 
		 */
		public void drawNodes(Graphics g, int x, int y)
		{
			int dataWidth = g.getFontMetrics().stringWidth(data);	// length of the string in just this node				
			int dataHeight = g.getFontMetrics().getHeight();		// height of the font of the string		
			
			int xRoot = x + nodeWidth/2 - dataWidth/2;	// x position of root
	        int yRoot = y + dataHeight + 10; 			// y position of root
	        int xPos = x;	// x position of any given node
	        int yPos = y;	// y position of any given node
	        
	        // change node colors for fun
	        int red = (int) nodeWidth%255 + 1;
	        int green = (int) edgeHeight%255 + 1;
	        int blue = (int) nodeHeight%255 + 1;
	        final Color RANDOM = new Color(red, green, blue);
	        
            g.setColor(Color.getColor(data, RANDOM));
            // draw the node shape
            g.fillRoundRect(xRoot - 15, yRoot - 15, dataWidth + 10, dataHeight + 5, 30, 30);
            
            g.setColor(Color.BLACK);
            // draw the node border
            g.drawRoundRect(xRoot - 15, yRoot - 15, dataWidth + 10, dataHeight + 5, 30, 30);
            
            // draw the data into the node
            g.drawString(data, xRoot - 7, yRoot);
            
            for (int i = 0; i < children.size(); i++)		// recursively draw nodes for all the children
            {
            	yPos = y + dataHeight + edgeHeight;			// move yPos to proper position
                children.get(i).drawNodes(g, xPos, yPos);	// draw child node at that xPos and yPos
                xPos += children.get(i).nodeWidth;			// add to width for the next child
            }
		}
		
		/**
		 * This class will draw the edges that connect a node to its children. It takes a graphics object
		 * as a parameter, and also takes two int parameters that represent the initial x and y coordinates
		 * relative to where we want to begin drawing the edges. Note: For this class I loosely referenced 
		 * code design from a similar tree visualizer from a CS 2420 Summer 2010 example class.  
		 * 
		 * @param g	graphics object
		 * @param x the starting x coordinate (the left wall of the node)
		 * @param y the starting y coordinate (the top wall of the node)
		 */
		public void drawEdges(Graphics g, int x, int y)
		{
			//int dataWidth = g.getFontMetrics().stringWidth(data);	// length of the string in just this node				
			int dataHeight = g.getFontMetrics().getHeight();		// height of the font of the string		
			
	        int yRoot = y + dataHeight + 10; 	// y position of root
			int xPos = x;	// x position of any given node
			int yPos = 0;	// y position of any given node
			
            for (int i = 0; i < children.size(); i++)	// for each child of this node
            {
            	yPos = y + 2*dataHeight + edgeHeight;	// set correct yPos
            	
            	// draw and edge from this node to its children
                g.drawLine(x + nodeWidth/2 - 8, yRoot, xPos - 8 + children.get(i).nodeWidth/2, yPos);
                xPos += children.get(i).nodeWidth;		// add to width for the next child
            }
            
            xPos = x;	// return x to the far left wall
            for (int i = 0 ; i < children.size(); i++)		// recursively draw edges for all the children 
            {
            	yPos = y + dataHeight + edgeHeight;			// move yPos to proper position
                children.get(i).drawEdges(g, xPos, yPos);	// draw child edges starting at that xPos and yPos
                xPos += children.get(i).nodeWidth;			// add to width for the next child
            }
		}
		
		/**
		 * This method is used to recursively add the children of the current node by reading in the following
		 * strings from the scanner.  This method will recursively create a new node to add to the children
		 * array of "this" node if a new opening tag is found. Thus this method actually builds the tree. 
		 * 
		 * @param Scanner
		 * @param String
		 */
		private void addChildren(Scanner dataSet)
		{
			/* Read in Data */
				
			//find children of this node
			while(dataSet.hasNext())	// if we haven't found the closing tag
            {
				String nextToken = dataSet.next();	// get the next string
				
				if ((nextToken.startsWith("<")) && (!nextToken.startsWith("</")))	// if it is an opening line
                {	
					String openingTag = getOpeningTag(nextToken);			// get the opening tag (remove "<")
					Node child = new Node(dataSet, this, openingTag);   	// create a new node (recursive)
					this.children.add(child);								// add it as a child node of this node 
                }
				else	
				{
					String tagToCheck = getClosingTag(nextToken);	// get the tag value
					if((tag.equals(tagToCheck)))					// if it is corresponding tag
						break;										// break out of the loop
					return;											// return to the parent node
				}
            }
		}
		
		/**
		 * Helper method to get the opening tag of a given openTagString. This method does not check 
		 * that the parameter is actually an opening tag, but assumes it is and treats it as such.   
		 * 
		 * @param openTagString 	the tag to be trimmed 
		 * @return String			the opening tag value
		 */
		private String getOpeningTag(String openTagString)
		{
				return openTagString.substring(1); // gets the value after the "<" (doesn't include the data value)
		}
		
		/**
		 * Helper method to get the closing tag of a given closingTagString. This method does not check 
		 * that the parameter is actually a closing tag, but assumes it is and treats it as such.   
		 * 
		 * @param closingTagString 	the tag to be trimmed 
		 * @return String			the closing tag value
		 */
		private String getClosingTag(String closingTagString)
		{
			String tempTag = closingTagString.substring(2);		// removes the "</"
			return tempTag.substring(0, (tempTag.length()-1));	// remove the ">" close bracket
		}
		
		/**
		 * Helper method to get the data value of a given valueString. This method does not check 
		 * that the parameter is actually a value string, but assumes it is and treats it as such.   
		 * 
		 * @param valueString 	the string to be trimmed 
		 * @return String		the value corresponding to some opening tag
		 */
		public String getDataValue(String valueString)
		{
			return valueString.substring(0, (valueString.length()-1)); // remove the ">" close bracket
		}
		
		/**
		 * Accessor method for data value
		 * 
		 * @return data the data value of the node
		 */
		public String getData()
		{
			return this.data;	// return data value
		}
		
		/**
		 * Helper method to return the tag/ value pair in this node as a string
		 * 
		 * @return String the tag/ value pair in this node
		 */
		public String toString()
		{
			return  "tag: " + tag + ", data: " + data;
		}
	}
}


