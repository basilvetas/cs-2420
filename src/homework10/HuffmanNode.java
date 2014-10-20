package homework10;

import java.util.*;

/**
 * Objects of this class represent one node in a
 * Huffman tree.  A Huffman tree is a binary two-tree, so
 * each node has a left and right child (or no children).  In addition,
 * a HuffmanNode object contains a totalFrequency count
 * that is the sum of the frequencies of the left and right
 * sub-node (if any).  Lastly, a HuffmanNode object keeps a list
 * of all the tokens stored in its subtree.
 * 
 * @author Peter Jensen / Basil Vetas - CS 2420
 * @version Spring 2014
 */
public class HuffmanNode implements Comparable<HuffmanNode>
{
    // The sum of all the frequencies in all the leaf nodes at or below
    //   this node.
    private int totalFrequency;
    
    // A list of all the tokens contained at or below this node.  This list is kept in sorted order.
    private ArrayList<HuffmanToken> tokens;
    
    // The left and right children of this node.
    private HuffmanNode left, right;
  
    /**
     * This constructor creates a leaf node in the Huffman tree.
     * A leaf node in a Huffman tree is mapped to a single token.
     * Use this token to initialize the frequency in this node.
     * 
     * @param  token  The token to store in this leaf node
     */   
    public HuffmanNode (HuffmanToken token)
    {
    	this.tokens = new ArrayList<HuffmanToken>();
    	this.tokens.add(token);		// adds the token to the array list
    	this.totalFrequency = token.getFrequency();	// initialize frequency in this node
    	this.left = null;
    	this.right = null;
    }
    
    /**
     * This constructor creates an interior node in the Huffman tree.
     * It takes two non-null nodes as parameters, and it initializes
     * its fields by combining the frequency and tokens of the child nodes.
     * Finally, the internal list of tokens is sorted.
     * 
     * This constructor has a major side effect.  To simplify building
     * of Huffman codes, this constructor will prepend Huffman code
     * bits to all of the tokens' codes stored below this point in the
     * Huffman tree.  All of the tokens in this node's left child subtree should
     * have a zero (false) bit prepended to their code, and all of the
     * tokens in this node's right child subtree should have a one (true) bit
     * prepended to their code.  Fortunately, each node contains a list
     * of the tokens in its subtree, so this step can be done without a
     * traversal.
     * 
     * The advantage of this side effect is that when a Huffman tree has
     * been completely built, the Huffman codes for each token will also have
     * been built.
     * 
     * @param  left  The subtree to become the left child of this node
     * @param  right  The subtree to become the right child of this node
     */   
    public HuffmanNode (HuffmanNode left, HuffmanNode right)
    {
    	this.tokens = new ArrayList<HuffmanToken>();
    	this.left = left;
    	this.right = right;
    	this.tokens.addAll(left.tokens);										// add the entire left subtree
    	this.tokens.addAll(right.tokens);									 	// add the entire right subtree
    	this.totalFrequency = (left.totalFrequency) + (right.totalFrequency);	// this frequency becomes sum of both children
   
    	//sort this.tokens (using compareTo)
    	
    	// prepend a 0 to all the nodes in the left subtree
    	for(int i = 0; i < this.left.tokens.size(); i++)
    	{
    		this.left.tokens.get(i).prependBitToCode(false);
    	}
    	
    	// prepend a 1 to all the nodes in the right subtree
    	for(int j = 0; j < this.right.tokens.size(); j++)
    	{
    		this.right.tokens.get(j).prependBitToCode(true);
    	} 	
    }
    
    /**
     * Returns the left subtree of this node, or null if this
     * is a leaf node.
     * 
     * @return  The left subtree of this node
     */   
    public HuffmanNode getLeftSubtree ()
    {
    	if(!(this.isLeafNode()))	// if left is not a leaf node
    		return left;			// return the left subtree of the 
    								// else if it is a leaf node
    	return null;				// return null
    }
    
    /**
     * Returns the right subtree of this node, or null if this
     * is a leaf node.
     * 
     * @return  The right subtree of this node
     */   
    public HuffmanNode getRightSubtree ()
    {
    	if(!(this.isLeafNode()))	// if right is not a leaf node
    		return right;			// return the right subtree of the 
    								// else if it is a leaf node
    	return null;				// return null
    }
    
    /**
     * Returns true if this node is a leaf node, false otherwise.
     * 
     * @return  True if this node is a leaf node
     */   
    public boolean isLeafNode ()
    {	
    	if((this.left == null) && (this.right == null)) // if both children are null then 'this' is a leaf node
    		return true;								// so return true
    	
        return false;
    }
    
    /**
     * If this is a leaf node, this method returns the token
     * that was used to create this leaf node.  Null is returned
     * if this is not a leaf node.
     * 
     * @return  The token stored in this leaf node
     */   
    public HuffmanToken getToken ()
    {
    	if(isLeafNode())			// if 'this' is a leaf node, there should only be one element in 'tokens' list 
    		return tokens.get(0); 	// so return the token at index 0 used to create this lead node
    	
    	return null; 				// if 'this' is not a leaf node, return null
    }
    
    /**
     * This method assumes that a HuffmanNode is stored in Object o.
     * (Note that a typecast is required in the method below.)
     * True is returned if this node is identical to the node in o,
     * and false otherwise.  (Hint -- just use your compareTo function
     * below.)
     * 
     * @param  o  An object that should be a HuffmanNode
     * @return  True if the this node equals the given node
     */   
    public boolean equals (Object o)
    {
    	HuffmanNode node = (HuffmanNode) o;	// typecast into a HuffmanNode
    	if(this.compareTo(node) == 0)		// if the node is equal to 'this' node
    		return true;					// return true
    	
        return false;
    }
    
    /**
     * This method determines which of two nodes is less than the other.
     * This is used to determine priority of nodes when they are
     * inserted into a priority queue.
     * 
     * Huffman nodes are compared by their frequency first, and in the
     * case of equal frequencies, by the byte value of the first token in 
     * each node's list second.  If their frequencies and tokens are equal, 
     * the HuffmanNode objects are assumed equal.  (An 'equal' result 
     * should NEVER happen unless a node is compared to itself.)
     * 
     * This method returns:
     * -1 if 'this' HuffmanNode object is less than 'node'
     * 0 if 'this' HuffmanNode object is equal to 'node'
     * 1 if 'this' HuffmanNode object is greater than to 'node'
     * 
     * @param  node  An object that should be a HuffmanNode
     * @return  -1, 0, or 1, depending on the relative order of the nodes
     */   
    public int compareTo (HuffmanNode node)
    {
    	if(this.totalFrequency < node.totalFrequency)	// if 'this' frequency count is less than node's freq count
    		return -1;	// 'this' is less than node
    	else if(this.totalFrequency > node.totalFrequency)	// if 'this' freq count is greater than node's freq count
    		return 1;	// 'this' is greater than node
    	else if(this.tokens.get(0).getValue() < node.tokens.get(0).getValue())	// if 'this' value is less than node's value
    		return -1; 	// 'this' is less than node
    	else if(this.tokens.get(0).getValue() > node.tokens.get(0).getValue()) // if 'this' value is greater than node's value
    		return 1;	// 'this' is greater than node
    	
    	return 0;		// else they two nodes are equal, return 0
    }
}
