package homework10;

import java.io.*;
import java.util.*;

/**
 * A HuffmanCompressor object contains no data - it is just an
 * implementation of the Compressor interface.  It contains the compress
 * and decompress methods, along with a series of helper methods 
 * for counting tokens, building the Huffman tree, and storing data
 * in byte arrays.
 * 
 * The only methods that should be public are the constructor and the
 * Compressor interface methods, the rest should be private.  I have
 * left them public, though, for testing.
 * 
 * @author Peter Jensen / Basil Vetas - CS 2420
 * @version Spring 2014
 */
public class HuffmanCompressor implements Compressor
{
    // There are NO fields in the compressor class.  If you need
    //   to get data to or from the methods, use a parameter!  (Of course,
    //   you shouldn't need to add any, the definitions below are complete.)

    /**
     * This constructor does nothing.  There are no fields to initialize.
     * It is provided simply for testing.  (You must make a HuffmanCompressor
     * object in order to test the compress and decompress methods.)
     */   
	public HuffmanCompressor(){}
    
	/*
	 * Before attempting to complete the main portion of the assignment, finish writing the HuffmanNode and HuffmanToken classes. 
	 * Your HuffmanCompressor must adhere to the method contracts specified in Compressor.java.
	 * You may create as many helper classes as you need.  This class is the core of the assignment
	 */

	/*
	 * You must use the Huffman compression and decompression algorithm in your solution.
	 * Your HuffmanCompressor class must not store any state.  
	 * This means that you should not have any fields (also called instance variables) or 
	 * static variables in HuffmanCompressor.  (Your helper classes may store state; they may have fields).
	 * Any state that you might want to store should be part of the compressed data.  (This includes sizes, code tables, etc.)
	 * You may not copy any portion of your solution from any source.
	 */
    
    /**
     * This helper method counts the number of times each data value occurs in
     * the given byte array.  For each different value, a HuffmanToken is
     * created and stored.  When the same value is seen again, its token's frequency
     * is increased.  After all the different data values have been counted
     * this method will return an ArrayList of HuffmanToken objects.
     * Each token will contain a count of how many times that token occurred
     * in the byte array.  (If you summed up the counts, the total would be
     * same as the length of the data array.)
     * 
     * Note that the returned tokens in the ArrayList may be in any order.
     * 
     * @param  data  A list of data bytes
     * @return       A list of HuffmanTokens that contain token counts
     */       
    private ArrayList<HuffmanToken> countTokens (byte[] data)
    {
    	ArrayList<HuffmanToken> tokensList = new ArrayList<HuffmanToken>();
    	
    	for(byte b: data)	// for each byte value in 'data'
    	{
    		HuffmanToken currentToken = new HuffmanToken(b);
    		
    		if(tokensList.contains(currentToken))		// if the current token is in the list 
    			tokensList.get(tokensList.indexOf(currentToken)).incrementFrequency();	// increment its frequency
    		else if(!(tokensList.contains(currentToken)))			// if it is not in the list
    		{
    			tokensList.add(currentToken);			// add it to the list
    			tokensList.get(tokensList.indexOf(currentToken)).setFrequency(1);	// set frequency to equal 1
    		}
    	}
    	
    	//check that the counts in tokensList are same as length of the data array 
    	int totalFreqs = 0;
    	for(int i = 0; i < tokensList.size(); i++)    		
    		totalFreqs += tokensList.get(i).getFrequency();		// add up the frequencies
    	
    	if(!(totalFreqs == data.length))						// check if they are equal
    		System.out.println("Error: Frequency count in tokensList does not match length of the byte array");
    		
        return tokensList;	// return the list of tokens and their frequencies
    }
    
