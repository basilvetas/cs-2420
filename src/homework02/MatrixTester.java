/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with more code to
 * sufficiently test your Matrix class. We will be using our own MatrixTester for grading.
 */
package homework02;

/**
 * Application for testing the Matrix class.
 * 
 * @author Your name here
 * @version The date goes here
 */
public class MatrixTester
{
    public static void main (String[] args)
    {
        
    	// This is test code for me Linear Algebra Midterm
    	
    	// #1. (a)

	    	 Matrix AA = new Matrix(new int[][] { { 1, 2, 3 }, { 7, 5, -5 }, { 3, -5, -6 } });
	         
	         Matrix BB = new Matrix(new int[][] { { 1, 7, 3 }, { 2, 5, -5 }, { 3, -5, -6} });
	         
	         System.out.println("\nCommute? Multiply A + A^t:\n" + AA.add(BB));
	         
	         System.out.println("\n A:\n" + AA);
	         
	         System.out.println("\n B:\n" + BB);
	    	
	         System.out.println("\nCommute? Multiply A*B:\n" + AA.multiply(BB));
	         
	         System.out.println("\nCommute? Multiply B*A:\n" + BB.multiply(AA));
    	 
    	 // #1. (b)
    	 
    	 
    	// Test Code for LA ends here
    	
    	System.out.println ("Basil Vetas");
        System.out.println ("Assignment #2\n");        
        
        // Note - when you create a new int[][], you can supply initial values, see
        //   below for the syntax.
        
        // These statements exercise the second Matrix constructor.
        
        Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

        Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

        // This Code is for my Linear Algebra Homework #3
        
        // Problem 3
        
        Matrix A = new Matrix(new int[][] { { 2, 1, -1 }, { 3, 1, 2} });
        
        Matrix B = new Matrix(new int[][] { { 1, 1 }, { 2, 0 }, { 3, 1} });
        
        Matrix C = new Matrix(new int[][] { { 1 }, { 3 } });
        
        //System.out.println("\nAdd B - A:\n" + B.add(A));
        System.out.println("\nMultiply A*B:\n" + A.multiply(B));
        
        System.out.println("\nMultiply B*C:\n" + B.multiply(C));
        
        Matrix AB = A.multiply(B);
        
        Matrix BC = B.multiply(C);
        
        System.out.println("\nMultiply (AB)*C:\n" + AB.multiply(C));
        
        System.out.println("\nMultiply A*(BC):\n" + A.multiply(BC));
        
        // Problem 5
        
        Matrix A2 = new Matrix(new int[][] { { 1, 2 }, { 3, -1 } });
        
        Matrix B2 = new Matrix(new int[][] { { 2, 0 }, { 1, 1 } });
        
        System.out.println("\nMultiply (A2B2):\n" + A2.multiply(B2));
        
        System.out.println("\nMultiply (B2A2):\n" + B2.multiply(A2));
        
        // Problem 11b.
        
        Matrix A_1 = new Matrix(new int[][] { { 1, 1, 1 }, { 0, 1, 1 }, { 0, 0, 1 } });
        
        Matrix A_squared = A_1.multiply(A_1);
        
        Matrix A_cubed = A_squared.multiply(A_1);
        
        Matrix A_fourth = A_cubed.multiply(A_1);
        
        System.out.println("\nA_1:\n" + A_1);
        
        System.out.println("\nA Squared:\n" + A_squared);
        
        System.out.println("\nA Cubed:\n" + A_cubed);
        
        System.out.println("\nA Fourth:\n" + A_fourth);
        
        // Homework Problems End Here
        
        // This is the known correct result of multiplying M1 by M2.
        
        Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
 
        /*
         * Note that none of the tests below will be correct until you have implemented all methods. This is just one
         * example of a test, you must write more tests and cover all cases.
         */

        // Get the matrix computed by your times method.
        // This statement exercises your multiply method.
        Matrix computedMultiply = M1.multiply(M2);

        // This statement exercises your toString method.
        
        System.out.println ("Test #1:");

        // This statement exercises your toString method.
        
        System.out.println("  Computed result for M1 * M2:\n" + computedMultiply);

        // This statement exercises your .equals method, and makes sure that your 
        // computed result is the same as the known correct result.
        
        if (!referenceMultiply.equals(computedMultiply))
            System.out.println("  Should be:\n" + referenceMultiply);
        else
            System.out.println ("  Correct");

        // TODO: fill in more tests that fully exercise all of your methods
        
        //This statement tests the .toString() method
        System.out.println("\nTest #2 - Print M1: \n" + M1.toString());
        
        //This statement tests the .getHeight() method
        System.out.println("Test #3 - Height of M1: " + M1.getHeight());
        
        //This statement tests the .getWidth() method
        System.out.println("Test #4 - Width of M1: " + M1.getWidth());
        
        //This statement tests the .setElement() method
        Matrix M7 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
        M7.setElement(1,1,400);
        System.out.println("\nTest #5 - Set Element (1,1) of M7 to 400: \n" + M7.toString());
        
        //This statement tests the .getElement() method
        System.out.println("Test #6 - Get Element (1,1) of M2: " + M2.getElement(1,1));
        
        //These two lines test the .add() method
        Matrix M3 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
        Matrix M4 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 }, { 3, 3, 3 } });
        System.out.println("\nTest #7 - Add M1 + M3:\n" + M1.add(M3));
        System.out.println("Test #8 - Add M1 + M4 (should return null):\n" + M1.add(M4) + "\n");
        
        // Tests the .equals() method by comparing M1 with M3
        System.out.println("Test #9 - Are M1 and M3 Equal: ");
        if(M1.equals(M3))
        	System.out.println("Yes");
        else
        	System.out.println("No");
        
        //The following code will test whether it is faster to add by rows or by columns
//        Matrix M5 = new Matrix(1000,1000);
//        Matrix M6 = new Matrix(1000,1000);
//        
//        for(int i = 0; i< M5.getWidth(); i++)
//        	for(int j = 0; j < M5.getHeight(); j++)
//        		M5.setElement(i,j,5); 
//        //System.out.println(M5.toString());
//        		
//        for(int i = 0; i< M6.getWidth(); i++)
//        	for(int j = 0; j < M6.getHeight(); j++)
//        		M6.setElement(i,j,i+j); 
//        //System.out.println(M6.toString());
//        
//        long startTime = 0, stopTime = 0;
//        
//        for(int i = 0; i < 100; i++)
//        {   startTime = System.currentTimeMillis(); 
//        	M5.add(M6);
//	        stopTime = System.currentTimeMillis();
//	        long elapsedTime = stopTime - startTime;
//	        //System.out.println(elapsedTime);
//        }
//        
        //System.out.println("\n");
        
//        long startTime2 = 0, stopTime2 = 0;
//     
//        for(int i = 0; i < 100; i++)
//        {	startTime2 = System.currentTimeMillis();
//        	M5.addColsFirst(M6);
//	        stopTime2 = System.currentTimeMillis();
//	        long elapsedTime2 = stopTime2 - startTime2;
//	        //System.out.println(elapsedTime2);
//        }
    }
}
