package homework04;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BusiestDay 
{
	private GregorianCalendar busiestDay;
	private int currentTrans;	// current days transactions at the warehouse	
	private int mostTrans;	// busiest days transactions at the warehouse

	/**
	 * Constructor 
	 * 
	 * @param currentDay
	 * @param givenTransactions
	 */
	public BusiestDay(GregorianCalendar busiestDay, int currentTrans, int mostTrans)
	{
		this.busiestDay = busiestDay;
		//System.out.println("Start " + this.busiestDay.get(Calendar.YEAR)+"/" + this.busiestDay.get(Calendar.MONTH) + "/" + this.busiestDay.get(Calendar.DAY_OF_MONTH));
		this.currentTrans = currentTrans;
		this.mostTrans = mostTrans;
	}
		
	/**
	 * This method will return the transactions for the currentDate 
	 */
	public void addNewTransactions(GregorianCalendar currentDate, Integer quantity)
	{
		this.currentTrans += quantity; 
		if(this.currentTrans >= this.mostTrans)	// check if current date trans is greater than busiest date trans
		{
			this.mostTrans = this.currentTrans;	// if so set most trans to current trans 
			this.busiestDay = (GregorianCalendar) currentDate.clone();	// and set new date to current date
		}
		
		//System.out.println("Busiest Day "+ this.busiestDay.get(Calendar.YEAR)+"/" + this.busiestDay.get(Calendar.MONTH) + "/" + this.busiestDay.get(Calendar.DAY_OF_MONTH));
		//System.out.println("Most Trans " + this.mostTrans);
		
	}
	
	/**
	 * This method will return the current transactions for that day 
	 */
	public void setCurrentTransactions(int i)
	{
		this.currentTrans = i;
	}
	
	/**
	 * This method will return the busiest day
	 */
	public String getBusiestDay()
	{
		return this.busiestDay.get(Calendar.YEAR)+"/" + this.busiestDay.get(Calendar.MONTH) + "/" + this.busiestDay.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * This method will return the current transactions for that day 
	 */
	public int getMostTrans()
	{
		return this.mostTrans;
	}
	
	/**
	 * To string method for busiest day
	 */
	public String toString ()
    {
        return "" + this.busiestDay + "\t trans" + this.mostTrans;
    }
}
