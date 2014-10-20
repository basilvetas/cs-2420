package homework04;

public class Warehouse
{
	private String name;	// name (location) of the warehouse
	//private Map<GregorianCalendar, Integer> daysTransactions = new HashMap<GregorianCalendar, Integer>();	// set of one day's total transactions
	private Inventory<FoodItem> inventory; 	// food items at the warehouse
	
	
	/**
	 * Constructor method that initializes instance variables
	 * 
	 * @param givenName
	 */
	public Warehouse(String givenName)
	{
		// initialize variables
		this.name = givenName;

		this.inventory = new Inventory<FoodItem>();
	}
	
	/**
     * Returns a String that describes this Warehouse object.
     * 
     * @return a String describing this object
     */
    public Inventory<FoodItem> getInventory ()
    {
        return this.inventory;
    }

    /**
     * Returns a String that describes this Warehouse object.
     * 
     * @return a String describing this object
     */
    public String toString ()
    {
        return "" + this.name;
    }
	
}
