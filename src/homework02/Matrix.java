/**
 * This file is the starting point for homework assignment02.
 */

package homework02;

/**
 * Objects of this class represent a matrix (from mathematics).  Matrix
 * elements are kept as long integer values.
 * 
 * @author Your name goes here.
 * @version The current date goes here.
 */
public class Matrix
{
    // Fields - use these instance variables in your solution
    
    private int numRows;
    private int numCols;
    private long elements[][];
    
    /**
     *  Creates an empty matrix of the specified size.  All entries are
     *  set to 0.
     *  
     *  @param   rows   the number of rows to be in this matrix
     *  @param   cols   the number of columns to be in this matrix
     *  @throws  IllegalMatrixSizeException if rows or cols are non-positive
     */ 
    public Matrix (int rows, int cols)
    {
    	this.numRows = rows; // initialize rows 
        this.numCols = cols; // initialize columns
    	
    	if(this.numRows < 0 || this.numCols < 0) // if rows or columns are non-positive, throws exception
        	throw new IllegalMatrixSizeException();
       
    	this.elements = new long[this.numRows][this.numCols]; // creates and empty matrix of the specified size
    }
    
    /**
     *  Creates this matrix from an existing 2D array of integer data.
     *  The contents of the array are copied into this matrix (so that
     *  the caller can change the input array without changing this matrix).
     *  
     *  The size of this matrix will match the size of the input data, so the
     *  input data array dimensions must be non-zero.
     *  
     *  @param   d   a 2D array of integer data to place in this matrix
     *  @throws  IllegalMatrixSizeException if the input array is empty or non-rectangular
     */  
    public Matrix (int data[][])
    {
        // Extract the size from the array, make sure it is rectangular.
        
        this.numRows = data.length; // d.length is the number of 1D arrays in the 2D array, the length of the first dimension
        if (this.numRows == 0)
            throw new IllegalMatrixSizeException();
        
        for (int row = 0; row < this.numRows; row++)  // Check to see all rows are the same width
            if (data[row].length != data[0].length)
                throw new IllegalMatrixSizeException();

        this.numCols = data[0].length; // d[0] is the first 1D array, the first row
                
        // Create the matrix, copy the values from the input array.
        
        this.elements = new long[this.numRows][this.numCols];
        
        for(int i = 0; i < this.numRows; i++) // loops through rows
        	for(int j = 0; j < this.numCols; j++) // loops through columns
        		this.elements[i][j] = data[i][j]; //sets each entry to zero
    }
      
    /**
     * Returns the number of columns in this matrix.
     * 
     * @return  the number of columns in this matrix
     */
    public int getWidth ()
    {
    	int width = numCols; // gets number of columns
        return width;  // returns number of columns
    }
    
    
    /**
     * Returns the number of rows in this matrix.
     * 
     * @return  the number of rows in this matrix
     */
    public int getHeight ()
    {
    	int height = numRows; // gets number of rows
        return height;  // returns number of rows
    }
    
    /**
     * Changes one location in this matrix to the specified value.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @param value  the new value for this element in the matrix
     */
    public void setElement (int row, int col, long value)
    {
        this.elements[row][col] = value; // sets a specific element to a specific value
    }
    
    /**
     * Returns the value from one location in this matrix.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @return the value of the specified element
     */
    public long getElement (int row, int col)
    {
    	long element = this.elements[row][col]; // gets a specified element 
        return element;  //returns the element
    }
    
    /**
     * Returns a multi-line string representing the contents of this matrix.
     * If printed, the returned string will resemble the mathematical diagram
     * of a matrix.  There will be single spaces between values and newline 
     * characters at the end of every row.  The returned string will only 
     * contain numbers, single spaces, and newline characters ('\n').
     * 
     * If the matrix contains {{1, 2, 3},{4, 5, 6},{7, 8, 9}}, this method would
     * return:
     *   "1 2 3\n4 5 6\n7 8 9\n"
     *   
     * @return a printable string representing this matrix
     */
    @Override
    public String toString ()
    {
    	String matrixOutput = ""; 
    	
    	for(int i = 0; i< this.numRows; i++) // loop through rows
    	   for(int j = 0; j < this.numCols;j++) // loop through columns
    	   {
    		   matrixOutput += this.elements[i][j]; // add element to the string at position i (row) and j (column)
    		   if(j == this.numCols-1) // if the matrix is at the end of the row
    			   matrixOutput += ("\n");  // start a new row
    		   else 
    			   matrixOutput += (" "); // if not, add a space between numbers 	
    	   }
        return matrixOutput;  // returns output
    }