    /**
     * This helper method builds and returns a Huffman tree that contains the
     * given tokens in its leaf nodes.
     * 
     * The Huffman tree-building algorithm is used here.  You may find it
     * in the book or in your notes from lecture.  Remember to first
     * create leaf nodes for all the tokens, and add these leaf nodes to a
     * priority queue.  You may then build and return the tree.
     * 
     * It is assumed that the tokens do not have Huffman codes when this method is
     * called.  Due to the side-effect of one of the HuffmanToken constructors,
     * the HuffmanToken objects will have correct Huffman codes when this method
     * finishes building the tree.  (They are built as the tree is built.)
     * 
     * @param  tokens  A list of Tokens, each one with a frequency count
     * @return         The root node of a Huffman tree
     */       
    private HuffmanNode buildHuffmanCodeTree (ArrayList<HuffmanToken> tokens)
    {
    	// create an empty priority queue to hold each HuffmanToken leaf node
    	PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
    	
    	// for each HuffmanToken in 'tokens'
    	for(int i = 0; i < tokens.size(); i++)
    	{   
    		HuffmanNode currentNode = new HuffmanNode(tokens.get(i));	// create a new leaf node for the current token
    		pq.add(currentNode);										// add the leaf node to the priority queue
    		
    		// Note: the nodes should be in order with the lowest frequency node at the top of the priority queue
    		// which is the case because we overwrote the compareTo method in the node Class for HuffmanNodes
    	}
    	
    	if(pq.size() == 1)		// if there is only one token in the pq, make a dummy root for it 
    	{
    		HuffmanNode leftChild = pq.poll();
    		byte b = 0;	// dummy right byte 
    		HuffmanToken emptyToken = new HuffmanToken(b);	//dummy right token
    		HuffmanNode rightChild = new HuffmanNode(emptyToken); //dummy right node
    		HuffmanNode dummyRoot = new HuffmanNode(leftChild, rightChild);
    		pq.add(dummyRoot);	// re-add it to the priority queue
    	}
    	else	// otherwise build the tree like normal
    	{
	    	// build the tree
	    	while(pq.size() > 1) 	// until only one node remains in the priority queue
	    	{	
	    		HuffmanNode leftChild = pq.poll();								// remove two nodes from the queue
	    		HuffmanNode rightChild = pq.poll();
	    		HuffmanNode parent = new HuffmanNode(leftChild, rightChild);	// create a parent for them
	    		pq.add(parent);													// add the parent to the priority queue
	    	}
    	}
    	HuffmanNode root = pq.poll();	// get the last HuffmanNode out of the priority queue and set it as root
    	
    	return root;	    			// return the root node of the tree
    }
    
    /**
     * This helper method creates a dictionary of Huffman codes from a list
     * of Huffman tokens.  It is assumed that the Huffman tokens have
     * correct Huffman codes stored in them.
     * 
     * This method is for convenience only.  Values and Huffman codes are extracted
     * from the tokens and added to a Map object so that they can be quickly
     * retrieved when needed.
     * 
     * @param  tokens  A list of Tokens, each one with a Huffman code
     * @return         A map that maps byte values to their Huffman codes
     */       
    private Map <Byte, ArrayList<Boolean>> createEncodingMap (ArrayList<HuffmanToken> tokens)
    {
    	// a map to store the code dictionary
    	Map<Byte, ArrayList<Boolean>> codeMap = new HashMap<Byte, ArrayList<Boolean>>();
    		
    	// for each HuffmanToken in 'tokens'
    	for(int i = 0; i < tokens.size(); i++)
    	{
    		// create a map from the token value to the token code
    		codeMap.put(tokens.get(i).getValue(), tokens.get(i).getCode());
    	}

        return codeMap;		// return the completed code dictionary
    }
    
    /**
     * This helper method encodes an array of data bytes as an ArrayList of
     * bits (Boolean values).  Huffman codes are used to translate the bytes
     * into bits.<p>&nbsp;<p>
     * 
     * For every value in the data array, the corresponding Huffman code is
     * retrieved from the map and added to a new ArrayList that will be returned.
     * 
     * @param  data    An array of data bytes that will be encoded (compressed)
     * @param  encodingMap  A map that maps byte values to Huffman codes (bits)
     * @return         An ArrayList of bits (Booleans) that represent the compressed (Huffman encoded) data
     */       
    private ArrayList<Boolean> encodeBytes (byte[] data, Map <Byte, ArrayList<Boolean>> encodingMap)
    {
    	// arraylist to hold the encoded bits
    	ArrayList<Boolean> encodedList = new ArrayList<Boolean>();
    	
    	for(byte b: data)	// for each value in the data array
    		encodedList.addAll(encodingMap.get(b));	// add the corresponding Huffman code from the map to encodedList     	

    	//return the compressed list of bit (as Boolean values)
        return encodedList;
    }
    
