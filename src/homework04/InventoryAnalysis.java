package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class InventoryAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

    	//new warehouse report object
    	WarehouseReport report = new WarehouseReport();
    	    	
    	// Asks the user for a text file name
    	System.out.print ("Please enter a file name with .txt");

    	// creates a new scanner object, and scans in the text file name that the user provides
    	Scanner in = new Scanner (System.in);	
    	String fileName = in.next();
    	
    	// creates a new filereader object and trys to read the data in the text file provided by the user
    	FileReader inputFile = null;
    	try
    	{
    		inputFile = new FileReader(fileName);	// reads in the text file
    	}
    	catch(FileNotFoundException e){}	// catches exception if the file is not found
    	 
    	// Creates a new scanner to scan the data in the inputFile and pass it into our processLine method
        Scanner dataSet = new Scanner(inputFile);
		report.processLine(dataSet);

		// prints out the finished Warehouse report
        
        report.printOutOfStockItems();
        report.printReport();
        report.printBusiestDay();
        
    	//Program terminates
        System.exit(0); // TA - MAKE SURE THIS IS THE WAY WE WANT THE PROGRAM TO TERMINATE

	}

}
