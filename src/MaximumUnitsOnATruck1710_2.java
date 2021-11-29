import java.util.Arrays;

/**
 * 
 * Approach 2: Using Array Sort
 * 
 *   - We could simplify the process of finding the maximum units every iteration.
 *   - We could arrange the box types in a particular order such that we could get the desired box type in constant time without having to iterate over the entire array.
 *   - The simply way to implement this is to sort the array boxTypes in decreasing order of a number of units.
 *   
 *   - Once all the elements in array boxTypes are sorted in that order, 
 *     we know that box type at 0th position is the one with maximum units and one at 1st position having the second highest number of units and so on.
 *     
 *  Algorithm:
 *  
 *   1. 
 *   - Initially, the truck is empty, hence the remaining truck capacity that must be filled would be equal to the truck size.
 *   
 *   2.
 *   - Sort the array boxTypes in decreasing order of a number of units.
 *   
 *   3.
 *   - Start picking up each box type from boxTypes array starting from 0th position.
 *   - The number of boxes that can be put in the truck would be 
 *     minimum of remainingTruckSize and the number of boxes available of the given type.
 *     
 *   4.
 *   - The process of filling the truck with box types would continue until the truck is not full
 *     i.e. remainingTruckSize is greater than 0.  
 *     
 *      
 *  - The following figure illustrates the approach in detail for truckSize = 8 and boxTypes = [[3,10],[6,5],[4,7],[2,9]]
 *  
 *  
 *   Truck size = 8
 *  
 *   BoxUnits
 *   3    10
 *   6    5
 *   4    7
 *   2    9
 *  
 *   -> Sort by units in decreasing order
 *  
 *   BoxUnits
 *   3    10
 *   2    9
 *   4    7
 *   6    5
 *  
 *  
 *  1.
 *    _ _ _ _ _ _ _ _    
 *    
 *    remainingTruckSize = 8
 *    
 *  2.  
 *    x x x_ _ _ _ _      
 *    
 *    BoxUnits
 *    3    10  <-
 *    2    9
 *    4    7
 *    6    5
 *    
 *    boxCount = min(8,3) = 3 -> Put 3 boxes in the truck
 *    
 *    remainingTruckSize = 5
 *    
 *  3.
 *    x x x x x _ _ _  
 *    
 *    BoxUnits
 *    3    10  
 *    2    9   <-
 *    4    7
 *    6    5  
 *    
 *    boxCount = min(5,2) = 2 -> Put all the boxes in the truck.
 *    
 *    remainingTruckSize = 3
 *     
 *     
 *  4.
 *    x x x x x x x x
 *    
 *    BoxesUnits  
 *    3    10  
 *    2    9   
 *    4    7   <-
 *    6    5  
 *    
 *    boxCount = min(3,4) = 3 -> We can put only 3 boxes in the truck out of 4.
 *    
 *    remainingTruckSize = 0
 *    
 *    
 *    
 *
 */
public class MaximumUnitsOnATruck1710_2 {
	
	public int maximumUnits(int[][] boxTypes, int truckSize) {
		
		// Sort the array in decreasing order of number of units.
		Arrays.sort(boxTypes,(a,b) -> b[1] - a[1]);
		
		// Create a variable to calculate the total box number* unit number
		int unitCount = 0;
		
		for (int[] boxType : boxTypes) {
			
			// pick up the boxcount  in boxType array from idex 0
			int boxCount = Math.min(truckSize, boxType[0]);
			// Calculate the total number of unit
			unitCount += boxCount*boxType[1];
			// reduce the remaining truck size
			truckSize -= boxCount;
			// if the truck size is empty, break
			if(truckSize == 0)
				break;
			
		}
		return unitCount;
	}

}
/**
 * 
 * Complexity Analysis:
 * 
 *  - Time Complexity:
 *    - O(n log n)
 *    - where n is the number of elements in array boxTypes.
 *    - Sorting the array boxTypes of size n takes (n log n) time.
 *    - Post that, we iterate over each element in array boxTypes 
 *      and in worst case, we might end up iterating over all the elements in the array.
 *    - This gives us time complexity  as O(nlogn) + O(n) = O(n log n).
 *    
 *  - Space complexity:
 *    - O(1)
 *    - as we use constant extra space   
 * 
 * 
 */
