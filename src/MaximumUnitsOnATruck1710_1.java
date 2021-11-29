/**
 * 
 * 1710. Maximum Units on a truck
 * 
 * Q.
 *   - You are assigned to put some amount of boxes onto one truck.
 *   - You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *    
 *     - numberOfBoxesi is the number of boxes of type i.
 *     - numberfUnitsPerBoxi is the number of units in each box of type i.
 *   
 *   - You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 *   - You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 *   
 *   
 *   - Return the maximum total number of units that can be put on the truck.
 *   
 *   
 *   Example 1:
 *   
 *    Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 *    Output: 8
 *    
 *    Explanation:
 *    
 *     - There are:
 *       - 1 box of the first type that contains 3 units.
 *       - 2 boxes of the second type that contain 2 units each.
 *       - 3 boxes of the third type that contain 1 unit.
 *       
 *       - You can take all the boxes of the first and second types, and one box of the third type.
 *       - The total number of units will be = (1*3) + (2*2) + (1*1) = 8
 *       
 *   Example 2:
 *   
 *    Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 *    Output: 91
 *    
 *   
 *    (5*10)+(0*5)+(2*7)+(3*9) = 50+0+14+27 = 91
 * 
 * Constraints:
 * 
 *  1 <= boxTypes.length <= 1000
 *  1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 *  1 <= truckSize <= 10^6
 *  
 * 
 * Solution:
 * 
 *  Overview:
 *   
 *   - We have to fill the truck with any box type and try to fill in the maximum number of units.
 *   - The 2D array boxTypes has the following information on each type of the box,
 *   
 *     - Number of boxes of that type
 *     - Number of units inside each box of that type
 *     
 *  - We must choose the boxes which give us maximum units.
 *  
 *  - The problem can be implemented using Greedy Approach.
 *  - We could iteratively fill the truck by picking up the boxes having maximum units from the remaining boxes at every point.
 *  - Let's look at the approach to implement the problem.
 *  
 *   
 * Approach 1: Brute Force
 * 
 *  Intuition:
 *  
 *   - We must fill the truck with maximum units.
 *   - Given the 2D array boxTypes, we could simply traverse over each box type, 
 *     find the one with maximum units and fill the truck with all the boxes of that type.
 *     
 *   - Once we fill the truck with a box type, we could mark it as used, and again find the box type with maximum units
 *     from the remaining ones.
 *   
 *   - This would continue until the truck is not full.
 *   
 *     
 *   - Let the box type with maximum unit be maxUnitsBoxType.
 *   - The number of boxes that can be put in the truck using boxes of type maxUnitsBoxType is given by maxUnitsBoxType[0].
 *   
 *   - However, we can only fill the truck until it is not full.
 *   - That is, we could only put the boxes in the truck until the remaining truck size is greater than 0.
 *   
 *   - Thus, if the remaining space is truck at a certain point is given by, remainingTruckSize, 
 *     the number of boxes that can be put in the truck can be put in the truck can be given by,
 *     
 *     boxCount = min(maxUnitsBoxType[0], remainingTruckSize)
 *     
 *       
 *     Truck size = 5
 *     Remaining capacity = 2
 *     
 *     x x x _ _
 *     
 *  Algorithm:
 *  
 *   1.
 *   - Initially, the truck is empty, hence the remaining truck capacity that must be filled would be equal to the truck size.
 *   - Initialise variable remainingTruckSize to truckSize.
 *   
 *   
 *   2.
 *   - The truck will be filled with boxes one by one until is not full.
 *   - In every iteration, we must find a box with maximum units from the remaining box types.
 *   - Let's use the method findMaxUnitBox that would return the index of a box type with maximum units given by maxUnitBoxIndex in the 2D array boxTypes.
 *   
 *   3.
 *   - Once, we have the maxUnitBoxIndex, we could find the number of boxes that we could put in the truck 
 *     as a minimum of remainingTruckSize and the number of boxes available of a given type.
 *   - Calculate the total number of units and reduce the truck's remaining capacity based on the number of boxes put in the truck.
 *   
 *   4.
 *   - Also, we must mark the current box as used.
 *   - One way of doing this would be to simply mark the number of units as -1.
 *   - When findMaxUnitBox would indicate that all the boxes are already used and we must terminate.
 *   
 *   5.
 *   - The process of filling the truck with box types would continue until the truck is not full i.e remainingTruckSize is greater that 0.
 *     
 *
 */
public class MaximumUnitsOnATruck1710_1 {
	
	// Calculate the maximum units of each type of box
	public int maximumUnits(int[][] boxTypes, int truckSize) {
		
		int unitCount = 0;
		int remainingTruckSize = truckSize;
		
		while(remainingTruckSize > 0) {
			
			int maxUnitBoxIndex = findMaxUnitBox(boxTypes);
			
			// check if all boxes are used
			if(maxUnitBoxIndex == -1)
				break;
			
			// find the box count that can be put in truck
			int boxCount = Math.min(remainingTruckSize, boxTypes[maxUnitBoxIndex][0]);
			
			unitCount += boxCount*boxTypes[maxUnitBoxIndex][1]; // Calculate the total number of units
			
			remainingTruckSize -= boxCount; // reduce the truck's remaining capacity
			
			boxTypes[maxUnitBoxIndex][1] = -1; // mark the current box as used
			
				
		}
		return unitCount;
	}  
	
	public int findMaxUnitBox(int[][] boxTypes) {
		
		int maxUnitBoxIndex = -1;
		int maxUnits = 0;
		for(int i = 0; i < boxTypes.length; i++) {
			if(boxTypes[i][1]>maxUnits) {
				maxUnits = boxTypes[i][1];
				maxUnitBoxIndex = i;
			}
		}
		return maxUnitBoxIndex;
	}
	
	

}
/**
 * Complexity Analysis:
 * 
 *  - Time complexity:
 *    - O(n^2)
 *    - where n is the number of elements in array boxTypes.
 *    - In the method findMaxUnitBox, we are iterating over all the elements in array boxTypes to find the maximum units.
 *    - In the worst case, when all the boxes are added to the truck we would iterate n times over the array of size n.
 *    - This would give total time complexity as O(n^2).
 *    
 *  - Space complexity:
 *    - O(1)
 *    - as we are using constant extra space to maintain the variables remainingTruckSize and unitCount.
 *    
 *  
 * 
 */


