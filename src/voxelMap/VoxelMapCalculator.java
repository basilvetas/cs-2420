package voxelMap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VoxelMapCalculator 
{

	/**
	 * This method asks the user to choose either a dome or a cone, and then enter the appropriate dimensions.
	 * The program prints to the console a 2D map of the height, in voxels, of each column of the shape.
	 * We assume that no dimension will be greater than 100 meters. We assume voxels are 1x1 meter each. 
	 * Program should complete within 30 seconds.
	 *  
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String shape = null;				// name of the shape
		float r = 0;						// radius in meters
		float h = 0;						// height in meters (only applies to dome)
		float volume = 0;					// volume of the shape
		float[][] voxelMap;					// voxel map of the shape
		final float PI = (float) Math.PI;	// value of 3.14159...
		
		Scanner console = new Scanner(System.in);
		
		boolean userInputGood = false;
		while(! userInputGood)
		{	System.out.print("Which shape would you like to calculate? Please enter either 'cone' or 'dome':");
			if (console.hasNext())
				shape = console.next();  	// get the name of the shape 
			else console.next();			
		
			// If the shape is not 'cone' or 'dome', loop and ask the user to input another name  
			if((shape.equals("cone") || (shape.equals("dome")))) userInputGood = true;
			else System.out.println("Invalid shape. Please try again.\n");
		}
		
		userInputGood = false;
		while(! userInputGood)
		{	System.out.print("Please enter the radius of your " + shape + " in meters:");;
			if (console.hasNextFloat())
				r = console.nextFloat();  	// get radius as a floating point number  
			else console.next();			
		
			// If the radius is not positive, loop and ask the user to input another number  
			if(r > 0) userInputGood = true;
			else System.out.println("Invalid radius. Please try again.\n");
		}

		//initialize voxelMap array to fit the shape (based on the radius)
		int x = (int) (2*r + 2);	// x dimension of map
		int y = (int) (2*r + 2);	// y dimension of map
		voxelMap = new float[x][y];	// let size = (x, y) 

		if(shape.equals("cone"))	// if shape is a cone
		{
			userInputGood = false;
			while(! userInputGood)
			{	System.out.print("Please enter the height of your " + shape + " in meters:");;
				if (console.hasNextFloat())
					h = console.nextFloat();  	// get height as a floating point number  
				else console.next();			
			
				// If the height is not positive, loop and ask the user to input another number  
				if(h > 0) userInputGood = true;
				else System.out.println("Invalid height. Please try again.\n");
			}

			volume = (1.0f/3.0f)*PI*r*r*h;
		}
		else
			volume = (2.0f/3.0f)*PI*r*r*r;
			
		System.out.println("The volume of your shape is: " + volume);
	
		// calculate voxel height map
		
		// note that each voxel has a volume = 1 meter since it is 1x1x1
		// create a variable to hold the volume of that voxel (1000 mm)
		
		// for level = 0 to level = height
			//for each voxel in the map
				// create a variable to hold the volume of the shape in that voxel
				// loop from zero to 1000 (the height of 1 voxel in millimeters
					// calculate the area of a 2D grid square that a 2D circle would intersect
					// create a variable to hold the area of the shape over that square
					// sum up the areas into our shape volume variable
				// divide the shape volume by the voxel volume
				// if the volume is greater than 50%, add a 1 to the voxel height map for that voxel position
		// after we do this for each voxel in our map at each height, the voxel map should be complete
			
		// will need the area of a base square = 1x1m or 1000x1000mm
		// will need area of the circle = PI*r*r
		// need to somehow start at the center
		
		
		for(int level = 0; level < h; level++)	// for each level in the map
		{
			// calculate where a voxel should be
			// add a 1 in the voxelMap where ever there is a voxel 
			// repeat
		}
		
//		for(int k = (int) r+1; k < 0 ; k--)
//		{
//			for(int l = (int) r; l < 0; l--)
//			{
//				voxelMap[k][l]++;
//			}
//		}

		
		// print out voxel height map
		System.out.println("The voxel height map of your shape is: \n");
		
		for(int i = 0; i < voxelMap.length; i++)
		{
			for(int j = 0; j < voxelMap.length; j++)
				System.out.print(voxelMap[i][j] + "  ");
			System.out.print("\n");
		}
	}
}
