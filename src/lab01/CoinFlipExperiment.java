package lab01;

public class CoinFlipExperiment {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] counts = new int[201];  
		int print = 0;
		while(print < 10000)
		{	int amount = coinFlipExperiment ();
			System.out.println ("Win/loss amount: " + amount);
			counts[amount+100] = counts[amount+100] + 1;
			print++;
		}
		//System.out.println(print);
		
		System.out.println ("Likelihood of win/loss amount after 100 flips:");
		  
		System.out.print ("Amount");
	  	System.out.print ("\t");  // A tab character		  
	  	System.out.print ("Probability");
		System.out.println ();

		// Loop through amounts

		double attempts = 10000.0;
		for (int i = 0; i <= 200; i++)
		{
	      System.out.print (i - 100);
	      System.out.print ("\t");
	      System.out.print (counts[i] / attempts);
	      System.out.println ();
		}
	}
	
	 /** Returns the amount of money you'd win or lose
	    * by flipping an unbalanced coin 100 times.
	    *
	    * @return  the amount of money won/lost
	    */
	static public int coinFlipExperiment ()
	{
		 int winnings = 0;
		 int count = 0;
		while(count<100)
		 {
			double flip = Math.random();
			
			 if (flip < 0.505)
			  {
				 winnings++;
			  }
			  else
			  {			
				  winnings--;
			  }
			 
			 count++;
			
		 }
		
		return winnings;
	}

}
