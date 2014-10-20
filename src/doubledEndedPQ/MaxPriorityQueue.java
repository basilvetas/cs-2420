package doubledEndedPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class implements a priority queue as a binary heap. Highest priority is the largest element.
 * 
 * Note: For proper implementation of this class by the double-ended priority queue, all updates made to
 * 'heap' must also be made in parallel to 'minHeap.heap'
 * 
 * @author pajensen / Basil Vetas
 * @date April 29 2014
 */
public class MaxPriorityQueue<E extends Comparable<? super E>>
{ 
	// Instance variables.
    private int width;          			// The number of children each node will have
    public ArrayList<E> heap;  				// The elements (stored in an ArrayList)
    public ArrayList<E> indices;			// The indices corresponding to the element
    private Map<E, Integer> indexMap;		// the indices of the element
    
    /**
     * This constructor builds an empty heap with a width of 2.  (The width is defined here as the number 
     * of children that each node will have.) Priority is specified using the reverse natural order 
     * of the objects. Objects that are 'greater than' other objects are first (have higher priority.)
     */
    public MaxPriorityQueue ()
    {        
        // Set up the instance variables.
        this.width = 2;
        this.heap = new ArrayList<E>();
        indexMap = new HashMap<E, Integer>();
    }

    /**
     * Returns the number of elements in the heap.
     * 
     * @return the number of elements in the heap
     */
    public int size ()
    {
        return heap.size(); 
    }
    
    /**
     * Adds an element to the heap.
     * 
     * @param element the element to add
     */
    public void add (E element)
    {
        // Compute the position of the next open space for this heap 
    	int currentIndex = heap.size();	// since size = highest index + 1

        // Add the element to the end of the heap (at this position).
        heap.add(currentIndex, element);    
    	indexMap.put(element, currentIndex);	
        
        // Compute the index of the parent position 
        int parentIndex = parent(currentIndex);
        
        // Get the parent element from the heap.
        E parent = heap.get(parentIndex);
        
        // As long as the heap condition is violated, loop and restore
        //   the heap condition for the current node and its parent node.
        while((element.compareTo(parent) > 0) && (currentIndex != 0))	
        {
        	// Store the parent node at the current position.
            heap.set(currentIndex, parent);
            indexMap.put(parent, currentIndex);	
            
            // Set the current position to the parent position.            
            currentIndex = parentIndex;
            // Compute the index of the parent position 
            parentIndex = parent(currentIndex);
            // Get the parent element from the heap.
            parent = heap.get(parentIndex);
        }
        
        // Store the element parameter in the heap at the current position.
        heap.set(currentIndex, element);
        indexMap.put(element, currentIndex);

    }
    
    /**
     * Returns and removes the highest priority element
     * from the heap.
     * 
     * @return the highest priority element in the heap
     * @throws EmptyHeapException if the heap is empty
     */
    public E poll ()
    {
    	// If there are no elements, throw an exception.
        if(heap.size() <= 0)
        	throw new EmptyHeapException ("There are no elements in the heap.");
        	
        // If there is only one element in the heap, remove it and return it.
        if(heap.size() == 1)
        {
        	indexMap.remove(heap.get(0));
        	return heap.remove(0);
        } 
        // Save the highest priority element in a temporary variable.
        E highestPriorityElement = heap.get(0);
        //indexMap.remove(heap.get(0));
        
        // Create a variable to keep track of the current position and set it to be the first position.
        int currentIndex = 0;
        
        // 	Remove the last element from the heap array, 
        //  keep it in a temporary variable.  (The heap size should get smaller.)
        indexMap.remove(heap.get(heap.size()-1));
        E lastElement = heap.remove(heap.size()-1);
        
        // 	Loop, restoring the heap condition until the condition is
        //  restored (or we arrive at a child with no children).
        //  This loop appears to be infinite, but the code breaks out of the
        //  loop when the heap condition is restored.
        while(true)
        {
	        // These variables will hold the index number and element contents of
	        //   the highest priority child of this element.
	        int hiPriIndex;
	        E hiPriElement;
	
	        // Find the highest priority child (the first child).  
	        hiPriIndex = child(currentIndex, 0);
	        
	        // If no children, break the outer loop.
	        if(hiPriIndex == -1)
	        	break;
	        else	// Set the 'best' to be the first child.
	        	hiPriElement = heap.get(hiPriIndex);
	
	        // Loop through the remaining children looking for a better one.
	        for(int child = 1; child < width; child++)
	        {
	            // Compute the index of the next child.  If none, break the inner loop.
	            int currentChildIndex = child(currentIndex, child);
	            if(currentChildIndex == -1)
	            	break;
	            
	            // Get the child element.  
	            E currentChildElement = heap.get(currentChildIndex);
	           
	            //If higher priority, save it as the highest priority child.
	            if(currentChildElement.compareTo(hiPriElement) > 0)	
	            {	
	            	hiPriIndex = currentChildIndex;
	            	hiPriElement = currentChildElement;
	            }
            }
            
            //   Compare the highest priority child against the 'last' element.
            //   If higher, we're done, heap condition will be met.  Break the loop.
            if(hiPriElement.compareTo(lastElement) < 0)				
            	break;
            else	// Otherwise, store this child in the current position and set the
            {		//   current position to be the position of the child.
            	heap.set(currentIndex, hiPriElement); 
            	indexMap.put(hiPriElement, currentIndex);
            	currentIndex = hiPriIndex;
            }   
        }
        
        // Store the last element at the current position.
        heap.set(currentIndex, lastElement);
        indexMap.put(lastElement, currentIndex);
        
        // Return the highest priority element (retrieved and saved early in this method).
        return highestPriorityElement;
    }
    