    /**
     * Returns the matrix product between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the product.
     * 
     * For the multiplication, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
	     * 
	     * @param  m  a matrix for the righthand side of matrix multiplication 
	     * @return the product (this * m)
	     */
	 	public Matrix multiply (Matrix m)
	    {
	 		if(this.numCols != m.numRows) // check if the matrices are compatible sizes to multiply
	 			return null; // if not return null
	        else 
	        { 	
	        	int[][] multipliedData = new int [this.numRows][m.numCols]; //create new 2D array to hold results
	        	
				for(int i = 0; i < this.numRows; i++) // loop through rows of 'this' matrix
	        		for(int j = 0; j < m.numCols; j++) // loop through columns of matrix m
	        			for(int k = 0; k < this.numCols; k++) // loop through columns of 'this' matrix
	        				multipliedData[i][j] += (int) this.elements[i][k]*m.elements[k][j]; // multiply elements
				
	        	Matrix multipliedMatrix = new Matrix(multipliedData); // create new matrix and populate with data
	    		return multipliedMatrix; // return new matrix
	        }
	    }

    /**
     * Returns the matrix sum between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the sum.
     * 
     * For the addition, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param m  a matrix for the righthand side of matrix addition 
     * @return  the sum (this + m)
     */
    public Matrix add (Matrix m)
    {
    	if((this.numCols != m.numCols) || (this.numRows != m.numRows)) // check if the sizes are compatible to add
	    	return null;
    	else
    	{
    		int[][] addedData = new int [this.numRows][numCols]; //create new 2D array to store the results
    		for(int i = 0; i < this.numRows; i++) // loop through Columns
    			for(int j = 0; j < this.numCols; j++) // loop through Rows
    				addedData[i][j] = (int) (this.elements[i][j] + m.elements[i][j]); // add element at given positin
    		Matrix addedMatrix = new Matrix(addedData); // create new Matrix and populate with data
    		return addedMatrix; //return new matrix
    	}
    }
    
    /**
     * Returns the matrix sum between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the sum.
     * 
     * For the addition, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param m  a matrix for the righthand side of matrix addition 
     * @return  the sum (this + m)
     */
//    public Matrix addColsFirst (Matrix m)
//    {
//    	if((this.numCols != m.numCols) || (this.numRows != m.numRows))
//	    	return null;
//    	else
//    	{
//    		int[][] addedData = new int [this.numRows][numCols];
//    		for(int i = 0; i < this.numCols; i++)
//    			for(int j = 0; j < this.numRows; j++)
//    				addedData[j][i] = (int) (this.elements[j][i] + m.elements[j][i]);
//    		Matrix addedMatrix = new Matrix(addedData);
//    		return addedMatrix;
//    	}    	
//    }
       
    /**
     * Returns true if Object o is a matrix and o is equal to this
     * matrix, and false otherwise.  Two matrices are equal if they
     * have the same dimensions and the the same values.
     * 
     * @return true if this matrix is equivalent to matrix o
     */    
    @Override
    public boolean equals (Object o)
    {
        if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
            return false;
        Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

       
        if((this.numCols != m.numCols) || (this.numRows !=m.numRows)) // checks if the matrix from Object o is the same size as 'this' matrix 
	    	return false; // if not same size, return false
    	else // if same size, run loop
    	{
    		for(int i = 0; i < this.numRows; i++) // loop through rows
    			for(int j = 0; j < this.numCols; j++) // loop through columns
    				if(this.elements[i][j] != m.elements[i][j]) // if element is not the same return false
			    		return false;
			return true; // if the loop is completed, return true	    			
    	}
    }
}

/**
 * This exception type is used when a matrix is created with illegal dimensions.
 * 
 * @author Peter Jensen
 * @Version January 11, 2014
 */
class IllegalMatrixSizeException extends RuntimeException
{
    // No overriding needed.
}
