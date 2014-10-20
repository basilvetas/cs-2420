package homework08;

/**
 * Objects of this class represent a set of sortable values.  The set
 * has the following performace characteristics:
 *     
 *     - the set is kept in a binary search tree
 *     
 *     - getting the size of the set - theta(1)
 *     
 *     - adding, removing, or searching for a random element - theta(nlgn)
 *     
 *     - adding, removing, or searching for an element that
 *       immediately follows the previously accessed element - theta(1)
 *     
 * In other words, this set performs very well if additions or
 * removals occur with long sequential runs of ordered data values.    
 *     
 * @author Basil Vetas
 * @version April 1, 2014
 */
public class SpecialtySet<E extends Comparable<E>>
{
    // Instance variables.  Students are allowed only these, do not add or change instance variables.
    
	private Node root;  // The root of the BST
	private int size;   // The number of data elements in the tree

    // Instance methods below.
    
    /**
     * Constructs an empty tree.
     */
    public SpecialtySet()
    {
    	// initialize variables
    	root = null;
    	size = 0;				
    }

    /**
     * Returns the number of elements in this SpecialtySet.
     * 
     * @return a count of the elements in this tree
     */
    public int size ()
    {
        return this.size;  // returns number of elements in the tree
    }
    
    /**
     * Returns the root of this SpecialtySet.
     * 
     * @return value of the root node
     */
    public E getRootValue ()
    {
        return root.data;  // returns the value of the root node
    }

    
    /** Returns 'true' if the specified data is in the tree, false otherwise.
     * 
     * @param data  A data value to search for
     * @return		true iff the data is in the set
     */   
    public boolean contains (E data)
    {
    	if(root == null)
    		return false;
    	return contains(data, root);  					// returns true if the tree contains 'data' and false if not
    }
   
  /**
   * Returns 'true' if the specified data is in the tree, false otherwise.
   * 
   * @param data		the data value we are searching for
   * @param treeNode	the root node of the tree
   * @return			true iff the data is in the set
   */
    private boolean contains (E data, Node treeNode)
    {
    	int direction = getDirection(data, treeNode);		// there is still some error here when for test 7 for when treeNode.data is null

    	if(treeNode != null)								// while there are still nodes in the tree
    	{
    		if(direction == 1)								// if 'data' is greater than the data in the node 
    			return contains(data, treeNode.right);				// traverse to the right child of the node
    		else if(direction == -1)						// if 'data' is less than the data in the node
    			return contains(data, treeNode.left);				// traverse to the left child of the node
    		else											// otherwise the data values are equal
    			return true;								// so return true since the tree contains 'data'
    	}
    	
    	return false;									// if tree doesn't contain 'data' return false;
    }
    
    /**
     * Adds the specified data to the tree.  (If the data
     * is already in the tree, the data is ignored.)
     * 
     * @param data  the data value to be added to the tree
     */
    public void add (E data)
    {
    	add(data, root);								// adds specified 'data' to the tree via 'root'
    }
    
    /**
     * Adds the specified data to the tree.  (If the data
     * is already in the tree, the data is ignored.)
     * 
     * @param data
     * @param treeNode
     */
    private void add (E data, Node treeNode)
    {
    	//if(contains(data, treeNode))
    	//	return;
    	if(treeNode == null)
    	{
    		root = new Node(data);					// create a root node
    		size++;									// increment size
    		root.height++;							// add 1 to height
    	}
    	else
    	{
	    	Node previous = treeNode.parent;
	    	Node current = treeNode;
	    	int direction = 0; // = getDirection(data, treeNode);
	
	    	while(current != null)							// if the tree is empty
	    	{
	    		direction = getDirection(data, current);	// calculate direction to traverse
	    		
	    		if(direction == 1)							// if 'data' is greater than data in node
	    		{
	    			previous = current;
	    			current = current.right;
	    		}
	    		else if(direction == -1)					// if 'data' is less than data in node
	    		{
	    			previous = current;
	    			current = current.left;
	    		}
	    		else										// if 'data' is already in the node
	    			return;									// do nothing, just return
	    	}
	    		
	    	current = new Node(data);					// create a new node
	    	size++;										// increment size
	    	current.height++;							// add 1 to left node's height
	    	join(previous, direction, current);			// join nodes
	    	balance(current.parent);					// balance parents height and meet AVL reqs.
    	}						
    }
    
