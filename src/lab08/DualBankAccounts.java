 package lab08;

/**
 * A class for demonstrating deadlock problems.
 */
public class DualBankAccounts implements Runnable
{
    private BankAccount A;
    private BankAccount B;
    private int threadCount = 0;

    /**
     *  Builds two bank account objects and stores them in 'this'.
     */
    DualBankAccounts()
    {
        A = new BankAccount();
        B = new BankAccount();
    }

    /**
     * This method is the starting point for each thread of execution.  Two
     *   threads will run on this code.
     */
    public void run ()
    {
        // Assign a unique integer to this thread.
 
        int myThreadNumber;
        
        synchronized(this)
        {
            threadCount++;
            myThreadNumber = threadCount;
        }

        // Each thread will do different work.
 
        if (myThreadNumber == 1)
        {
	    // One thread will run this code.
	    
            synchronized(A)
            {
                try
                {
                    Thread.sleep(500);  // Simulates some other work.
                }
                catch (InterruptedException e) {}
                
                synchronized(B)
                {
                  A.withdraw(25);
                  B.deposit(25);
                }
            }
        }
        else
        {
	    // The other thread will run this code.
	    
            synchronized(A)
            {
                try
                {
                    Thread.sleep(500);  // Simulates some other work.
                }
                catch (InterruptedException e) {}
                
                synchronized(B)
                {
                    B.withdraw(25);
                    A.deposit(25);
                }
            }
        }
        
        System.out.println ("Done");
    }

    /**
     * The application entry point.
     */
    public static void main (String[] args)
    {
       DualBankAccounts    myBankAccount = new DualBankAccounts();
 
       Thread thread1 = new Thread(myBankAccount);
       Thread thread2 = new Thread(myBankAccount);
        
       thread1.start();
       thread2.start();
    }
} 