package doubledEndedPQ;

import java.util.*;

/**
 * Tester class for the DoubleEndedPriorityQueue class.  This class tests whether or not the double
 * ended priority queue has the same min and max values as individual min and max PQs after removing the
 * min and max values in each.  
 * 
 * @author pajensen / Basil Vetas
 * @date April 29 2014
 */
public class QueueTesterTwo
{
    public static void main (String[] args)
    {
        // Create a double ended priority queue and a min and max PQ to check 
        
        DoubleEndedPriorityQueue<Integer> pq = new DoubleEndedPriorityQueue<Integer> ();
        MinPriorityQueue<Integer> minPQ = new MinPriorityQueue<Integer>();
        MaxPriorityQueue<Integer> maxPQ = new MaxPriorityQueue<Integer>();
        
        // Use a Random object with a specific seed so the random number
        //   sequence is the same every time.  (This makes debugging easier.) 
        
        java.util.Random generator = new Random (46981723);
        
        // Add 100 random integers between [0..1000) to the priority queue.
        //   Also keep them in an array for double-checking the queue.
        
        for (int i = 0; i < 50; i++)
        {
            int number = i*3; //generator.nextInt(1000);
            pq.add(number);   
            minPQ.add(number);
            maxPQ.add(number);
        }
        
        // Print the raw heap and expected order.
        
        System.out.println ("Elements (as stored in the heap): ");
        System.out.println (pq);
        System.out.println ("Max: " + maxPQ);
        System.out.println ("Min: " + minPQ);
        System.out.println ("Double Size: " + pq.size());
        System.out.println ("Max Size: " + maxPQ.size());
        System.out.println ("Min Size: " + minPQ.size());
        
        while (pq.size() > 0)
        {
            pq.pollMin();
            minPQ.poll();
            //maxPQ.poll();
            
            Integer pqMin = pq.getMin();
            Integer minMin = minPQ.peek();
            
            Integer pqMax = pq.getMax();
            Integer maxMax = maxPQ.peek();
            
            if((pqMin != null) && !(pqMin.equals(minMin)))
            	System.out.println("Error: Min.");
            
            //if((pqMax != null) && !(pqMax.equals(maxMax)))
            //	System.out.println("Error: Max.");
            
            //System.out.println("Min: " + pq.getMin());
            //System.out.println("Max: " + pq.getMax());
        }
        
    	//System.out.println (pq);
    	System.out.println ("Size: " + pq.size());
    	System.out.println ("Test complete.");
    }    
}
