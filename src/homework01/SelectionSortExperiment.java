package homework01;

//import java.util.Arrays;

public class SelectionSortExperiment 
{
	/**
	 * This experiments with selection sort by evaluating how 
	 * frequently selection sort encounters an element that is 
	 * smaller than the current best choice for smallest element
	 * 
	 * The class repeats the experiment for arrays of size 0 to 
	 * size 1024, and repeats 20 times for each array.
	 * 
	 * The average count(n) is printed for each array of size n
	 * 
	 * @author	basilvetas
	 * @date	9 January 2014
	 */
	public static void main(String[] args) 
	{
		//Prints my name and categories
		System.out.println("Basil Vetas\nAssignment #1\n");
		System.out.println("n\tcount(n)");
		
		//repeat experiment for arrays of size 1 - 1024
		for(int i = 1; i <= 1024; i++)
		{
			int sizeOfArray = i;
			//System.out.println("size " + sizeOfArray);
			double numerator = 0;
			
			//for each array of size n, repeats expriment 20 times
			for(int repeat = 0; repeat < 20; repeat++)
			{
				double numberOfBetterValues = selectionSortExperiment(makeRandomArray(sizeOfArray));
				//System.out.println("numberOfBetterValues " + numberOfBetterValues);
				
				numerator = numerator + numberOfBetterValues;
				//System.out.println("repeat " + repeat);
				//System.out.println("numerator " + numerator);
			}
			
			//takes average of the 20 times repeated for each array
			double average = numerator/20;
			
			//prints the average count(n) for array of size n
			System.out.println(sizeOfArray + "\t" + average);
		}
	
	}

	/** Returns an array of doubles filled with random values in the
	  * range [0...1).
	  *
	  * The caller specifies a non-negative n to indicate the size of
	  *   the array.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  n  The desired size of an array.
	  * @return    An array of n doubles filled with random values
	  */
	static public double[] makeRandomArray (int n)
	{
		//creates a new array of size n
		double[] randomSet = new double[n];
		
		//fills each element with a random number
		for(int i = 0; i < n; i++)
		{
			randomSet[i] = Math.random(); 
		}
		
		//System.out.print(Arrays.toString(randomSet));
		
		//returns array filled with random numbers
		return randomSet; 
	}
	
	/** Returns a count of how many times selection sort finds a
	  * 'better' value during the sorting process.  The input array
	  * is sorted as a side effect.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  data  An array of double values
	  * @return       A count of how many times 'better' values were found
	  */
	static public int selectionSortExperiment(double[] data)
	{
		//creats variable to hold the count of how many times the
		//selectionSort has to "swap" numbers
		int count = 0;
	    // Loop for starting index
		
		//size is the length of the array
		int size = data.length;
		//System.out.print(size);
		
		//for each position in the array
		for (int firstPosition = 0; firstPosition <= size-2; firstPosition++) 
		{
			//best position starts as the first position
			int bestPosition = firstPosition;
			
			//for the current position
			for(int currentPosition = firstPosition+1; currentPosition <= size-1; currentPosition++)
			{
				//compare value at current position to the best position
				if (data[currentPosition] < data[bestPosition])
				{	
					//if less than, make the current position the new best position
					bestPosition = currentPosition; 
					count++; // add one to count
				}
			}
			//switches the best position and the first position
			double temp = data[firstPosition];
    		data[firstPosition] = data[bestPosition];
    		data[bestPosition] = temp;
		}
		//System.out.print(Arrays.toString(data));
		
		//returns the count of the number of swaps
		return count;
	}
	
}