    /**
     * Guarantees that the specified data is not in the tree.
     * (The data is removed if needed.)
     * 
     * @param data  a data value to be removed from the tree
     */
    public void remove (E data)
    {
    	remove(data, root);
    }
    
    private void remove (E data, Node treeNode)
    {
    	if(treeNode == null)		// if empty
        	return;					// create a root node
    	else
    	{
    		// track current, previous nodes and direction relationship between them
	    	Node previous = treeNode.parent;	
	    	Node current = treeNode;
	    	int direction = 0; // = getDirection(data, treeNode);
	    	int prevDirection = 0;
	
	    	while(current != null)							// if the tree is empty
	    	{	
	    		prevDirection = direction;
	    		direction = getDirection(data, current);	// calculate direction to traverse
	    		
	    		if(direction == 1)							// if 'data' is greater than data in node
	    		{
	    			previous = current;
	    			current = current.right;
	    		}
	    		else if(direction == -1)					// if 'data' is less than data in node
	    		{
	    			previous = current;
	    			current = current.left;
	    		}
	    		else if(direction == 0) 					// if we find the 'data' we are looking for
	    			break;
	    		else
	    			return;									// does not contain the 'data' to be removed
	    	}
	    	
	    	if(current == null)								// if current is null, then the data isn't there
	    		return;
	    	else if(size == 1)								// if there is only one node it is the root
	    	{
	    		root = null;								// so delete the root
	    		size = 0;	  
	    		return;
	    	}
	    	else if((current.left == null) && (current.right == null)) 		// if current has no children
	    	{
	    		// remove it
	    		
	    		//direction = getDirection(current, previous);				// recalculate direction relationship
	    		join(null, prevDirection, current);
	    		join(previous, prevDirection, null);						// join nodes
	    		size--;														// reduce size
	    		balance(previous);										// balance parents height and meet AVL reqs.
	    		return;
	    	}
	    	else if((current.left != null) && (current.right != null))	// if current has two children 
	    	{
	    		// find largest value in the left subtree
	    		E temp = findMax(current.left).data; 
	    		
	    		// delete smallest value node
	    		remove(temp);
	    		
	    		// set the current data value to the largest value in the left subtree
	    		current.data = temp;
	    		return;
	    	}
	    	else if((current.right != null))							// if current has a right child
	    	{
	    		current = current.right;
	    		//direction = getDirection(current, previous);			// recalculate direction relationship
	    		//join(previous, direction, current);						// join nodes
	    		//size--;													// reduce size
	    		//balance(previous);										// balance parents height and meet AVL reqs.
	    	}
	    	else 														// if current has a left child
	    	{
	    		current = current.left;
	    		//direction = getDirection(current, previous);			// recalculate direction relationship
	    		//join(previous, direction, current);						// join nodes
	    		//size--;													// reduce size
	    		//balance(previous);										// balance parents height and meet AVL reqs.
	    	}	

	    	//direction = getDirection(current, previous);			// recalculate direction relationship
	    	join(previous, prevDirection, current);					// join nodes
    		size--;													// reduce size
    		balance(previous);										// balance parents height and meet AVL reqs.
    	}
    }
    
    /**
     * Links together parent & child nodes
     * 
     * Note: does not affect heights
     * 
     * @param parent	-a node(or null if child is the root)
     * @param direction	-will be -1 for left, 1 for right, 0 otherwise
     * @param child		-a node (or null if deleting a child)
     */
    private void join(Node parent, int direction, Node child)
    {
    	if(parent == null)				// if child is the root (special case)
    	{
    		child.parent = null;		// make the child's parent be null
    	}
    	else if(child == null)			// if we are at a leaf (special case)
    	{
    		if(direction == -1)			// if it is a left child
        		parent.left = null;		// disconnect parent from left child
    		if(direction == 1)			// if it is a right child
    			parent.right = null;	// disconnect parent from right child
    	}
    	else if(direction == 1)			// if child is to the right
    	{
    		child.parent = parent;		// create parent child relationship
    		parent.right = child;		// give parent a right child
    	}
    	else if(direction == -1)		// if child is to the left
    	{
    		child.parent = parent;		// create parent child relationship
    		parent.left = child;		// give parent a left child
    	}
    	else
    		return;
    }
    
