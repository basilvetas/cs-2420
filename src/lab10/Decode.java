package lab10;

import java.io.FileInputStream;

public class Decode 
{

	/**
	 * Reads in and decodes the secret.txt text file from CS 2420 Lab 10 Spring 2014.  
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try
		{

		/* Read the bytes from the file into an array of byte values
		 * 
		 * There is a simple constructor and a read method in the FileInputStream class that will make this part 
		 * very easy.  (See the Java API for the FileInputStream class to see the parameters for each call.)
		 *  */
		
		// Create an array of bytes (not integers) exactly large enough to hold the file.  (376 bytes)
			byte[] b = new byte[376];
			
	    // Create a FileInputStream object to read from the file (also created here).			
			FileInputStream file = new FileInputStream("secret.txt");
		
	    // Using the FileInputStream object, read enough bytes to fill the array.
			file.read(b);
			
	    // Close the FileInputStream object.
			file.close();
		
		/* Rebuild the characters using bits from the byte array */

		// Create variables to hold the message (starting out empty), a
	    //   counter to keep track of how many bits have been assembled, and an
	    //   integer for reassembling bits (starting out with all bits cleared).
			String secretMessage = "";
			int assembledCounter = 0;
			int assembledByte = 0;
			
			

	    // Loop through all 376 bytes of the file.  (Each byte becomes the 'current' byte.)

			for(int i = 0; i < b.length; i++)
			{
				// Extract the correct bit from the current byte.

				int current = b[i];
				
				int value = ((current >> 2) & 1);
				
		        // Put it in the correct position of the integer used for reassembly (if needed).

				assembledByte = (assembledByte | (value << (i % 8))); 
				
				//System.out.println("Character: " + (char) assembledByte);
				
		        // Increment the bit counter
				assembledCounter++;

		        // If eight bits have been reassembled, typecast the reassembled bits as a char and append them to the message.
		        if(assembledCounter == 8)
		        {
		        	secretMessage += (char) assembledByte;
		        	//Also, reset the bit counter and reset the integer used for reassembly to 0.
		        	assembledCounter = 0;
					assembledByte = 0;
		        }
			}
			
			System.out.println("Secret message: " + secretMessage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
}
