package homework10;

import java.util.*;

/**
 * Objects of this class represent a single Huffman token.  A
 * Huffman token is just a data value (a byte), a frequency, and
 * the Huffman code for this token.
 * 
 * @author Peter Jensen / Basil Vetas - CS 2420
 * @version Spring 2014
 */
public class HuffmanToken
{
    // The byte value of this token.
    private byte value;
    
    // The number of times this token occurs in some data.
    private int frequency;
    
    // The Huffman code that corresponds to this token.
    //   The first bit in the code is the first element in the array.
    private ArrayList<Boolean> code;
    
    /**
     * This constructor initializes the token to its default
     * values.  A byte value is stored in the token, the frequency
     * is set to zero, and an empty code is created.<p>&nbsp;<p>
     * 
     * The frequency and code will be set up later as the 
     * Huffman compression (or decompression) proceeds.
     * 
     * @param  value  The byte value to store in this token
     */   
    public HuffmanToken (byte value)
    {
    	this.value = value;
    	this.frequency = 0;
		code = new ArrayList<Boolean>();
    }
    
    /**
     * Increases the frequency count by one.
     */   
    public void incrementFrequency ()
    {
    	this.frequency++;	// adds 1 to the frequency count 
    }
    
    /**
     * Returns the frequency count.
     * 
     * @return  The number of times this token exists in some data
     */   
    public int getFrequency ()
    {
        return this.frequency;	// returns the frequency count for the byte value
    }
    
    /**
     * Changes the frequency count to some particular value.
     * 
     * @param  frequency  Any valid frequency count
     */   
    public void setFrequency (int frequency)
    {
    	this.frequency = frequency;	// sets the frequency count for the byte value
    }
    
    /**
     * Returns the byte value stored in this token.
     * 
     * @return  The byte value stored in this token
     */   
    public byte getValue ()
    {
        return this.value;	// returns the byte value of this token
    }
    
    /**
     * Adds the specified bit to the beginning of the
     * Huffman code for this token.  
     * 
     * @param  bit  A bit to prepend to the Huffman code for this token
     */   
    public void prependBitToCode (Boolean bit)
    {
    	this.code.add(0, bit);	// adds the bit to the beginning of the Huffman code (at index '0')
    }
    
    /**
     * Returns a copy of the Huffman code that represents this token.
     * The first bit of the Huffman code is in the 0th position in the
     * ArrayList.
     * 
     * @return  The Huffman code for this token
     */   
    public ArrayList<Boolean> getCode ()
    {
        return this.code;	// returns a copy of the Huffman code represented as boolean values (true or false)
    }
    
    /**
     * Overrides the equals method in the Object class. Checks if the 'this' token is 
     * equal to another token based on their byte values. 
     * 
     * @param o - the reference object with which to compare.
     * @return true if this object is the same as the object argument; false otherwise.
     */
    public boolean equals(Object o)
    {
    	if (!(o instanceof HuffmanToken))		// check if the object is a HuffmanToken
    			return false;					// if not, return false
    	
    	HuffmanToken token = (HuffmanToken) o;	// If so, typecast it to a HuffmanToken
    	
    	if(this.value == token.value)			// if the values are equal
    		return true;						// return true
    	
    	return false;							// otherwise return false
    }
}
