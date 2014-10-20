package doubledEndedPQ;

/**
 * Tester class for the DoubleEndedPriorityQueue class.  This class tests whether or not the double
 * ended priority queue has the same min and max values as individual min and max PQs after removing the
 * min and max values in each.  
 * 
 * @author pajensen / Basil Vetas
 * @date April 29 2014
 */
public class QueueTesterThree
{
    public static void main (String[] args)
    {
        // Create a double ended priority queue and a min and max PQ to check 
        
    	long totalComparisons = 0;
    	int numTrials = 100;
    	int numElements = 1001;
    	System.out.println("n\tComparisons");
    	for(int m = 1; m < numElements; m++)
    	{
	    	for(int k = 0; k < numTrials; k++)
	        {
		        DoubleEndedPriorityQueue<TrackedInteger> pq = new DoubleEndedPriorityQueue<TrackedInteger> ();
		        
		        for (int i = 0; i < m; i++)
		        {
		        	double j = i*3; //Math.random()*1000000;
		        	int l = (int) j;
		        	//System.out.println(l);
		        	TrackedInteger number = new TrackedInteger(l);
		            pq.add(number);   
		        }
		        //System.out.println (pq);
		        //totalComparisons += TrackedInteger.comparisonCount;
		        TrackedInteger.comparisonCount = 0;
	        
		        while (pq.size() > 0)
		        {
		            pq.pollMax();
		        }
		        
		        totalComparisons += TrackedInteger.comparisonCount;
		        TrackedInteger.comparisonCount = 0;
	        }
	    	
	        long avgComparisons = totalComparisons / numTrials; 
	        System.out.println(m + "\t" + avgComparisons);
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
}
