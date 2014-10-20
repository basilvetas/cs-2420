package lab08;

import java.util.ArrayList;

public class BankTest implements Runnable
{
    private BankAccount         account;
    private final static int    maxLoops = 1000000;
    private final static int    maxThreads = 16;
    private int                 threadsFinished;

    /**
     * Constructor
     */
    BankTest()
    {
        account = new BankAccount();
        threadsFinished = 0;
    }

    /**
     * 
     */
    public void run ()
    {
        if (Math.random() > 0.5)
        {
            for (int i = 0; i < maxLoops; i++)
            {
                account.deposit(Math.random() * 50);
            }
        }
        else
        {
            for (int i = 0; i < maxLoops; i++)
            {
                account.withdraw((Math.random() * 50));
            }
        }

        threadsFinished++;
    }

    /**
     * 
     * @return
     */
    public int getNumberOfCollisions()
    {
        return account.getNumberOfCollisions();
    }
 
    /**
     * 
     * @return
     */
    public int getNumberOfThreadsFinished()
    {
        return threadsFinished;
    }

    /**
     * 
     * @param args
     */
    public static void main (String[] args)
    {
        BankTest          myBankTest = new BankTest();
        ArrayList<Thread> myThreads = new ArrayList<Thread>();

        // Create maxThreads threads
        for (int i = 0; i < maxThreads; i++)
        {
            myThreads.add(new Thread(myBankTest));
        }

        // Start each thread
        for (int i = 0; i < maxThreads; i++)
        {
            myThreads.get(i).start();
        }

        // Wait for all the threads to finish
        while (myBankTest.getNumberOfThreadsFinished() < maxThreads) {}

        System.out.println("There were " + myBankTest.getNumberOfCollisions() + " collisions. ");    
    }

}