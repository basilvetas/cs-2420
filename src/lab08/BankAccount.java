package lab08;

public class BankAccount
{
    private double balance;
    private int    collisionCount;

    BankAccount ()
    {
        balance = 100;
        collisionCount = 0;
    }
   
    /**
     * Deposit money into a bank account
     * 
     * @param amount
     */
    public synchronized void deposit(double amount)
    {
        double original_balance;
        double new_balance;

        // Compute a new balance.
        
        original_balance = balance;
        new_balance = balance + amount;

        // Check for corruption prior to updating the balance.

        if (original_balance != balance)
        {
            collisionCount++;
        }

        // Update the balance.
        
        balance = new_balance;
    }
    
    /**
     * Withdraw money from a bank account
     * 
     * @param amount
     */
    public synchronized void withdraw(double amount)
    {
        double original_balance;
        double new_balance;

        // Compute a new balance.
        
        original_balance = balance;
        new_balance = balance - amount;

        // Check for corruption prior to updating the balance.

        if (original_balance != balance)
        {
            collisionCount++;
        }

        // Update the balance.
        
        balance = new_balance;
    }

    /**
     * Accessor method for number of collisions
     * 
     * @return
     */
    public int getNumberOfCollisions()
    {
      return collisionCount;
    }
    
}