    /**
     * This helper method decodes a list of bits (which are Huffman codes) into
     * an array of bytes.  In order to do the decoding, a Huffman tree
     * containing the tokens is required.<p>&nbsp;<p>
     * 
     * To do the decoding, follow the decoding algorithm given in the book
     * or review your notes from lecture.<p>&nbsp;<p>
     * 
     * (You will need
     * to build a Huffman tree prior to calling this method, and the Huffman
     * tree you build should be exactly the same as the one that was used to
     * encode the data.)
     * 
     * @param  bitCodes  An ArrayList of bits (Booleans) that represent the compressed (Huffman encoded) data
     * @param  codeTree  A Huffman tree that can be used to decode the bits
     * @param  dataLength  The number of bytes that will be in the decoded byte array
     * @return           An array of bytes that represent the uncompressed data
     */       
    private byte[] decodeBits (ArrayList<Boolean> bitCodes, HuffmanNode codeTree, int dataLength)
    {
    	byte [] decodedList = new byte[dataLength];	    			// byte array to hold the decoded list
    	HuffmanNode currentNode = codeTree;							// initialize current node to codeTree (root)
    	int counter = 0;											// counter for next empty position in byte array
    	
      	if(dataLength == 0)			// if there is not data
    		return decodedList;		// return
    	
    	for(int i = 0; i <= bitCodes.size(); i++)// for each element in the bitCodes array list
    	{
    		if(decodedList[dataLength-1] != 0)						// if the codeList is full, break
    			break;
    		if(currentNode.isLeafNode())							// if currentNode is a leaf node
    		{
    			decodedList[counter] = currentNode.getToken().getValue();	// add it to the byte array
    			currentNode = codeTree;										// reset currentNode to root
    			counter++;													// increment counter
    		}
    		
    		if(i >= bitCodes.size())								// if there are no more element, break 
    			break;
    		else if(bitCodes.get(i) == false)						// if the code at 'i' is 0 (false)
    			currentNode = currentNode.getLeftSubtree();			// traverse the left subtree
    		else if(bitCodes.get(i) == true) 						// if the code at 'i' is 1 (true)
    			currentNode = currentNode.getRightSubtree();		// traverse the right subtree
    	}

    	// return the uncompressed data 
        return decodedList;
    }
    