    /**
     * Returns and removes the specified element from the heap. Based on how this class is implemented by
     * the double-ended priority queue, we can assume that the parameter element E is the smallest element in
     * the corresponding minPriorityQueue (and thus the smallest element in the double-ended priority queue).
     * 
     * @param E the element to be removed (min element)
     */
    public E remove(E element)
    {
    	int index = indexMap.get(element);	// get the index of the element

    	// If there are no elements, throw an exception.
        if(heap.size() <= 0)
        	throw new EmptyHeapException ("There are no elements in the heap.");
        	
        // If there is only one element in the heap, remove it and return it.
        if(heap.size() == 1)
        {
        	indexMap.remove(heap.get(0));
        	return heap.remove(0);
        }
        // Save the highest priority element in a temporary variable.
        E highestPriorityElement = heap.get(index);
        //indexMap.remove(heap.get(index));
        
        // Create a variable to keep track of the current position and set it to be the first position.
        int currentIndex = index;
        
        // 	Remove the last element from the heap array, 
        //  keep it in a temporary variable.  (The heap size should get smaller.)
        indexMap.remove(heap.get(heap.size()-1));
        E lastElement = heap.remove(heap.size()-1);
        
        if(heap.size() == index)
        	return highestPriorityElement;
        
        // 	Loop, restoring the heap condition until the condition is
        //  restored (or we arrive at a child with no children).
        //  This loop appears to be infinite, but the code breaks out of the
        //  loop when the heap condition is restored.
        while(true)
        {
	        // These variables will hold the index number and element contents of
	        //   the highest priority child of this element.
	        int hiPriIndex;
	        E hiPriElement;
	        
	        // Find the highest priority child (the first child).  
	        hiPriIndex = child(currentIndex, 0);
	        
	        // If no children, break the outer loop.
	        if(hiPriIndex == -1)
	        	break;
	        else	// Set the 'best' to be the first child.
	        	hiPriElement = heap.get(hiPriIndex);
	
	        // Loop through the remaining children looking for a better one.
	        for(int child = 1; child < width; child++)
	        {
	            // Compute the index of the next child.  If none, break the inner loop.
	            int currentChildIndex = child(currentIndex, child);
	            if(currentChildIndex == -1)
	            	break;
	            
	            // Get the child element.  
	            E currentChildElement = heap.get(currentChildIndex);
	           
	            //If higher priority, save it as the highest priority child.
	            if(currentChildElement.compareTo(hiPriElement) > 0)	
	            {	
	            	hiPriIndex = currentChildIndex;
	            	hiPriElement = currentChildElement;
	            }
            }
            
            //   Compare the highest priority child against the 'last' element.
            //   If higher, we're done, heap condition will be met.  Break the loop.
            if(hiPriElement.compareTo(lastElement) < 0)				
            	break;
            else	// Otherwise, store this child in the current position and set the
            {		//   current position to be the position of the child.
            	heap.set(currentIndex, hiPriElement); 
            	indexMap.put(hiPriElement, currentIndex);
            	currentIndex = hiPriIndex;
            }   
        }
        
        // Store the last element at the current position.
        heap.set(currentIndex, lastElement);
        indexMap.put(lastElement, currentIndex);
        
        // Return the highest priority element (retrieved and saved early in this method).
        return highestPriorityElement;
    }
    
    /**
     * Returns but does not remove the highest priority element in this priority queue
     * 
     * @return E the element with the largest value according to natural ordering
     */
    public E peek()
    {
    	if(heap.size() == 0)	// if heap is empty
    		return null;		// just return null
    	
    	return heap.get(0);
    }
    
    /**
     * Given the index of some node in the heap, this method
     * returns the index of its parent node in the heap, or
     * 0 if the node is the heap root node.
     * 
     * @param the index (or position) of some node in the heap
     * @return the index of the parent node for this node
     */
    private int parent (int index)
    {
        // If the index is at the root, return 0.
    	if(index == 0)
    		return 0;
    	else	
    	{	// Otherwise, compute and return the index of the parent.
    		int parentIndex = (index - 1) / width;
    		return parentIndex;
    	}
    }
    
    /**
     * Given the index of some node in the heap, this method
     * returns the index of its 'nth' child.  The child number
     * is specified as a number between [0..width).  If no
     * such child exists, -1 is returned.
     * 
     * @param the index (or position) of some node in the heap
     * @param the child number, between 0 and width-1 inclusive
     * @return the index of that child node for this node, or -1 if none.
     */
    private int child (int index, int childNumber)
    {
    	// If the child number is illegal, return -1 to indicate 'no child'.
        if(childNumber > width)		
        	return -1;				
        
        // Compute the index of the child.
        int childIndex = (index*width) + childNumber + 1; 
        
        // If the childIndex is beyond the end of the heap, return -1 to indicate 'no child'.
        if(childIndex >= heap.size())
        	return -1;
        
        // Otherwise, return the child index.
        return childIndex;
    }

    /**
     * Returns the heap as a String.
     * 
     * @return the heap as a String
     */
    public String toString ()
    {
        return heap.toString();
    }
    
    /**
     * A heap-specific exception class.
     * 
     * @author pajensen
     */
    static public class EmptyHeapException extends RuntimeException
    {
        public EmptyHeapException (String message)
        {
            super (message);
        }
    }
}
