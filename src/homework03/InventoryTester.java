package homework03;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
 
public class InventoryTester {
 
    /**
     * Application for testing Inventory class (the methods) that Basil and Luna made in the Inventory.java file.
     *
     * @author Luna Koizumi and Basil Vetas
     * @version January 22, 2014
     */
 
    public static void main(String[] args)
    {
    	// Create another item to extend item
    	// Call constructor on the superclass
    	
    	// Declaring variables
    	int quantity; // This is a variable we will use when testing our getQuantity method
    	
    	
    	// TEST #1: This is a test to make sure that the inventory list we created is accurate and our addItem method works
    	// properly for items when item name or date are different.
    	
    	Inventory<Item> tester = new Inventory<Item>();
    	Item itemOne = new Item("Item 1"); // Creating new item
    	Item itemTwo = new Item("Item 2");
    	Item itemThree = new Item("Item 3");
    	Item itemFour = new Item("Item 4");
    	Item itemFive = new Item("Item 5");
    	Item itemNegative = new Item("Item 6"); // Allows us to test whether our code can accurately skip items with null quantity.
    	GregorianCalendar dateOne = new GregorianCalendar (2014, 1, 22);
    	GregorianCalendar dateOneDuplicate = new GregorianCalendar (2014, 1, 22);
    	GregorianCalendar dateTwo = new GregorianCalendar (2013, 1, 22);
    	GregorianCalendar dateThree = new GregorianCalendar (2012, 1, 22);
    	GregorianCalendar dateThreeDuplicate = new GregorianCalendar (2012, 1, 22);
    	GregorianCalendar dateFour = new GregorianCalendar (2011, 1, 22);
    	GregorianCalendar dateFive = new GregorianCalendar (2010, 1, 22);
    	GregorianCalendar dateNegative = new GregorianCalendar (2009, 1, 22);
    	tester.addItem(itemOne, dateOne, 1);
    	tester.addItem(itemTwo, dateTwo, 2);
    	tester.addItem(itemThree, dateThree, 3);
    	tester.addItem(itemFour, dateFour, 4);
    	tester.addItem(itemFive, dateFive, 5);
    	tester.addItem(itemOne, dateFive, 1);
    	
    	System.out.println("TEST #1: \nBelow is the inventory of items, dates, and quantity. \n");
    	tester.printList();
    	System.out.println("\n");
    	
    	
    	// TEST #2: This is a test to make sure that the quantity of item increases if the item and date are the same.
    	tester.addItem(itemThree, dateThreeDuplicate, 1); // After this, we should see the quantity 4 for the "Item 3"
    	System.out.println("TEST #2: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see a quantity of 4 for Item 3");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #3: This is a test to make sure that nothing happens if the quantity being added is negative.
    	tester.addItem(itemThree, dateThreeDuplicate, -2); 
    	System.out.println("TEST #3: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same inventory list as Test #2");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #4: This is a test to make sure that nothing happens if the date is null.
    	tester.addItem(itemThree, null, 1); 
    	System.out.println("TEST #4: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same inventory list as Test #2 & #3");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #5: This is a test to make sure that nothing happens if the item is null.
    	tester.addItem(null, dateThreeDuplicate, 1); 
    	System.out.println("TEST #5: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same inventory list as Test #2, #3 & #4");
    	tester.printList();
    	System.out.println("\n");

    	// TEST #6: This is a test to make sure that the inventory eliminates the item if its quantity is 0 or less.
    	tester.removeItem(itemOne, dateOneDuplicate, 2); // After this, we should not see "Item 1"
    	System.out.println("TEST #6: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should no longer see Item 1)");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #7: This is a test to make sure that the quantity of an item decreases if the item and date are the same.
    	tester.removeItem(itemFive, dateFive, 3); // After this, we should see a quantity of 2 for "Item 5"
    	System.out.println("TEST #7: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see a quantity of 2 for Item 5)");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #8: This is a test to make sure that the removeItem function does not do anything if the item quantity is negative or 0.
    	tester.removeItem(itemNegative, dateNegative, -10); // This should have no effect on our output since quantity is negative
    	System.out.println("TEST #8: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same list as in Test #7)");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #9: This is a test to make sure that the removeItem function does not do anything when item is null.
    	tester.removeItem(null, dateNegative, 2); 
    	System.out.println("TEST #9: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same list as in Test #7 & #8)");
    	tester.printList();
    	System.out.println("\n");
    	
    	// TEST #10: This is a test to make sure that the removeItem function does not do anything when date is null.
    	tester.removeItem(null, dateNegative, 2); 
    	System.out.println("TEST #10: \nBelow is the updated inventory of items, dates, and quantity. \n" +
    			"(Note: We should see the same list as in Test #7, #8 & #9)");
    	tester.printList();
    	System.out.println("\n");
    	
    	
    	// TEST #11: This is a test to make sure our get Quantity method works when given a valid item name and date.
    	quantity = tester.getQuantity(itemTwo, dateTwo); // We should get the quantity 2
    	System.out.println("TEST #11: \nThere are " + quantity + " of Item 2.\n\n");
    	
    	
    	// TEST #12: This is a test to make sure our get Quantity method does not work when given a null item name.
    	quantity= tester.getQuantity(null, dateTwo); // Makes sure our code outputs 0 because one input is null.
    	System.out.println("TEST #12: \nThere are " + quantity + " of null date and null item.\n\n");
    	
    	// TEST #13: This is a test to make sure our get Quantity method does not work when given a null date.
    	quantity= tester.getQuantity(itemTwo, null); // Makes sure our code outputs 0 because one input is null.
    	System.out.println("TEST #13: \nThere are " + quantity + " of null date and null item.\n\n");
    	
    	// TEST #14: This is a test to make sure our getDate method works when given a valid item name input.
    	//tester.getDate(itemTwo); // We should get 2013/1/22
    	System.out.print("TEST #14: \nThe (due) date for Item 2 is ");
    	GregorianCalendar dateForItemTwo = tester.getDate(itemTwo);
    	if (dateForItemTwo != null)
    		System.out.print(dateForItemTwo.get(Calendar.YEAR)+"/"+dateForItemTwo.get(Calendar.MONTH)+"/"+dateForItemTwo.get(Calendar.DAY_OF_MONTH));
    	System.out.println(".\n\n");
    
    	// TEST #15: This is a test to make sure our getItemsPastDate method does not work when given a null date.
    	GregorianCalendar dateForDateNull = tester.getDate(null); // Makes sure our code accurately skips getDate method when inputs are null
    	System.out.print("TEST #15: \nThe (due) date for a null input for the date is ");
    	if (dateForDateNull != null)
    		System.out.print(dateForDateNull.get(Calendar.YEAR)+"/"+dateForDateNull.get(Calendar.MONTH)+"/"+dateForDateNull.get(Calendar.DAY_OF_MONTH));
    	else 
    		System.out.print("null");
    	System.out.println(".\n\n");
    	
    	// TEST #16: This is a test to make sure our getDate method can identify the older of two same items.
    	GregorianCalendar olderDate = tester.getDate(itemOne); // 
    	System.out.print("TEST #16: \nThe (due) date for a null input for item is ");
    	if (olderDate != null)
    		System.out.print(olderDate.get(Calendar.YEAR)+"/"+olderDate.get(Calendar.MONTH)+"/"+olderDate.get(Calendar.DAY_OF_MONTH));
    	System.out.println(".\n\n");
    	
    	// TEST #17: This is a test to make sure our getItemsPastDate method works when given a valid due date.
    	System.out.println("TEST #17: \nItems older than 2012/1/22 is");
    	ArrayList<Item> olderList = tester.getItemsPastDate(dateThree); // Should return Item 4 and 5
    	for (Item i : olderList)
    		System.out.println(i.getName());
    	System.out.println("\n");
    	
    	// TEST #18: This is a test to make sure our getItemsPastDate method returns no list when the date is older
    	// than any item on the list.
    	System.out.print("TEST #18: \nItems older than 2009/1/22 is ");
    	olderList = tester.getItemsPastDate(dateNegative); // Should return Item 4 and 5
    	for (Item i : olderList)
    		System.out.println(i.getName());
    	System.out.println("\n\n");
    	
    	// TEST #19: This is a test to make sure our getItemsPastDate method returns no list when the date is null
    	// than any item on the list.
    	System.out.print("TEST #19: \nItems older than a null date is ");
    	olderList = tester.getItemsPastDate(null); // Should return Item 4 and 5
    	for (Item i : olderList)
    		System.out.println(i.getName());

    }
 }