    /**
     * Given any array of bytes that contain some data, this method returns a 
     * compressed form of the original data.  The returned, compressed bytes must
     * contain sufficient information so that the decompress method below can
     * reconstruct the original data.
     * 
     * Huffman coding is used to compress the data.<p>&nbsp;<p>
     * 
     * Some of the code for this method has been provided for you.  You should figure out
     * what it does, it will significantly help you.
     *
     * @param  compressedData  An array of bytes that contains some data that should be compressed
     * @return                 An array of bytes that contains the compressed form of the original data
     */
    public byte[] compress (byte[] data)
    {
    	if(data == null)	// check for a null byte array
    		return null;
    	
        // Variable initialization and compression steps
    	//System.out.println("\nCompress: ");
    	
    	ArrayList<HuffmanToken> tokens = countTokens(data);		// create tokens list
    	//HuffmanTools.dumpHuffmanCodes(tokens);  				// Useful for debugging
    	buildHuffmanCodeTree(tokens);
    	
    	//HuffmanTools.dumpHuffmanCodes(tokens);  				// Useful for debugging
    	
    	Map <Byte, ArrayList<Boolean>> codeMap = createEncodingMap(tokens);	// create a map from bytes to codes
    	ArrayList<Boolean> encodedBits = encodeBytes(data, codeMap); // use map to encode/compress data
    	byte[] compressedBytes = null;	// will hold new compressed data as bytes array
        
        // You need to set up the appropriate variables before this code begins.  This
        //   code will place various data elements of the compressed data into
        //   a byte array for you.
  
        try
        {
        	// Create a new byteOutput object to hold the bytes from the file
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();	
            // Create a new output object to hold the data from the file
            DataOutputStream output = new DataOutputStream (byteOutput);                    
            
            // write an int to the output object to indicate length of the original data file
            output.writeInt (data.length);		
            writeTokenList (output, tokens);		// writes the data values to the output stream
            writeBitCodes (output, encodedBits);	// write the byte "decode" codes to the output stream
            
            compressedBytes = byteOutput.toByteArray();
        }
        catch (IOException e)
        {
            System.out.println ("Fatal compression error: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Return the compressed data
        return compressedBytes;
    }
    
   /**
     * Given an array of bytes that contain compressed data that 
     * was compressed using this compressor, this method will reconstruct and return
     * the original, uncompressed data.  The compressed bytes must contain sufficient
     * information so that this method can reconstruct the original data bytes.
     *
     * Huffman coding is used to decompress the data.<p>&nbsp;<p>
     * 
     * Some of the code for this method has been provided for you.  You should figure out
     * what it does, it will significantly help you.
     *
     * @param  compressedData  An array of bytes that contains some data in compressed form
     * @return                 An array of bytes that contains the original, uncompressed data
     */
    public byte[] decompress (byte[] compressedData)
    {
    	if(compressedData == null)	// check for a null byte array
    		return null;
    	
    	// Variable initialization     	
    	int dataLength = 0;
    	ArrayList<HuffmanToken> tokens;
    	ArrayList<Boolean> encodedBits;
    	byte[] decompressedBytes = null;
    	
        // You need to set up the appropriate variables before this code begins.  This
        //   code will extract various data elements from the compressedData bytes for you.
        
        try
        {
        	// variable to hold input stream of compressed data bytes
            ByteArrayInputStream byteInput = new ByteArrayInputStream (compressedData);
            
            // variable for reading in the data from the input stream
            DataInputStream input = new DataInputStream (byteInput);                    
            
            dataLength = input.readInt ();		// reads in the next (first) four bytes of the input stream
            tokens = readTokenList (input);		// reads in the tokens from the input stream & stores in list
            encodedBits = readBitCodes (input); // reads in data
        }
        catch (IOException e)
        {
            System.out.println ("Fatal compression error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        //System.out.println("\nDecompress: ");
        
        //HuffmanTools.dumpHuffmanCodes(tokens);  				// Useful for debugging
        HuffmanNode root = buildHuffmanCodeTree(tokens);		// create Huffman tree
        //HuffmanTools.dumpHuffmanCodes(tokens);  				// Useful for debugging
        
        // decompress the data using the boolean list of codes, the code tree, and knowing the data length
        decompressedBytes = decodeBits (encodedBits, root, dataLength);	

        // Return the decompressed data
        return decompressedBytes;        
    }

    // The following methods read and write data values from a ByteArray Streams.  Because I'm giving you
    //   this code, you should try to comment these methods yourself.
    
    /** 
     * This method first writes an int to the output object to indicate the number of 
     * tokens in the ArrayList. Next, for each token in the ArrayList, it writes the 
     * byte value and corresponding frequency to the output object. 
     * 
     * Note: This is the actual "data" portion of the new compressed file 
     * 
     * @param output	The output stream we are writing the data to
     * @param tokens 	An ArrayList of HuffmanTokens
     */
    private void writeTokenList (DataOutputStream output, ArrayList<HuffmanToken> tokens) throws IOException
    {
        output.writeInt (tokens.size());	// write int to indicate number of tokens
        
        for (HuffmanToken token : tokens)	// for each token
        {
            output.writeByte (token.getValue());		// write token value
            output.writeInt (token.getFrequency());		// write corresponding frequency 
        }
    }
    
    /** 
     * This method reconstructs from the input data an array list of HuffmanTokens that contains one token
     * for each byte value in the original data file. These tokens contain both the value of the byte as well
     * as the frequency of that byte in the original data file. 
     * 
     * It is assumed that the input data stream contains an int indicating the number of tokens in the 
     * list as its first 4 bytes. It is also assumed that the remaining bytes in the list consist of a byte 
     * followed immediately by 4 bytes representing the frequency of that byte in the original data file 
     * 
     * @param input	The DataInputStream to be read
     * @return ArrayList<HuffmanToken>	the array list of uncompressed tokens
     */
    private ArrayList<HuffmanToken> readTokenList (DataInputStream input) throws IOException
    {
        ArrayList<HuffmanToken> tokens = new ArrayList<HuffmanToken> ();
        
        int count = input.readInt ();	// get int (four bytes) indicating the number of tokens in the list
        
        for (int i = 0; i < count; i++)	// for each token in the array list
        {
        	// create a new HuffmanToken for the next byte in the list
            HuffmanToken token = new HuffmanToken (input.readByte()); 	  
          
            // get the frequency of that byte value and set it in the token 
            token.setFrequency (input.readInt());						 
            
            tokens.add (token); 	// add the token to the array list of tokens
        }
        
        return tokens;		// return the array list of HuffmanTokens
    }
    
    /** 
     * This method gets 8 bits from the boolean ArrayList, converts them from T or F to 0 or 1, respectively,
     * and stores them in a new byte value 'b' which is then written to the output object. This process is 
     * repeated for the entire ArrayList of boolean bits.
     * 
     * Note: These bytes are the "decode keys" of the compressed file
     * 
     * @param outout	The output stream we are writing the data to
     * @param bits		An ArrayList of encoded bits representing the compressed codes
     * @return int		The number of bytes written to the output object
     */
    private int writeBitCodes (DataOutputStream output, ArrayList<Boolean> bits) throws IOException
    {        
        int bytesWritten = 0;							// counter keeps track of number of bytes written to output object
        
        for (int pos = 0; pos < bits.size(); pos += 8)	// iterate through bits ArrayList by 8 bits (1 byte)
        {
            int b = 0;									// variable for the new byte value after extracting from 'bits'
            for (int i = 0; i < 8; i++)					// iterate through a byte (8 bits)
            {
                b = b * 2;										// shift the rightmost bit one position left
                if (pos + i < bits.size() && bits.get(pos+i))	// if the current bit is a 1 (a 'true' boolean)
                    b = b + 1;									// add 1 to the byte variable
            }
            output.writeByte ((byte) b);						// once all 8 bits are correctly placed, write b to output 
            bytesWritten++;										// increment the number of bytes we have written to output
        }
        
        return bytesWritten;									// return the number of bytes written to output
    }
    
    /** 
     * This method reads in the remaining bytes from an input steam of data and stores them correctly as 
     * bit segments (True or False values) in an array list of booleans.  They bit segments are used as the
     * codes for decoding a file compressed using the HuffmanCompressor. This method assumes that the data 
     * length and the list of tokens/frequencies have already been read and removed from the input stream. 
     * 
     * @param input					The input data stream to be read 
     * @return ArrayList<Boolean>	The list of codes used for decoding
     */
    private ArrayList<Boolean> readBitCodes (DataInputStream input) throws IOException
    {
        ArrayList<Boolean> bits = new ArrayList<Boolean> (); // new ArrayList to hold the codes
        
        while (input.available() > 0)			// while input has remaining bytes that can be read
        {
            int b = input.readByte ();			// read in the next byte and store it in 'b' 
            
            for (int i = 7; i >= 0; i--)		// for each bit in byte 'b'
                bits.add (((b >> i) & 1) == 1);	// add either a true (for 1) or false (for 0) value to the list 
        }
        
        return bits;	// return the array list of booleans with the codes for decoding the file
    }
}
