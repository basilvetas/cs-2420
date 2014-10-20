package doubledEndedPQ;

import java.util.*;

/**
 * Tester class for the DoubleEndedPriorityQueue class
 * 
 * @author pajensen / Basil Vetas
 * @date April 29 2014
 */
public class QueueTester
{
    public static void main (String[] args)
    {
        // Create a double ended priority queue 
        
        DoubleEndedPriorityQueue<Integer> pq = new DoubleEndedPriorityQueue<Integer> ();
        
        // Use a Random object with a specific seed so the random number
        //   sequence is the same every time.  (This makes debugging easier.) 
        
        java.util.Random generator = new Random (46981723);
        
        // Add 100 random integers between [0..1000) to the priority queue.
        //   Also keep them in an array for double-checking the queue.
        
        ArrayList<Integer> backup = new ArrayList<Integer>();
        
        for (int i = 0; i < 60; i++)
        {
            int number = i*3; //generator.nextInt(1000);
            pq.add(number);   
            backup.add(number);
            //System.out.println("Size: " + pq.size());
        }

        // Sort the backup list so that it will be in priority order.
        
        Collections.sort(backup);
        
        // Print the raw heap and expected order.
        
        System.out.println ("Elements (as stored in the heap): ");
        System.out.println (pq);
        System.out.println ("Size: " + pq.size());
        
        System.out.println ("Expected elements in priority order: ");
        System.out.println (backup);
        
        // Remove and print out the elements of the queue, check to
        //   see that they are in increasing order (decreasing priority order) and
        //   that they exist in the backup array at that position.
        
        System.out.println ("Actual order: ");
        System.out.print ("[");
        String seperator = "";
        
        boolean error = false;
        int last = Integer.MIN_VALUE;
        while (pq.size() > 0)
        {
            int current = pq.pollMin();
            int expected = backup.remove(0);
            if (current < last || current != expected)
                error = true;
            last = current;
            System.out.print (seperator + current);
            seperator = ", ";
        }
        
        System.out.println ("]");
        if (error)
            System.err.println ("Error detected - see results.");
        System.out.println ("Test complete.");
    	//System.out.println (pq);
    	System.out.println ("Size: " + pq.size());
    }    
}
