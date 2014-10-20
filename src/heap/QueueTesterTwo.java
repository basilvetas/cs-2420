package heap;

/**
 * Tester class for the HeapPriorityQueue class
 * 
 * @author pajensen / Basil Vetas
 * @date April 29 2014
 */
public class QueueTesterTwo
{
    public static void main (String[] args)
    {
        // Create a priority queue using a heap with two children per node.
    	long totalComparisons = 0;
    	int numTrials = 100;
    	for(int width = 2; width < 21; width++)
    	{
    		for(int k = 0; k < numTrials; k++)
            {
    	        HeapPriorityQueue<TrackedInteger> pq = new HeapPriorityQueue<TrackedInteger>(width);
    	        
    	        for (int i = 0; i < 100000; i++)
    	        {
    	        	double j = Math.random()*1000000;
    	        	int l = (int) j;
    	        	//System.out.println(l);
    	        	TrackedInteger number = new TrackedInteger(l);
    	            pq.add(number);   
    	        }
    	        
    	        totalComparisons += TrackedInteger.comparisonCount;
    	        TrackedInteger.comparisonCount = 0;
    	        
    	        //TrackedInteger.comparisonCount = 0;
    	        //long addComparisonCount = TrackedInteger.comparisonCount;
    	        //System.out.println("Add Comparisons: " + addComparisonCount);
    	        
    	        for (int i = 0; i < 100000; i++)
    	        {
    	            pq.poll();   
    	        }
    	
    	        totalComparisons += TrackedInteger.comparisonCount;
    	        TrackedInteger.comparisonCount = 0;
    	        //System.out.println("Number of Comparisons: " + (TrackedInteger.comparisonCount));
        	}
            
            long avgComparisons = totalComparisons / numTrials; 
            System.out.println("Width:\t" + width + "\tAverage Comps:\t" + avgComparisons);
            totalComparisons = 0;
    	}
    }  
    
    /**
     * A helper class with a static variable for tracking all comparisons
     * made with any of this type of object. This TrackInteger class is code used from 
     * assignment #6 for CS 2420 Spring 2014 in the SpecialtySet Test class.
     *
     * @author Peter Jensen / Basil Vetas
     * @version 2/22/2014
     */
    private static class TrackedInteger implements Comparable<TrackedInteger>
    {
        static long comparisonCount = 0;
        
        Integer i;
        
        TrackedInteger(int i)
        {
            this.i = i;    
        }
        
        @Override
        public int compareTo (TrackedInteger o)
        {
            comparisonCount++;
            return i.compareTo(o.i);
        }
        
        @Override
        public boolean equals (Object o)
        {
            return (o instanceof TrackedInteger) ? ((TrackedInteger) o).compareTo(this) == 0 : false;
        }
        
        @Override
        public String toString ()
        {
            return "" + i;
        }

    }
    
//	pq.add(5);   
//    pq.add(1);   
//    pq.add(10);   
//    pq.add(3);   
//    pq.add(11);   
//    pq.add(8);   
//    pq.add(12);   
//    pq.add(0);   
//    pq.add(9);   
//    pq.add(6);   
//    pq.add(7);   
//    pq.add(4);   
//    pq.add(2);
    
}
