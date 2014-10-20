package homework03;
 
import java.util.GregorianCalendar;
 
/**
 * A very simple class whose objects contain a single item, quantity, and date. 
 * Includes a few helper methods to help you identify if this DatedItem matches another DatedItem,
 * or if this DatedItem is older than another DatedItem.
 *
 * @author Basil Vetas and Luna Koizumi
 * @version January 22, 2014
 */
public class DatedItem<T extends Item>
{  
    // Instance variables
     
    protected T item; // name of the item
    protected int quantity; // number of items
    protected GregorianCalendar criticalDate; // date of the item
     
    /* Constructor */
     
    /**
     * Constructs a DatedItem with the given item, quantity, and date.
     *
     * @param type - a name for the item
     * @param quantity - quantity of items
     * @param date - date of the item
     */
    public DatedItem(T givenItem, GregorianCalendar givenDate, int givenQuantity)
    {
        // initialize variables
        this.item = givenItem;
        this.quantity = givenQuantity;
        this.criticalDate = givenDate;
    }
     
    /**
     * Method to compare if 'this' DatedItem and criticalDate matches 
     * another DatedItem and criticalDate.
     * 
     * @param compareType - The DatedItem you are comparing 'this' with
     * @return a true or false
     */
    public boolean equals (DatedItem<T> compareType)
    {
    	boolean itemMatches = this.item.equals(compareType.item); // checks if 'this' item matches 'compareType' item
    	boolean dateMatches = this.criticalDate.equals(compareType.criticalDate); // check if 'this' date matches 'compareType' date
        return (itemMatches && dateMatches); // return true if they both match
    }
    
    /**
     * Method to compare if 'this' date is older than another date
     * 
     * @param targetDate - date we want to compare with
     * @return a true or false
     */
    public boolean isOlderThan (GregorianCalendar targetDate)
    {
    	return (this.criticalDate.before(targetDate));	// check if 'this' date is older than (before) the targetDate
    }
    /**
     * Allows us to correctly print out a DatedItem object
     * 
     * @param 
     * @return String - the string value of a DatedItem (item name, date, quantity)
     */
    public String toString()
    {
    	// declare string
    	String s = "Item: " + item.getName() + "\tDate: " + criticalDate.get(1) +"/" + 
    				criticalDate.get(2) + "/" + criticalDate.get(5)+ "\tQuantity: " + quantity; 
    	return s;
    }

}

