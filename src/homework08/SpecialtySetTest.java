package homework08;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

/**
 * A few tests for the SpecialtySet class.  The last test demonstrates a novel
 * way to count comparisons.  Students should add more tests (especially more
 * simple tests) to validate their SpecialtySet.
 * 
 * @author Basil Vetas
 * @version 2/22/2014
 */
public class SpecialtySetTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {        
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown () throws Exception
    {        
    }

    /**
     * This test evaluates a double rotation
     * 
     */    
    @Test
    public void test25 ()
    {
    	SpecialtySet<Integer> s = new SpecialtySet<Integer>();
    	int a = 5;
    	int b = 10;
    	int c = 20;
    	int d = 30;
    	int e = 40;
    	int f = 45;
    	int g = 50;
    	int h = 60;
    	int i = 70;
    	int j = 55;
    	
    	s.add(a);
        s.add(b);
        s.add(c);
        s.add(d);
        s.add(e);
        s.add(f);
        s.add(g);
        s.add(h);
        s.add(i);
        
        s.add(j);
        
        assertEquals("The size contains: ", 10, s.size());  
    }
    
    /**
     * Test the size of a newly constructed tree.
     */
    @Test
    public void test01 ()
    {
        SpecialtySet<Double> s = new SpecialtySet<Double>();
        assertEquals("A newly constructed set should have a 0 size: ", 0, s.size());
    }

    /**
     * A simple add/contains test.
     */    
    @Test
    public void test02 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.contains("Hello");
        