    /**
     * Helper method to compare two data items. Assumes data1 comes from a child node and data2 from a 
     * parent node. Returns a direction value (as an int) indicating how the specified data is related to the node
     * returns -1 if the data is in the left subtree, 1 if the data is in the right subtree, 0 if the 
     * data is in n 
     * 
     * Note: this code does not traverse the tree it just check which direction to go
     * 
     * @param parent 	some node
     * @param child 	another node
     * @return an int representing direction to go
     */
    private int getDirection(Node child, Node parent)
    {
    	if((parent == null) || (child == null))
    		return 0;
    	if((child.data == null) || (parent.data == null))
    		return 0;
    	
    	int result = child.data.compareTo(parent.data);
    	
    	if(result > 0)						// if 'data1' is less than 'data2' 
			return 1;
		else if(result < 0)					// if 'data1' is greater than 'data2'
			return -1;
		else								// otherwise the data values are equal
			return 0;
    }
  
    /**
     * Helper method to compare two data items. Assumes data1 comes from a child node and data2 from a 
     * parent node. Returns a direction value (as an int) indicating how the specified data is related to the node
     * returns -1 if the data is in the left subtree, 1 if the data is in the right subtree, 0 if the 
     * data is in the node 'n'
     * 
     * Note: this code does not traverse the tree it just check which direction to go
     * 
     * @param n 		-some node
     * @param target	-some data to search for
     * @return 		an int representing direction to go
     * 
     */
    private int getDirection(E target, Node n)
    {
    	if(n == null)								// special case
    		return 0;
    	if((target == null) || (n.data == null))	// special case
    		return 0;
    	
    	int result = target.compareTo(n.data);
    		
    	if(result > 0)					// if 'data1' is less than 'data2' 
			return 1;
		else if(result < 0)				// if 'data1' is greater than 'data2'
			return -1;
		else							// otherwise the data values are equal
			return 0;
    }
    
    /**
     * Private helper method to find the node with the largest value
     * 
     * Citation: I referenced our textbook for this algorithm, Data Structures & Problem Solving Using 
     * Java, Weiss, 4th Edition, page 694
     * 
     * @param tree	the subtree you want to search for the smallest value
     * @return the node with the largest data value in the subtree
     */
    private Node findMax(Node tree)
    {
    	if(tree != null)				// if the tree isn't empty	
    		while(tree.right != null)	// as long as it isn't empty
    			tree = tree.right;		// traverse down the right node until reaching the bottom
    	
    	return tree;					// return that node
    }
    
    /**
     * This method balances the tree by rotating the nodes appropriately. It also check the heights
     * and makes sure all the balance conditions for AVL are met. It resets heights if necessary and
     * recursively re-balances parent nodes if necessary. 
     * 
     * @param n some node to be balanced
     * 
     */
    private void balance(Node n)
    {
    	if(n == null)	// base case if n (the parent) is null
    		return;
    	
    	// check balance conditions
    	if(Math.abs(getHeight(n.left) - getHeight(n.right)) >= 2)		// if the heights differ by more than 1
    	{
    		// rotate if necessary
        	if(getHeight(n.left) >= getHeight(n.right))					// if left is too deep
        	{
        		if(getHeight(n.left.right) > getHeight(n.left.left))	// double rotation if necessary
        		{	n.left = rotateRightChild(n.left);
        			balance(n);
        		}
        		else
        			n = rotateLeftChild(n);
        	}
        	else if(getHeight(n.left) < getHeight(n.right))				// if right is too deep
        	{
        		if(getHeight(n.right.left) > getHeight(n.right.right))	// double rotation if necessary
        		{	n.right = rotateLeftChild(n.right);
        			balance(n);
        		}
        		else
        			n = rotateRightChild(n);			// I think I need to reset my root here or something
        	}
 		}
    	
    	if(n.parent == null)	// if n is now the tree root, make sure to update 'root'
    		root = n;
    	int temp = n.height;    	
    	n.height = getHeight(n);	// reset height 
    	if(n.height != temp)		// if height of n changes from what it was, re-balance parent (recursively)
    		balance(n.parent);		
    }
    
