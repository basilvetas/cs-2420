package doubledEndedPQ;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class extends HashMap in order to allow individual keys to map to duplicate elements in the 
 * MaxPriorityQueue and MinPriorityQueue classes in order to properly implement a Double-Ended PQ.
 * 
 * @author Basil Vetas
 */
public class MultiMap<K, V> extends HashMap<K, V>
{
	HashMap<K, ArrayList<V>> map;
	
	public MultiMap()
	{
		map = new HashMap<K, ArrayList<V>>();
	}
	
	
	/**
	 * Associates the specified value with the specified key in this map. 
	 * If the map previously contained a mapping for the key, the new value is added to a list value
	 * for that key.
	 * 
	 * @param K 	key with which the specified value is to be associated
	 * @param V 	value to be associated with the specified key
	 * @return V	the previous value associated with key, or null if there was no mapping for key.
	 */
	@Override
	public V put(K key, V value)
	{
		ArrayList<V> indexList;	
		V returnValue;
		
		// Check if map contains the key
		if (map.containsKey(key)) 
		{
			indexList = map.get(key);			// if so, get the key
			
			if(indexList == null)				// if the index values of that key are null
				indexList = new ArrayList<V>();	// create a new array list 
			
			returnValue = indexList.get(indexList.size()-1);
			indexList.add(value);  			// then add the value to the list
		} 
		else 	// If map does not contain the key
		{
			
			indexList = new ArrayList<V>();	// create a new list for that key
			indexList.add(value);           // add the value to the list
			returnValue = null;
		}
		
		map.put(key,indexList);				// add the key/ value set to the map
		
		return returnValue;	// we don't need to return anything
		
	}
	
	/**
	 * Removes the mapping for the specified key from this map if present.
	 * 
	 * 
	 * @param key	key whose mapping is to be removed from the map
	 * @return V	the previous value associated with key, or null if there was no mapping for key. 
	 */
	@Override
	public V remove(Object key)
	{
		ArrayList<V> indexList = map.get(key);
		
		if(indexList != null)	// if the list in not null
		{			
			V value = indexList.get(indexList.size()-1);	// temp variable
			indexList.remove(indexList.size()-1);			// remove the last index
			return value;									// and return it
		}
		else
			return null;	// otherwise just return null		
	}
	
	/**
	 * Returns the value to which the specified key is mapped, or null if this map contains no mapping for 
	 * the key.
	 * 
	 * 
	 * @param key	 the key whose associated value is to be returned
	 * @return V	the value to which the specified key is mapped, or null
	 */
	@Override
	public V get(Object key)
	{
		ArrayList<V> indexList = map.get(key);
		
		if(indexList != null)	// if the list in not null
			return indexList.get(indexList.size()-1);	// return the last index of the element
		else
			return null;
	}
	
}
