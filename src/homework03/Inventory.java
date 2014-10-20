package homework03;
 
import java.util.ArrayList;
import java.util.GregorianCalendar;
 
/**
 * This is the start of Basil and Luna's CS 2420 Assignment #3.
 *
 * This class is a class called Inventory. Objects of this list represent an inventory of items,
 * whose quantity and (due) dates are kept as integers and Gregorian Calendar dates.
 *
 * @author  Luna Koizumi and Basil Vetas
 * @version January 22, 2014
 */
public class Inventory <I extends Item>
{
 
    private ArrayList<DatedItem<I>> inventory; // Variable names start with small letters

    /**
	 * Constructor creates an empty Inventory.
	 * 
	 * @param N/A
	 */
    public Inventory()
    {
    	// constructs an inventory
    	inventory = new ArrayList<DatedItem<I>>(); // Objects, like ArrayList, start with big letters
    }
 
	/**
	 * Method which adds an item to a list after making sure the item and criticalDate
	 * is not null, or the quantity is less than or equal to 0. If the item and criticalDate 
	 * being added is the same as an already-existing item, it increases the quantity rather
	 * than adding a new item on the list.
	 * 
	 * @param item - item type
	 * @param criticalDate - critical date of an item
	 * @param quantity - quantity of an item
	 */
	public void addItem(I item, GregorianCalendar criticalDate, int quantity)
	{
		if(item == null || criticalDate == null || quantity < 0) // check to make sure item and date aren't null, and qty greater than zero
			return;
		DatedItem<I> addedItem = new DatedItem<I>(item, criticalDate, quantity);	// declare a new DatedItem
		
		if(inventory.size() == 0)	// if inventory is empty, add the 'addedItem' to the inventory (don't need to check it)
			inventory.add(addedItem);
		else // if inventory is not empty, check if there are equivalent items already in it
		{
			for(int i = 0; i < inventory.size(); i++) // loop through the inventory
		    {
		    	if(inventory.get(i).equals(addedItem)) // check if items and dates match
		    	{
		    		inventory.get(i).quantity += addedItem.quantity; // if they are the same, add the item's quantity to 'i's quantity
		    		return;
		    	}	    	
		    }
    		inventory.add(addedItem); // if they don't match, add a new item to the list   
		}
	}
	 
	/**
	 * Method which decreases the quantity of an item in a list after making sure 
	 * the item and criticalDate is not null, or the quantity is less than or equal to 0.
	 * This method also removes the item if the quantity becomes less than or equal to 0.
	 * 
	 * @param item - item type
	 * @param criticalDate - critical date of an item
	 * @param quantity - quantity of an item
	 */
	public void removeItem(I item, GregorianCalendar criticalDate, int quantity)
	{
		if(item == null || criticalDate == null || quantity < 0) // check to make sure item and date aren't null, and qty greater than zero
			return; 
		DatedItem<I> removedItem = new DatedItem<I>(item, criticalDate, quantity);
	    for(int i = 0; i < inventory.size(); i++) // loop through the inventory
	    {
	    	if(inventory.get(i).equals(removedItem)) // check if items and dates match
	    		{
	    			inventory.get(i).quantity -= removedItem.quantity; // if they match, subtract removed item's quantity from 'i's quantity
	    		}
			if (inventory.get(i).quantity <= 0) // Check if quantity is equal to or less than 0
				inventory.remove(i); // If quantity is negative or 0, then remove item from list
	    }
	}
	 
	/**
	 * Method which retrieves the quantity of an item with a specific critical date in the inventory. 
	 * 
	 * @param item - item type
	 * @param criticalDate - The critical date of an item.
	 * @return The quantity for a given item and date.
	 */
	public int getQuantity(I item, GregorianCalendar criticalDate)
	{
		DatedItem<I> targetItem = new DatedItem<I>(item, criticalDate, 0);	// declare a new DatedItem with specified type and date
		for(int i = 0; i < inventory.size(); i++) // loop through the inventory
		    	if(inventory.get(i).equals(targetItem)) // check if targetItem match item 'i'
		    		return inventory.get(i).quantity;	// if it is the item we are looking for, return the quantity		
	    return 0;	// if the item is not in the list, return zero
	}
	 
	/**
	 * Method which retrieves the the critical date for a specified item in the inventory. 
	 * If there are multiple matching items in the inventory, this method retrieves the oldest
	 * critical date for the specified item. 
	 * 
	 * @param item - item type
	 * @return - Gregorian Calendar critical date for the item. null is returned if a
	 * 			 specified item is not in the inventory. 
	 */
	public GregorianCalendar getDate(I item)
	{
		GregorianCalendar temp = null;
		for(int i = 0; i < inventory.size(); i++) // loop through the inventory
		{		if(inventory.get(i).item.equals(item)) // check if items and dates match
				{	if(temp == null)	// check if temp is null
						temp = inventory.get(i).criticalDate; // if it is, set it equal to the first date
					else if(inventory.get(i).criticalDate.before(temp))	// if temp already has a date, compare it to all other dates in inventory
						temp = inventory.get(i).criticalDate;	// let temp equal the oldest date
				}
			
		}	
		    		
		return temp;	// return the oldest date
	}
	 
	
	/**
	 * Method which returns an ArrayList of items whose critical date is before the targetDate.
	 * 
	 * @param targetDate - the date we want to compare all others to
	 * @return ArrayList<I> - an ArrayList of items 
	 */
	public ArrayList<I> getItemsPastDate(GregorianCalendar targetDate)
	{
		ArrayList<I> olderList = new ArrayList<I>(); // Declare a new ArrayList of the older DatedItems
		
		for (int i = 0; i< inventory.size(); i++) // Loop through inventory 
			if(inventory.get(i).criticalDate.before(targetDate)) // if the date of Item "i" is older the target date 
				olderList.add(inventory.get(i).item); // add Item "i" to the array list of older items
		
		return olderList;	// return the new ArrayList of older DatedItems
	}
	

	/**
	 * This method allows us to loop through the inventory and print out each DatedItem object in the list
	 * 
	 * @param N/A
	 */
    public void printList ()
    {
        for (DatedItem<I> i : inventory)	// for each DatedItem 'i' in inventory
            System.out.println (i);	// print i
    }
    
    
	 
}