    /**
     * This rotation is used to balance the tree when the outer left node causes an imbalance
     * 
     * Citation: I referenced our textbook for this algorithm, Data Structures & Problem Solving Using 
     * Java, Weiss, 4th Edition, page 710
     * 
     * @param oldRoot some node
     * @return the node rebalanced
     */
    private Node rotateLeftChild(Node oldRoot)
    {
    	// adjust nodes appropriately and redirect parents and children
    	Node newRoot = oldRoot.left;
    	Node ancestor = oldRoot.parent;

    	int direction = getDirection(newRoot, ancestor);
    	join(ancestor, direction, newRoot);
    	//ancestor.left = newRoot;
    	//newRoot.parent = ancestor;
    	
    	join(oldRoot, -1, newRoot.right);
    	//oldRoot.left = newRoot.right;
    	//newRoot.right = oldRoot;
    	
    	join(newRoot, 1, oldRoot);
    	
    	if(newRoot.left != null)
    		newRoot.left.height = getHeight(newRoot.left);
    	if(newRoot.right != null)
    		newRoot.right.height = getHeight(newRoot.right);
    	newRoot.height = getHeight(newRoot);
    	//newRoot.right.parent = newRoot;

    	return newRoot;
    }
    
    /**
     * This rotation is used to balance the tree when the outer right node causes an imbalance
     * 
     * Citation: I referenced our textbook for this algorithm, Data Structures & Problem Solving Using 
     * Java, Weiss, 4th Edition, page 711
     * 
     * @param oldRoot some node
     * @return the node rebalanced
     */
    private Node rotateRightChild(Node oldRoot)
    {
    	
    	// adjust nodes appropriately and redirect parents and children
    	Node newRoot = oldRoot.right;   
    	Node ancestor = oldRoot.parent;

    	int direction = getDirection(newRoot, ancestor);
    	join(ancestor, direction, newRoot);
    	
		//ancestor.right = newRoot;
		//newRoot.parent = ancestor;
    	
    	join(oldRoot, 1, newRoot.left);
    	//oldRoot.right = newRoot.left;
    	//newRoot.left.parent = oldRoot;
    	
     	join(newRoot, -1, oldRoot);
    	
    	//newRoot.left.parent = newRoot;
     	if(newRoot.left != null)
     		newRoot.left.height = getHeight(newRoot.left);
     	if(newRoot.right != null)
     		newRoot.right.height = getHeight(newRoot.right);
     	newRoot.height = getHeight(newRoot);
     	
    	return newRoot;
    }
    
    /**
     * Helper method to return the height in the tree of a given node
     * 
     * @param n some node
     * @return int the height of the node in the tree
     */
    private int getHeight(Node n)
    {
    	if(n == null)		// special case
    		return 0;
    	if((n.left == null) && (n.right == null))
    		n.height = 1;
    	else
    		n.height = Math.max(getHeight(n.left),getHeight(n.right)) + 1;
    	return n.height;
    }
    
    /** 
     * A debugging function (not required) that
     * verifies the element count and element sortedness.
     * My test also printed out the contents of the set.
     * 
     * Students may write debugging functions like this
     * one, but they may not write external tests or other
     * internal code that depends on the execution of any 
     * internal test function.
     * 
     * @return  true iff the set passes an internal test
     */
    boolean validate ()
    {
    	return false;	// Stub
    }

    
    // An example of an inner class (a class within another object).
    
    /**
     * A private helper class for the SpecialtySet class.
     * Node objects are used to construct a binary search tree
     * in a SpecialtySet.
     *
     * Students are not allowed to change this class.
     * 
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private class Node
    {
    	private E data;      // The data element - may be changed after it is assigned
    	private Node parent;       // Parent - initialized to null
    	private Node left, right;  // Children - initialized to null
    	private int height;        // Height of this subtree - initialized to 0
        
        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes (next is null), although it
         * is expected that 'next' may change.  
         * 
         * @param data   the data to store in the node
         */
        Node (E data)
        {
            this.data = data;
            this.parent = null;
            this.left = null; 
            this.right = null;
            this.height = 0;
        }
    }
}
