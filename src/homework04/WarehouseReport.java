package homework04;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WarehouseReport {
	
	// map of locations to warehouse objects
	private Map<String, Warehouse> locationANDwarehouse; 
	
	// Keep track of all the food items in a file
	private Map<String, FoodItem> foodItemMap;
	
	private Set<FoodItem> foodItemSet;
	
	// set of all upc codes
	private Set<String> upcCodes;
	
	// map to keep track of the busiest day for each Warehouse
	private Map<Warehouse, BusiestDay> warehouseTObusiestDays;		
	
	private GregorianCalendar currentDate; // variable to hold the current date as we read through a data file
	
	// Constructor
	public WarehouseReport()
	{
		foodItemMap = new HashMap<String, FoodItem>();
		locationANDwarehouse = new HashMap<String, Warehouse>();
		warehouseTObusiestDays = new HashMap<Warehouse, BusiestDay>();
		foodItemSet = new HashSet<FoodItem>();
	}
    
	/**
	 * This process line method scans through a data file and places 
	 * @param dataSet
	 * 			A scanner object containing an inventory data.txt file
	 */
    public void processLine(Scanner dataSet) 
	{
    	while (dataSet.hasNextLine())
		{
			String line = dataSet.nextLine();
			line = line.trim();
			String firstWord = "";
			char currentLetter;
			
			// Read through each character in the string until we find the first space
			for (int i=0; i< line.length(); i++)
			{
				currentLetter = line.charAt(i);
				if (currentLetter != ' ')
					firstWord += currentLetter;
				else 	        	// Compares if the first word equals FoodItem, Warehouse, Receive:, Request:, Start date:, Next day, End.
					if (firstWord.equals("FoodItem"))
		        	{	
		        		//System.out.println("Its a food item!");

		        		// get the Food Item name, UPC code, and shelf life from the data text file
		        		String[] foodInfo = line.split(" ");
		        		//System.out.println(Arrays.toString(foodInfo));
		        		
		        		// get the name of the food item from the data file
		        		String name = "";
		        		
		        		for(int j = 11; j < foodInfo.length; j++)
		        		{
		        			name += foodInfo[j] + " ";	// makes sure to get the entire name
		        		}
		        		
		        		// get the UPC code and shelf life from the data file
		        		String upcCode = foodInfo[4];
		        		int shelfLife = Integer.parseInt(foodInfo[8]);
		        		
		        		// adds the new food item to set of food items
		        		foodItemMap.put(upcCode, new FoodItem(name, upcCode, shelfLife)); 
		        		
		        		foodItemSet.add(new FoodItem(name, upcCode, shelfLife));
		        		
		        		//System.out.println(foodItemSet);
		        		
		        		break;
		        	}
		        		
		        	else if (firstWord.equals("Warehouse"))
		        	{
		        		// get the WareHouse Info from the data text file and put it into an array
		        		String[] warehouseInfo = line.split(" ");
		        		//System.out.println(Arrays.toString(warehouseInfo));
		        		
		        		// get the name of the food item from the data file
		        		String warehouseName = "";
		        		for(int j = 2; j < warehouseInfo.length; j++)
		        		{
		        			warehouseName += warehouseInfo[j];	// makes sure to get the entire name
		        		}
		        		
		        		// adds a warehouse name (key) and a new warehouse object (value) to our locationANDwarehouse map 		        		
		        		locationANDwarehouse.put(warehouseName, new Warehouse(warehouseName)); 
		        		
		        		//System.out.println(locationANDwarehouse);
		        		
		        		break;
		        	}
		        	
		        	else if (firstWord.equals("Start"))
		        	{
		        		// Get initial start date
		        		String[] startdateInfo = line.split(" ");
		        		
		        		//System.out.println(Arrays.toString(startdateInfo));
		        		
		        		String[] date = startdateInfo[2].split("/");	// split date components into an array string
		        		
		        		//System.out.println(Arrays.toString(date));
		        		
		        		// get day/month/year from the array string and convert to ints
		        		int year = Integer.parseInt(date[2]);
		        		int month = Integer.parseInt(date[1]);
		        		int day = Integer.parseInt(date[0]);
		        		
		        		// create a new Gregorian calendar for the given date
		        		GregorianCalendar startDate = new GregorianCalendar (year, day, month);
		        		currentDate = startDate;
		        		
		        		//System.out.println(startDate.get(Calendar.YEAR)+"/"+startDate.get(Calendar.MONTH)+"/"+startDate.get(Calendar.DAY_OF_MONTH));
		        		
		        		break;
		        	}
		        	
		        	else if (firstWord.equals("Receive:"))
		        	{
		        		//splitting the receive request into a string 
		        		String[] receiveInfo = line.split(" ");
		        		//System.out.println(Arrays.toString(receiveInfo));
		        		
		        		//get the critical (expiration) date of the listed food item, by retrieving it from our foodItemSet map
		        		GregorianCalendar criticalDate = foodItemMap.get(receiveInfo[1]).getExpirationDate(currentDate);

		        		Warehouse w = null;
		        		// if the location listed is in our warehouse map
		        		if(locationANDwarehouse.containsKey(receiveInfo[3]))
		        		{
		        			w = locationANDwarehouse.get(receiveInfo[3]);	// then we get that warehouse out of the map
		        			
		        			// and we add the given quantity of the food item to the inventory for that warehouse 
		        			w.getInventory().addItem(foodItemMap.get(receiveInfo[1]), criticalDate, Integer.parseInt(receiveInfo[2]));
		        			
		        			int transactions = Integer.parseInt(receiveInfo[2]);
			        		
		        			//System.out.println(transactions);
		        			
		        			// keep track of how many transactions were added
		        			if(!(warehouseTObusiestDays.containsKey(w)))
		        			{	//System.out.println("Create: " + w);
		        				warehouseTObusiestDays.put(w, new BusiestDay(currentDate, transactions, transactions));
		        			}
		        			else
		        			{	//System.out.println(w);
		        				warehouseTObusiestDays.get(w).addNewTransactions(currentDate, transactions);
		        			}
		        		}

		        		break;
		        	}
		        	
		        	else if (firstWord.equals("Request:"))
		        	{
		        		//splitting the receive request into a string 
		        		String[] requestInfo = line.split(" ");
		        		//System.out.println(Arrays.toString(requestInfo));
		        		
		        		Warehouse w = null;
		        		if(locationANDwarehouse.containsKey(requestInfo[3])) // if the location listed is in our warehouse map
		        		{
		        			w = locationANDwarehouse.get(requestInfo[3]);	// then we get that warehouse out of the map
		        		
		        			// get the Quantity Requested and set to qtyReq
		        			int qtyReq = Integer.parseInt(requestInfo[2]);
		        			
		        			if(!(warehouseTObusiestDays.containsKey(w)))
		        			{
		        				//System.out.println("Create" + w);
		        				warehouseTObusiestDays.put(w, new BusiestDay(currentDate, qtyReq, qtyReq));
		        			}
		        			else
		        			{
		        				//System.out.println(w);
		        				warehouseTObusiestDays.get(w).addNewTransactions(currentDate, qtyReq);
		        			}
		        			
		        			while(qtyReq > 0)
		        			{
		        				if(!(w.getInventory().contains(foodItemMap.get(requestInfo[1]))))
		        					break;
		        				
		        				GregorianCalendar oldestDate = w.getInventory().getOldestDate(foodItemMap.get(requestInfo[1]));
		        				
		        				//System.out.println(oldestDate.get(Calendar.YEAR)+"/"+oldestDate.get(Calendar.MONTH)+"/"+oldestDate.get(Calendar.DAY_OF_MONTH));
		        				
		        				int oldestQuantity = w.getInventory().getQuantity(foodItemMap.get(requestInfo[1]), oldestDate);
		        				int qtyFromOldest;
		        				
								if(qtyReq <= oldestQuantity)
								{
									// subtract requested quantity from oldest item
		        					w.getInventory().removeItem(foodItemMap.get(requestInfo[1]), oldestDate, qtyReq);

		        					// keep track of how many transactions were added
		        					qtyReq=0; // quantity should = zero since no more to subtract
								}
		        				else
		        				{
		        					// make sure we only subtract until zero, and them make qtyReq equal to the remainder
		        					qtyFromOldest = oldestQuantity;
									w.getInventory().removeItem(foodItemMap.get(requestInfo[1]), oldestDate, qtyFromOldest);
									
									// keep track of how many transactions were added
		        					qtyReq -= qtyFromOldest; //qtyReq = remainder
		        				}
		        			}
		        		}
		        	
		        		break;
		        	}
		        	
		        	else if (firstWord.equals("Next"))
		        	{
		        		// for the busiest day of each warehouse, set the current transactions equal to zero
		        		for(Warehouse w: warehouseTObusiestDays.keySet())
		        		{
		        			warehouseTObusiestDays.get(w).setCurrentTransactions(0);
		        		}
		        		
		        		// increment the current day by one, to reflect the next day		      		
		        		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		        		
		        		for(String s: locationANDwarehouse.keySet())	// for each Key 's' in the Map
		        		{
		        			Warehouse currentWarehouse = locationANDwarehouse.get(s); // get the value for Key 's' in locationANDwarehouse
	
		        			// create a variable to hold the expired food items and get the expired items
			        		ArrayList<FoodItem> expiredFoods = currentWarehouse.getInventory().getItemsPastDate(currentDate);
			        		
		        			// remove all the expired foods from the Inventory
		        			currentWarehouse.getInventory().removeAllExpired(expiredFoods);
		        			
		        		}

		        		break;
		        	}
		        	
		        	else if (firstWord.equals("End"))
		        	{		 
		        		// Ends reading in file
		        		dataSet.close();
		        		break;
		        	}	
				
			}
		}
	}

   /**
    * Determines which products exist in all warehouses and outputs UPC and name
    **/
    public void getFullyStockedItems ()
	{
    	// This is our algorithm for the fully stocked method
    	
    	// Make a set toRemove:
		// Set <String> toRemove = new HashSet();
		
		// Make a copy of the first warehouses' set of items:
		// Set <FoodItem> fullyStockedList = new HashSet();
		// fullyStockedList.addAll(warehouseOneSet);
		
		// We compare each warehouse item set to the next
		// If an item does not exist in both the fullyStockedList and warehouse set, add the item to the toRemove set
				// After each warehouse set is compared, remove all items in toRemove from the fullyStockedList
				// fullyStockedList.removeAll(toRemove);
		// Repeat this process until all warehouse sets have been compared.
		// Return fullyStockedList
		
    	// The below code need to be changed so that we go through each mapped key and compare 
    	// all of those to the next set of keys and so forth

    	
//    	Set<FoodItem> fullMaster = new HashSet<FoodItem>(); 
//    		fullMaster.addAll(foodItemSet);
//    	
//    	Set<FoodItem> emptyMaster = new HashSet<FoodItem>(); 
//		emptyMaster.addAll(foodItemSet);
//    		
//    	
//    	ArrayList<FoodItem> oneWarehouse = new ArrayList<FoodItem>();
//    	Set<FoodItem> fullyStocked = new HashSet<FoodItem>();
//    	
//    	for(String w: locationANDwarehouse.keySet())
//    	{
//    		oneWarehouse = locationANDwarehouse.get(w).getInventory().getItems();	
//    		
//    		for(FoodItem f: oneWarehouse)
//    			if(locationANDwarehouse.get(w).getInventory().contains(f))
//    				fullyStocked.add(f);
//    	}	
//    	
//    	emptyMaster.removeAll(fullyStocked);
//    	
     	//System.out.println("Fully Stocked" + fullyStocked);
    	
     	//System.out.println("Emp" + emptyMaster);
    	
    	
//    	for(String w: locationANDwarehouse.keySet())
//    	{
//    		for(FoodItem f: fullyStocked)
//    			if(!(locationANDwarehouse.get(w).getInventory().contains(f)))
//    				fullyStocked.remove(f);
//    	}
//    	
   
    	
    	
    	
//    			addAll(locationANDwarehouse.get(w).getInventory().getItems());
//    	System.out.println(fullyStocked);
//    	
//    	
//    	for(FoodItem f: fullyStocked)
//    	{
//    		f.getUpcCode();
//    		f.getName();
    	//}

	}
	
    /**
     * Determines which products DNE in any warehouse and outputs product UPC and name
     */
    public void printOutOfStockItems()
	{
    	// This is our algorithm for the out of stock method
    	
    	// Make a set toRemove: 
		// Set <FoodItem> toRemove = new HashSet();
		
		// Make a copy of the complete list from FoodItem:
		// Set <FoodItem> masterListCopy = new HashSet();
		// masterListCopy.addAll(masterList);
	
		// We compare each warehouse item set to the to the masterList. 
		// If an item exists both in the masterList and in the warehouseSet, add the item to the toRemove set
				// After each warehouse set is compared, remove all items in toRemove from masterList
				// masterList.removeAll(toRemove);
		// Repeat this process until all warehouse sets have been compared. 
		// Return the masterList
    	
    	
//    	Set<FoodItem> emptyMaster = new HashSet<FoodItem>(); 
//		emptyMaster.addAll(foodItemSet);
//    	
//		ArrayList<FoodItem> oneWarehouse = new ArrayList<FoodItem>();
//    	Set<FoodItem> fullyStocked = new HashSet<FoodItem>();
//		
//		for(String w: locationANDwarehouse.keySet())
//    	{
//    		oneWarehouse = locationANDwarehouse.get(w).getInventory().getItems();	
//    		
//    		for(FoodItem f: oneWarehouse)
//    			if(locationANDwarehouse.get(w).getInventory().contains(f))
//    				fullyStocked.add(f);
//    	}	
//    	
//    	emptyMaster.removeAll(fullyStocked);
//    	String emptySet = (String) emptyMaster.toString(); 
//    	emptySet = emptySet.trim();
//    	String UPC = emptySet.substring(22, 32);
//    	String Name = emptySet.substring(55, emptySet.length()-2);
//    	
//    	
//    	
//    	
//    	
//    	System.out.println(UPC + " " + Name);
	}
	
   /**
    * Determines the single busiest day of each warehouse and outputs the day for each warehouse
    * @return
    */	
    public void printBusiestDay()
    {
    	
    }
    
	//Print method
	public String printReport()
	{
		System.out.println("\n\nReport by Basil Vetas & Luna Koizumi\n");
		
		System.out.println("Unstocked Products:");
		
		System.out.println("\nFully-Stocked Products:");		
    	
    	System.out.println("\n" + "Busiest Days:");
		
		for(Warehouse w: warehouseTObusiestDays.keySet())
		{
			System.out.println(w + " " + warehouseTObusiestDays.get(w).getBusiestDay() + " "
								+ warehouseTObusiestDays.get(w).getMostTrans());
		}
		
		return "";
	}	
}