        // Changed a bug from 's.contains("hello")' to 's.contains("Hello")'
        assertEquals("The set should contain 'Hello': ", true, s.contains("Hello"));  
    }
    
    /**
     * A long running add/remove/contains test that uses the
     * helper class below.  This test is VERY incomplete.
     */    
    @Test
    public void test03 ()
    {
        // Our set.
        
        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
        
        // A known good set to validate against.
        
        Set<Integer> v = new TreeSet<Integer>();
        
        // A random number generator seeded to give back the same sequence each time.
        
        Random r = new Random(8675309);
        
        // A debugging variable.
        
        int totalActions = 0;
        
        // Repeatedly 'visit' sequences of numbers.
        
        for (int repeat = 0; repeat < 100; repeat++)
        {
            // Pick a base, length, step
            
            int base = r.nextInt(1000);
            int length = r.nextInt(50) + 50;
            int step = r.nextInt(3) + 1;
     
            // Do an action the appropriate number of times.
            
            for (int i = 0; i < length; i++)
            {
                // Make the next integer in the sequence.
                
                TrackedInteger ti = new TrackedInteger(base + i * step);   
                //System.out.println("S.ti: " + ti + "\n" + "V.ti.i: " + ti.i);
                // Pick an action.
                
                int action = r.nextInt(3);
            
                // Do the action.
              
                totalActions++;
                
                if (action == 0)
                {
                    s.add(ti);    // Change our set 
                    	//System.out.println("sAdd: " + ti);
                    	//System.out.println("sAddSz: " + s.size());
                    v.add(ti.i);  // Also change the known good set
                    	//System.out.println("vAdd: " + ti.i);
                		//System.out.println("vAddSz: " + v.size());
                   	
                }
                else if (action == 1)
                {
                    s.remove(ti); // Change our set
                    	//System.out.println("sRem: " + ti);
                    	//System.out.println("sRemSz: " + s.size());
                    v.remove(ti.i);  // Also change the known good set
                    	//System.out.println("vRem: " + ti.i);
                		//System.out.println("vRemSz: " + v.size());
                }
                else if (action == 2)
                {
                    // The 'contains' method should report identically for both sets.
                	if(!(s.contains(ti) == v.contains(ti.i)))
                	{
                		//System.out.println("failS: " + ti);
                		//System.out.println("failV: " + ti.i);
                		//System.out.println("S Contains 163: " + s.contains(ti));
                		//System.out.println("V Contains 163: " + v.contains(ti.i));
                	}
                	assertEquals(s.contains(ti), v.contains(ti.i));
                }
            }
        }

        // If the specialty set is coded properly, a relatively small number of
        //   comparisons are done.
        
        //System.out.println ("Test 3: " + TrackedInteger.comparisonCount);        
        
        assertTrue ("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 63164);
        
        // Uncomment if needed.
        
        //System.out.println (totalActions);
        //System.out.println ("Test 3: " + TrackedInteger.comparisonCount);        
        //assertEquals(s.validate(), true);
    }
    
    /**
     * A simple add/contains test, but for a tree with multiple elements. This tests the ability to add 
     * elements at the bottom of a tree. Checks if contains the first element.
     * 
     */    
    @Test
    public void test04 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Z");
        s.add("Y");
        s.add("X");
        s.add("W");
        s.add("V");
        s.add("U");
        s.add("T");
        s.add("S");
        s.add("R");
        s.add("Q");
        s.add("P");
        s.add("O");
        s.add("N");
        s.add("M");
        s.add("L");
        s.add("K");
        s.add("J");
        s.add("I");
        s.add("H");
        s.add("G");
        s.add("F");
        s.add("E");
        s.add("D");
        s.add("C");
        s.add("B");
        s.add("A");
        
        assertEquals("The set should contain 'So Long': ", true, s.contains("A"));  
    }
    
    /**
     * A simple add/removes test, but for a tree with multiple elements. This tests the ability to add 
     * elements at the top of a list and remove them after. Checks if size is zero.
     */    
    @Test
    public void test05 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("A");
        s.add("B");
        s.add("C");
        s.add("D");
        s.add("E");
        s.add("F");
        s.add("G");
        s.add("H");
        s.add("I");
        s.add("J");
        s.add("K");
        s.add("L");
        s.add("M");
        s.add("N");
        s.add("O");
        s.add("P");
        s.add("Q");
        s.add("R");
        s.add("S");
        s.add("T");
        s.add("U");
        s.add("V");
        s.add("W");
        s.add("X");
        s.add("Y");
        s.add("Z");
        
        s.remove("Z");
        s.remove("Y");
        s.remove("X");
        s.remove("W");
        s.remove("V");        
        s.remove("U");        
        s.remove("T");        
        s.remove("S");
        s.remove("R");
        s.remove("Q");
        s.remove("P");
        s.remove("O");
        s.remove("N");
        s.remove("M");
        s.remove("L");
        s.remove("K");
        s.remove("J");
        s.remove("I");
        s.remove("H");
        s.remove("G");
        s.remove("F");
        s.remove("E");
        s.remove("D");
        s.remove("C");
        s.remove("B");
        s.remove("A");
        
        assertEquals("The set should contain nothing: ", 0, s.size());  
    }
    
    /**
     * A simple add/contains test, but for a list with multiple elements. Looks for a middle element.
     * This test also tests the ability to add elements at both the beginning and at the end.
     */    
    @Test
    public void test06 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Goodbye");
        s.add("So Long");
        s.add("Farewell");
        
        assertEquals("The set should contain 'Farewell': ", true, s.contains("Farewell"));  
    }
    
    /**
     * A simple add/contains test, but that attempts to add duplicate elements.
     * This test also tests the ability to add elements at the beginning and at the end
     */    
    @Test
    public void test07 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Hello");
        //s.validate();
        s.add("Goodbye");
        s.add("Goodbye");
        s.add("Goodbye");
        //s.validate();
        s.add("So Long");
        s.add("So Long");
        s.add("So Long");
        s.add("So Long");
        //s.validate();
        s.add("Farewell");
        s.add("Farewell");
        s.add("Farewell");
        s.add("Farewell");
        s.add("Farewell");
        //s.validate();
        
        assertEquals("The set should contain 'Goodbye': ", true, s.contains("Goodbye"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with multiple elements. Looks for a middle element.
     * This tests the ability to remove an element from the middle of the set.
     */    
    @Test
    public void test08 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Goodbye");
        s.add("So Long");
        s.add("Farewell");
        //s.validate();
        s.remove("Goodbye");
        //s.validate();
        
        assertEquals("The set should not contain 'Goodbye': ", true, !s.contains("Goodbye"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with multiple elements. Looks for a beginning element.
     * This tests the ability to remove an element from the beginning of the set.
     */    
    @Test
    public void test09 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Goodbye");
        s.add("So Long");
        s.add("Farewell");
        //s.validate();
        s.remove("Farewell");
        //s.validate();
        
        s.contains("Farewell");
        
        assertEquals("The set should not contain 'Farewell': ", true, !s.contains("Farewell"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with multiple elements. Looks for an end element.
     * This tests the ability to remove an element from the end of the set.
     */    
    @Test
    public void test10 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Goodbye");
        s.add("So Long");
        s.add("Farewell");
        //s.validate();
        s.remove("So Long");
        //s.validate();
        
        assertEquals("The set should not contain 'So Long': ", true, !s.contains("So Long"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with one elements. 
     */    
    @Test
    public void test11 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.remove("Hello");
        
        assertEquals("The set should not contain 'Hello' and size should be zero: ", 0, s.size());  
    }
    
    /**
     * A simple remove test, but for an empty set. 
     * 
     */    
    @Test
    public void test12 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.remove("Hello");
        //System.out.println(s.size());
        assertEquals("The set should be zero: ", 0, s.size());  
    }
    
    /**
     * A simple test to find the size of the set. The set also includes duplicates to check that they
     * 	are not added for the size. We also add elements out of order to check for any errors.
     */    
    @Test
    public void test13 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("a");
        s.add("b");
        s.add("c");
        s.add("d");
        //s.validate();
        
        s.add("c");        
        s.add("d");        
        s.add("a");
        s.add("b");
        //s.validate();
        
        s.add("d");        
        s.add("c");        
        s.add("b");
        s.add("a");
        //s.validate();
        
        s.add("d");        
        s.add("a");        
        s.add("c");
        s.add("b");
        //s.validate();
        s.size();
        
        assertEquals("The size of the set should be 4: ", 4, s.size());  
    }
    
    /**
     * A simple test to find the size of the set. The set also removes elements to check that they
     *  are not included for the size.  
     */    
    @Test
    public void test14 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.remove(1);
        s.remove(2);
        s.add(5);
        s.add(6);
        s.remove(3);
        s.remove(3);

        //s.validate();
        
        assertEquals("The size of the set should be 3: ", 3, s.size());  
    }
    
    /**
     * A simple test to check if a set contains something that it does not contain. 
     */    
    @Test
    public void test15 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        s.add(1);


        //s.validate();
        
        assertEquals("The size contains 2: ", false, s.contains(2));  
    }
    
    /**
     * A test that adds one million elements to make sure the size is correct as the set becomes large
     */    
    @Test
    public void test16 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        for(int i = 0; i < 100; i++)
        {
        	s.add(i);
        }
       // s.validate();
        assertEquals("The size contains 2: ", 100, s.size());  
    }
    
    /**
     * A simple remove/contains test, but for a list with multiple elements. Looks for an end element.
     * This tests the ability to remove an element from the end of the set.
     */    
    @Test
    public void test17 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.add("Goodbye");
        s.add("So Long");
        s.add("Farewell");
        s.add("Kitchen");
        s.add("Ukraine");
        s.add("Idaho");
        s.add("Omelet");
        //s.validate();
        s.remove("So Long");
        //s.validate();
        
        assertEquals("The set should not contain 'So Long': ", true, !s.contains("So Long"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with one elements. 
     */    
    @Test
    public void test18 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");
        s.remove("Hello");
        
        assertEquals("The set should not contain 'Hello': ", true, !s.contains("Hello"));  
    }
    
    /**
     * A simple remove/contains test, but for a list with one elements. 
     */    
    @Test
    public void test19 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("A");
        s.add("B");
        s.add("C");
        s.add("D");
        s.add("E");
        s.add("F");
        s.add("G");
        s.add("H");
        s.add("I");
        s.add("J");
        
        s.remove("J");
        s.remove("I");
        s.remove("H");
        s.remove("G");
        s.remove("F");
        s.remove("E");
        s.remove("D");
        s.remove("C");
        s.remove("B");
        s.remove("A");
        
        assertEquals("The set should have 0 elements: ", 0, s.size());  
    }
    
    /**
     * A simple remove/contains test, but for a list with one elements. 
     */    
    @Test
    public void test20 ()
    {
        SpecialtySet<String> s = new SpecialtySet<String>();
        
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        s.add("6");
        s.add("7");
        s.add("8");
        s.add("9");
        s.add("10");
        s.add("11");
        s.add("12");
        s.add("13");
        s.add("14");
        s.add("15");
        s.add("16");
        
        s.remove("7");

        assertEquals("The set should have 0 elements: ", 15, s.size());  
    }
    
    /**
     * A test that adds one million elements to make sure the size is correct as the set becomes large
     */    
    @Test
    public void test21 ()
    {
        SpecialtySet<Integer> s = new SpecialtySet<Integer>();
        for(int i = 0; i < 100; i++)
        {
        	s.add(i);
        }
        for(int i = 0; i < 50; i++)
        {
        	s.remove(i);
        }
        
       // s.validate();
        assertEquals("The size contains 2: ", 50, s.size());  
    }
    
    /**
     * Evaluates how many element comparisons, on average, are needed to add 10,000 random numbers 
     * in the range [0..999,999] to the BST-based SpecialtySet. 
     */    
    @Test
    public void test22 ()
    {
    	
        for(int j = 0; j < 1000; j++)
        {
        	SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
	        for(int i = 0; s.size() < 10000; i++)
	        {
	        	double random = 1000000*Math.random();
	        	int r = (int) random;
	        	//System.out.println("random:" + r);
	        	TrackedInteger ti = new TrackedInteger(r);
	        	s.add(ti);
	        }
	        //System.out.println ("Random:\t" + TrackedInteger.comparisonCount);
	        TrackedInteger.comparisonCount = 0;
        }
	    
        //assertEquals("The size contains: ", 10000, s.size());  
    }
    
    /**
     * Evaluates many element comparisons are needed to add 10,000 unique numbers 
     * in increasing order to the BST-basedSpecialtySet
     */    
    @Test
    public void test23 ()
    {
        for(int w = 0; w < 1000; w++)
        {
        	SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();
	        for(int i = 0; i < 10000; i++)
	        {
	        	int j = i*100000;
	        	TrackedInteger ti = new TrackedInteger(j);
	        	s.add(ti);
	        }
	        
	        //System.out.println ("Increasing: \t" + TrackedInteger.comparisonCount);
	        TrackedInteger.comparisonCount = 0;
        }  
        //assertEquals("The size contains: ", 10000, s.size());  
    }
    
    /**
     * This test evaluates a double rotation
     * 
     */    
    @Test
    public void test24 ()
    {
    	SpecialtySet<Integer> s = new SpecialtySet<Integer>();
    	int a = 5;
    	int b = 10;
    	int c = 20;
    	int d = 30;
    	int e = 40;
    	int f = 45;
    	int g = 50;
    	int h = 60;
    	int i = 70;
    	int j = 55;
    	
    	s.add(a);
        s.add(b);
        s.add(c);
        s.add(d);
        s.add(e);
        s.add(f);
        s.add(g);
        s.add(h);
        s.add(i);
        
        s.add(j);
        
        assertEquals("The size contains: ", 10, s.size());  
    }
    
    /**
     * A helper class with a static variable for tracking all comparisons
     * made with any of this type of object.
     *
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private static class TrackedInteger implements Comparable<TrackedInteger>
    {
        static long comparisonCount = 0;
        
        Integer i;
        
        TrackedInteger(int i)
        {
            this.i = i;    
        }
        
        @Override
        public int compareTo (TrackedInteger o)
        {
            comparisonCount++;
            return i.compareTo(o.i);
        }
        
        @Override
        public boolean equals (Object o)
        {
            return (o instanceof TrackedInteger) ? ((TrackedInteger) o).compareTo(this) == 0 : false;
        }
        
        @Override
        public String toString ()
        {
            return "" + i;
        }

    }
    
}
