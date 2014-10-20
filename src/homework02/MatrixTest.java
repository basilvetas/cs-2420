package homework02;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
	
	Matrix M1;
	
	@Before
	public void setUp() throws Exception 
	{
		M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetHeight() 
	{
		
		int height = M1.getHeight();
		assertEquals(2,height);
		
	}

}
