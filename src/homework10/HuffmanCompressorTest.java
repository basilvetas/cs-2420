package homework10;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HuffmanCompressorTest 
{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*Create tests for testing your compression and decompression methods.  
	This part is up to you, but I recommend that your tests compress and decompress some data, 
	and then your test can check to make sure the decompressed data is byte-for-byte the same as the original.*/
	
	/*We will test your Compressor class with our own test harness with data files of up to 1 megabyte on 
	a Java virtual machine of at least 256 megabytes.  A large number of points will be reserved for correctness, 
	so make sure you test your code.
	
	The most common mistake students make is storing state in the Compressor class.  
	You must assume that the test machine will be powered down prior to decompressing a compressed file.  
	(All stored variable values are lost.)
	*/
	
	/**
	 * This test evaluates compression/decompression for a simple String object by comparing bytes. To pass
	 * the test, the byte values before and after compression/decompression must be equal.
	 */
	@Test
	public void test01()
	{
		System.out.println("Test 1:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "Hello Good Bye So Long FareWell I I I I";
		byte[] b = s.getBytes(); 
		//System.out.println("before: ");
		String before = HuffmanTools.dumpBytesAsBits(b, 0, b.length);
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		//System.out.println("after: ");
		String after = HuffmanTools.dumpBytesAsBits(d, 0, d.length);
		
		assertEquals("Bytes are equal:", true, before.equals(after));
	}
	
	/**
	 * This test evaluates compression/ decompression for a simple String object.
	 * 
	 */
	@Test
	public void test02()
	{
		System.out.println("Test 2:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "Helllo";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
	}

	/**
	 * This test evaluates compression/decompression for a simple String object by comparing bytes. To pass
	 * the test, the byte values before and after compression/decompression must be equal.
	 */
	@Test
	public void test03()
	{
		System.out.println("Test 3:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "";
		for(int i = 0; i < 100; i++)
			s += i;
		
		byte[] b = s.getBytes(); 
		String before = HuffmanTools.dumpBytesAsBits(b, 0, b.length);
		System.out.println("");
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String after = HuffmanTools.dumpBytesAsBits(d, 0, d.length);
		System.out.println("");
		
		assertEquals("Bytes are equal:", true, before.equals(after));
	}
	
	/**
	 * This test evaluates compression/decompression for a simple String object by comparing bytes. To pass
	 * the test, the byte values before and after compression/decompression must be equal.
	 */
	@Test
	public void test04()
	{
		System.out.println("Test 4:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "AAAAAAAAAAA";
		byte[] b = s.getBytes(); 
		String before = HuffmanTools.dumpBytesAsBits(b, 0, b.length);
		System.out.println("");
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String after = HuffmanTools.dumpBytesAsBits(d, 0, d.length);
		System.out.println("");
		
		assertEquals("Bytes are equal:", true, before.equals(after));
		
	}
	
	/**
	 * This test evaluates compression/ decompression for a String object of only 1 repeated character.
	 * 
	 */
	@Test
	public void test05()
	{
		System.out.println("Test 5:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "AAAAAAAAAAA";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
		
	}
	
	/**
	 * This test evaluates compression/ decompression for an empty String object.
	 * 
	 */
	@Test
	public void test06()
	{
		System.out.println("Test 6:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
		
	}

	/**
	 * This test evaluates compression/ decompression for a String object with one character.
	 * 
	 */
	@Test
	public void test07()
	{
		System.out.println("Test 7:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "A";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
	}
	
	/**
	 * This test evaluates compression/ decompression for a String object with two characters.
	 * 
	 */
	@Test
	public void test08()
	{
		System.out.println("Test 8:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "AB";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
	}
	
	/**
	 * This test evaluates compression/ decompression for a String object with spaces as characters.
	 * 
	 */
	@Test
	public void test09()
	{
		System.out.println("Test 9:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = "          ";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
	}
	
	/**
	 * This test evaluates compression/ decompression for a String object with one character and
	 * spaces only. 
	 * 
	 */
	@Test
	public void test10()
	{
		System.out.println("Test 10:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		String s = " A ";
		byte[] b = s.getBytes(); 
		byte[] c = a.compress(b);
		byte[] d = a.decompress(c);
		String t = new String(d);
		
		assertEquals("Strings are equal:", true, s.equals(t));
	}

	/**
	 * This test evaluates compression/ decompression for a null file.
	 * 
	 */
	@Test
	public void test11()
	{
		System.out.println("Test 11:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		byte[] c = a.compress(null);	// compress the null file

		assertEquals("Strings are equal:", true, c == null);
	}
	

	/**
	 * This test evaluates compression/ decompression for a null file.
	 * 
	 */
	@Test
	public void test12()
	{
		System.out.println("Test 12:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		byte[] c = a.decompress(null);	// decompress the null file

		assertEquals("Strings are equal:", true, c == null);
	}
	
	
	
	/**
	 * This test evaluates compression/ decompression for the "80Days.txt" test file. This test compares
	 * the bytes in the original file to the bytes in the decompressed file and evaluates whether the
	 * corresponding bytes are equal. 
	 * 
	 */
	@Test
	public void test13()
	{
		System.out.println("Test 13:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		try
		{
		    File inputFile  = new File("80Days.txt");	// create a new file object to hold the text file

		    int fileLength = (int) inputFile.length();	// get the length of the file
		    byte[] b = new byte[fileLength];			// create an array of bytes with length of the file
		    
		    FileInputStream in = new FileInputStream(inputFile);	// new input stream of the file
		    in.read(b);												// read the input stream in the byte array
		    in.close();												// close the input stream
		    
			byte[] c = a.compress(b);					// compress the file
			
			//String t = new String(c);					// put compressed file into a string
			
			File outputFile = new File("Compressed80Days.txt"); 	// create a new file for the compressed data
			
			FileOutputStream out = new FileOutputStream(outputFile);	// output stream from file

		    out.write(c);		// write the compressed data to the file
		    out.close();		// close the file
		    
		    File compressedFile  = new File("Compressed80Days.txt");	// create a new file object to hold the text file

		    int compressedLength = (int) compressedFile.length();		// get the length of the file
		    byte[] compressedB = new byte[compressedLength];			// create an array of bytes with length of the file
		    
		    FileInputStream compressedIn = new FileInputStream(compressedFile);	// new input stream of the file
		    compressedIn.read(compressedB);										// read the input stream in the byte array
		    compressedIn.close();												// close the input stream
		    
			byte[] decompressed = a.decompress(compressedB);		
		    
			File decompressedFile = new File("Decompressed80Days.txt"); 	// create a new file for the decompressed data
			
			FileOutputStream decompressedOut = new FileOutputStream(decompressedFile);	// output stream from file

			decompressedOut.write(decompressed);	// write the decompressed data to the file
			decompressedOut.close();				// close the file
			
			int decompressedLength = (int) decompressedFile.length();	// get the length of the file
		    byte[] d = new byte[decompressedLength];					// create an array of bytes with length of the file
		    
		    FileInputStream input = new FileInputStream(decompressedFile);	// new input stream of the file
		    
		    File original  = new File("80Days.txt");			// create a new file object to hold the original text file

		    int originalLength = (int) original.length();		// get the length of the original file
		    byte[] o = new byte[originalLength];				// create an array of bytes with length of the original file
		    
		    FileInputStream oStream = new FileInputStream(original);	// new input stream of the original file
		    
		    oStream.read(o);											// read the input stream in the byte array
		    input.read(d);												// read the input stream in the byte array
		    
		    for(int i = 0; i < o.length; i++)							// for each byte in the array from original file
		    {
		    	assertEquals("Bytes are equal:", true, o[i] == d[i]);	// compare it to the corresponding byte in the decompressed file
		    }
			
			oStream.close();				// close the input stream of original file
		    input.close();					// close the input stream of decompressed file
		}		
		catch (IOException e)
		{
			System.out.println("Cannot read file: I/O Exception: "  + e.getMessage() + "Stack Trace: " + e.getStackTrace());
		}
	}
	
	/**
	 * This test evaluates compression/ decompression for the "Ulysses.txt" test file. This test compares
	 * the bytes in the original file to the bytes in the decompressed file and evaluates whether the
	 * corresponding bytes are equal. 
	 * 
	 */
	@Test
	public void test14()
	{
		System.out.println("Test 14:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		try
		{
		    File inputFile  = new File("Ulysses.txt");	// create a new file object to hold the text file

		    int fileLength = (int) inputFile.length();	// get the length of the file
		    byte[] b = new byte[fileLength];			// create an array of bytes with length of the file
		    
		    FileInputStream in = new FileInputStream(inputFile);	// new input stream of the file
		    in.read(b);												// read the input stream in the byte array
		    in.close();												// close the input stream
		    
			byte[] c = a.compress(b);					// compress the file
			
			//String t = new String(c);					// put compressed file into a string
			
			File outputFile = new File("CompressedUlysses.txt"); 	// create a new file for the compressed data
			
			FileOutputStream out = new FileOutputStream(outputFile);	// output stream from file

		    out.write(c);		// write the compressed data to the file
		    out.close();		// close the file
		    
		    File compressedFile  = new File("CompressedUlysses.txt");	// create a new file object to hold the text file

		    int compressedLength = (int) compressedFile.length();		// get the length of the file
		    byte[] compressedB = new byte[compressedLength];			// create an array of bytes with length of the file
		    
		    FileInputStream compressedIn = new FileInputStream(compressedFile);	// new input stream of the file
		    compressedIn.read(compressedB);										// read the input stream in the byte array
		    compressedIn.close();												// close the input stream
		    
			byte[] decompressed = a.decompress(compressedB);		
		    
			File decompressedFile = new File("DecompressedUlysses.txt"); 	// create a new file for the decompressed data
			
			FileOutputStream decompressedOut = new FileOutputStream(decompressedFile);	// output stream from file

			decompressedOut.write(decompressed);	// write the decompressed data to the file
			decompressedOut.close();				// close the file
			
			int decompressedLength = (int) decompressedFile.length();	// get the length of the file
		    byte[] d = new byte[decompressedLength];					// create an array of bytes with length of the file
		    
		    FileInputStream input = new FileInputStream(decompressedFile);	// new input stream of the file
		    
		    File original  = new File("Ulysses.txt");			// create a new file object to hold the original text file

		    int originalLength = (int) original.length();		// get the length of the original file
		    byte[] o = new byte[originalLength];				// create an array of bytes with length of the original file
		    
		    FileInputStream oStream = new FileInputStream(original);	// new input stream of the original file
		    
		    oStream.read(o);											// read the input stream in the byte array
		    input.read(d);												// read the input stream in the byte array
		    
		    for(int i = 0; i < o.length; i++)							// for each byte in the array from original file
		    {
		    	assertEquals("Bytes are equal:", true, o[i] == d[i]);	// compare it to the corresponding byte in the decompressed file
		    }
			
			oStream.close();				// close the input stream of original file
		    input.close();					// close the input stream of decompressed file
		}		
		catch (IOException e)
		{
			System.out.println("Cannot read file: I/O Exception");
		}
	}
	
	/**
	 * This test evaluates compression/ decompression for the "Yankee.txt" test file. This test compares
	 * the bytes in the original file to the bytes in the decompressed file and evaluates whether the
	 * corresponding bytes are equal. 
	 * 
	 */
	@Test
	public void test15()
	{
		System.out.println("Test 15:\n");
		HuffmanCompressor a = new HuffmanCompressor();
		
		try
		{
		    File inputFile  = new File("Yankee.txt");	// create a new file object to hold the text file

		    int fileLength = (int) inputFile.length();	// get the length of the file
		    byte[] b = new byte[fileLength];			// create an array of bytes with length of the file
		    
		    FileInputStream in = new FileInputStream(inputFile);	// new input stream of the file
		    in.read(b);												// read the input stream in the byte array
		    in.close();												// close the input stream
		    
		    byte[] c = a.compress(b);					// compress the file

			//String t = new String(c);					// put compressed file into a string
			
			File outputFile = new File("CompressedYankee.txt"); 	// create a new file for the compressed data
			
			FileOutputStream out = new FileOutputStream(outputFile);	// output stream from file

		    out.write(c);		// write the compressed data to the file
		    out.close();		// close the file
		    
		    File compressedFile  = new File("CompressedYankee.txt");	// create a new file object to hold the text file

		    int compressedLength = (int) compressedFile.length();		// get the length of the file
		    byte[] compressedB = new byte[compressedLength];			// create an array of bytes with length of the file
		    
		    FileInputStream compressedIn = new FileInputStream(compressedFile);	// new input stream of the file
		    compressedIn.read(compressedB);										// read the input stream in the byte array
		    compressedIn.close();												// close the input stream
		    
			byte[] decompressed = a.decompress(compressedB);		
		    
			File decompressedFile = new File("DecompressedYankee.txt"); 	// create a new file for the decompressed data
			
			FileOutputStream decompressedOut = new FileOutputStream(decompressedFile);	// output stream from file

			decompressedOut.write(decompressed);	// write the decompressed data to the file
			decompressedOut.close();				// close the file
			
			int decompressedLength = (int) decompressedFile.length();	// get the length of the file
		    byte[] d = new byte[decompressedLength];					// create an array of bytes with length of the file
		    
		    FileInputStream input = new FileInputStream(decompressedFile);	// new input stream of the file
		    
		    File original  = new File("Yankee.txt");			// create a new file object to hold the original text file

		    int originalLength = (int) original.length();		// get the length of the original file
		    byte[] o = new byte[originalLength];				// create an array of bytes with length of the original file
		    
		    FileInputStream oStream = new FileInputStream(original);	// new input stream of the original file
		    
		    oStream.read(o);											// read the input stream in the byte array
		    input.read(d);												// read the input stream in the byte array
		    
		    for(int i = 0; i < o.length; i++)							// for each byte in the array from original file
		    {
		    	assertEquals("Bytes are equal:", true, o[i] == d[i]);	// compare it to the corresponding byte in the decompressed file
		    }
			
			oStream.close();				// close the input stream of original file
		    input.close();					// close the input stream of decompressed file
		}		
		catch (IOException e)
		{
			System.out.println("Cannot read file: I/O Exception: " + e.getMessage() + " Stack Trace: " + e.getStackTrace());
		}
	}
	
}
