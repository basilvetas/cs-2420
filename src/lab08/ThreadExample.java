package lab08;

public class ThreadExample
{
    /** An application that starts multiple threads of execution. */
    public static void main (String[] args)
    {
        Runnable a = new Alpha();  // Look below for class Alpha
        Runnable b = new Beta();   // Look below for class Beta
        
        Thread s = new Thread(a);    // Prepare a thread for execution in object a
        Thread t = new Thread(b);  // Prepare a thread for execution in object b
        
        s.start();  // Execution will now simultaneously begin in a.run()
        t.start();  // Execution will now simultaneously begin in b.run()
        
        // End the current thread of execution by letting main exit.
        // Other threads continue running.
        
        System.out.println ("Main is exiting.");
    }


    /** A class with a runnable method. */    
    static class Alpha implements Runnable
    {
        public void run ()
        {
            // Just a bit of code to take up a few seconds.
            double d = 0;
            for (int i = 0; i < 10000000; i++)
            {
                d += Math.sin(i);
                if (i % 1000000 == 0)
                    System.out.println ("Alpha run method, loop iteration: " + i + "  d contains: " + d);
            }
        }
    }
    
    /** Another class with a runnable method */
    static class Beta implements Runnable
    {
        public void run ()
        {
            // Just a bit of code to take up a few seconds.
            double d = 0;
            for (int i = 0; i < 100000000; i++)
            {
                d += Math.random();
                if (i % 10000000 == 0)
                    System.out.println ("Beta run method, loop iteration: " + i + "  d contains: " + d);
            }
        }
    }
}