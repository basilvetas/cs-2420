package homework05;

import java.util.Arrays;

/**
 * A class for testing the Quicksort Algorithm
 * 
 * @author Basil Vetas
 * @date Feb 15 2014
 */
public class Experiment1 
{
	/**
	 *@param args
	 */
	public static void main(String[] args) 
	{
		new Experiment1().testQuicksort();	// initiates the tests
	}
	
	/* Class methods & variables */
	long comparisonCounter;	// counts number of comparisons
	
	/**
	 * Constructor
	 */
	public Experiment1()
	{
		comparisonCounter = 0;
	}
	
	/**
	 * This method tests arrays of different sizes for the specified number of trials
	 */
	public void testQuicksort()
	{
		int numTrials = 1000;	// number of trials for each array size
		long totalComparisons = 0;	// total comparisons for a given array size
		
		// test arrays between [0 .. 20]
		int startArraySize1 = 0;
		int endArraySize1 = 20;

		System.out.println("Array Size: \tAverage Comparisons: "); // print data labels
		
			for(int arraySize = startArraySize1; arraySize < endArraySize1+1; arraySize++) // for each array size
			{
				totalComparisons = 0;
				
					for(int trials = 0; trials < numTrials; trials++)	// run quicksort 'numTrials' times
					{
						double[] randomData = createArrayOfSize(arraySize); // create a random array
						double[] randomCopy = Arrays.copyOf(randomData, randomData.length);	// copies the random array
						
						totalComparisons += comparisonCounter; // add current comparisons to total
						comparisonCounter = 0; //reset comparison count
						
						quickSort(randomData); 		//quicksort array
						Arrays.sort(randomCopy); 	//sort the array for comparison
		
						// check if quicksort correctly sorted the data
						for(int i =0; i < randomData.length; i++)	// for the size of the array
						{
							if(randomData[i] != randomCopy[i])		// check if each element is the same
								System.out.println("NOT SORTED");	// if not, the array is not sorted
						}	
					}
					
					// print out array size and average comparisons
					System.out.println((arraySize)  + "\t\t" + totalComparisons/numTrials);
			}
		
		// test arrays between [1 .. 65536]
		int startArraySize2 = 1;
		int endArraySize2 = 65536;
		
		System.out.println("\nArray Size: \tAverage Comparisons: "); // print data labels
		
		for(int arraySize = startArraySize2; arraySize < endArraySize2+1; arraySize = 2*arraySize)	// for each array size
		{
			totalComparisons = 0;
			
			for(int trials = 0; trials < numTrials; trials++) // run quicksort 'numTrials' times
			{
				double[] randomData = createArrayOfSize(arraySize); // create a random array
				double[] randomCopy = Arrays.copyOf(randomData, randomData.length);	// copies the random array
				
				totalComparisons += comparisonCounter; // add current comparisons to total
				comparisonCounter = 0; //reset comparison count
	
				quickSort(randomData); 		//quicksort array
				Arrays.sort(randomCopy); 	//sort the array for comparison
			
				// check if quicksort correctly sorted the data
				for(int i =0; i < randomData.length; i++)	// for the size of the array
				{
					if(randomData[i] != randomCopy[i])		// check if each element is the same
						System.out.println("NOT SORTED");	// if not, the array is not sorted
				}
			}
				// print out array size and average comparisons
				System.out.println((arraySize) + "\t\t" + totalComparisons/numTrials);
		}
	}
	
	/**
	 * Create a random array of doubles of the specified size
	 * 
	 * @param size The size of the random array
	 * @return double[] an array of random values
	 */
	public static double[] createArrayOfSize(int size)
	{
		double[] data = new double[size];
		
		for(int i = 0; i < data.length; i++)	// for each position
		{
			double randomValue = Math.random();	// create a random value
			data[i] = randomValue;	// add the value to the array
		}
		
		return data; // return populated array
	}
	
	/**
	 * This method runs quicksort by calling a recursive quicksort function
	 * 
	 * @param data	The array to be sorted
	 */
	public void quickSort(double[] data)
	{
		qSort(data, 0, data.length-1);	// call the recursive quicksort method
	}
	
	/**
	 * 
	 * @param data	The array to be sorted
	 * @param start	The position of the first element in the array
	 * @param end	The position of the final element in the array
	 */
	public void qSort(double[] data, int start, int end)
	{	
		if(end - start < 1)
			return;

		int mid = partition(data, start, end);
		qSort(data, start, mid-1);
		qSort(data, mid+1, end);
	}
	
	/**
	 * 
	 * @param data	The array to be sorted
	 * @param start	The position of the first element in the array
	 * @param end	The position of the final element in the array
	 * @return int 	The final pivot position
	 */
	public int partition (double[] data, int start, int end)
	{
	    double pivot = data[end];
	
	    int left = start;
	    int right = end-1;
	
	    while(true)
	    {
	    	while(advanceCount(data[left] < pivot))	  
	    		left = left + 1;
	    	
	    	while(advanceCount(right > left && data[right] >= pivot))
	    		right = right - 1;
	      
	    	if(left >= right)
	    		break;
	
	      //swap data elements at positions left, right
	      double temp = data[left];
	      data[left] = data[right];
	      data[right] = temp;
	        
	      left = left + 1;
	      right = right - 1;
	    }
	
	    // swap data elements at positions left, end
	    double temp = data[left];
	    data[left] = data[end];
	    data[end] = temp;  
	   
	    return left;
	}
	
	/**
	 * This is a method used to count the number of comparisons in quicksort
	 * @param b A boolean parameter
	 * @return boolean The same value as boolean parameter
	 */
	public boolean advanceCount(boolean b)	
	{
		comparisonCounter ++;	// add 1 t count
		
		return b;	// return the boolean value
	}
	
	public void print(double[] a)
	{
		for(double e: a)
			System.out.print(e + ", ");
		System.out.print("\n");
	}
}

