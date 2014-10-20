package doubledEndedPQ;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * This class implements a double-ended priority queue as a binary heap. This data structure keeps track of
 * the largest element as the lowest priority and the smallest element as the highest priority and meets 
 * these requirements: 
 * 
 * The size method returns the number of elements in the queue in O(1) time.
 * The insert method adds an element to the queue with average time cost of O(1).
 * The removeMin method removes and returns the element with the highest priority in O(lg n) time.
 * The removeMax method removes and returns the element with the lowest priority in O(lg n) time.
 * The double-ended priority queue supports duplicate elements and supports as many elements as memory allows
 * 
 * @author Basil Vetas
 * @date May 1 2014
 */
public class DoubleEndedPriorityQueue<E extends Comparable<? super E>> 
{
	private MinPriorityQueue<E> minHeap;
	private MaxPriorityQueue<E> maxHeap;
	private int queueSize;
	
	/**
	 * Constructor takes no parameters
	 */
	public DoubleEndedPriorityQueue()
	{
		// initialize variables
		minHeap = new MinPriorityQueue<E>();
		maxHeap = new MaxPriorityQueue<E>();	//11, Collections.reverseOrder()
		queueSize = 0;
	}
	
	/**
	 * Returns the number of elements in the double ended priority queue
	 * @return int 	the size of the queue
	 */
	public int size()
	{
		return queueSize;
	}
	
	/**
	 * Adds an element to the double ended priority queue
	 * 
	 * @param element
	 */
	public void add(E element)
	{
		minHeap.add(element);	// add element to minHeap
		maxHeap.add(element);	// add element to maxHeap
		
		// Basic check to make sure sizes are the same
		if(minHeap.size() != maxHeap.size())
			System.out.println("Error: The size of minHeap is " + minHeap.size() + ", and the size of MaxHeap is " + maxHeap.size());
		
		queueSize++;			// increment size
	}
	
	public E pollMin()
	{
		E minValue = minHeap.poll();			// remove from minHeap
		
//		for(E e: maxHeap)
//		{
//			if(e.equals(minValue))
//			{
//				maxHeap.remove(e);
//				break;
//			}
//		}

		maxHeap.remove(minValue);				// remove from maxHeap
		
		// Basic check to make sure sizes are the same
		if(minHeap.size() != maxHeap.size())
			System.out.println("Error: The size of minHeap is " + minHeap.size() + ", and the size of maxHeap is " + maxHeap.size());
		
		queueSize--;							// decrement size
		return minValue;						// return value from poll on minHeap
	}
	
	public E pollMax()
	{
		E maxValue = maxHeap.poll();			// remove from maxHeap
		
//		for(E e: minHeap)
//		{
//			if(e.equals(maxValue))
//			{
//				minHeap.remove(e);
//				break;
//			}
//		}
		
		minHeap.remove(maxValue);				// remove from minHeap
		
		// Basic check to make sure sizes are the same
		if(minHeap.size() != maxHeap.size())
			System.out.println("Error: The size of minHeap is " + minHeap.size() + ", and the size of maxHeap is " + maxHeap.size());
		
		queueSize--;							// decrement size
		return maxValue;						// return value from poll on maxHeap
	}
	
	/**
	 * Returns but does not remove the minimum element (highest priority) in this priority queue
	 * @return E the minimum element
	 */
	public E getMin()
	{
		return minHeap.peek();
		//.getMin();
	}
	
	/**
	 * Returns but does not remove the maximum element (lowest priority) in this priority queue
	 * @return E the maximum element
	 */
	public E getMax()
	{
		return maxHeap.peek();
		//.getMax();
	}

    /**
     * Returns the heap as a String.
     * @return the heap as a String
     */
    public String toString ()
    {
        return "Max: " + maxHeap.toString() + "\nMin: " + minHeap.toString();
    }
}
