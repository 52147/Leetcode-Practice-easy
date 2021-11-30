import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 
 * 1710. Maximum Units On a struck 
 * 
 *  Approach 3: Using Priority Queue. (!!Some people on LeetCode say this approach failed hidden test cases in Amazon OA!!)
 *  
 *  - There is yet another way to get the maximum units in constant time by using the Heap data structure.
 *  - We could build Max Heap where the value at the root node of the heap is always the maximum value among all its children.
 *  - So we could build a max heap based on the number of units.
 *  
 *  - In every iteration, we could pick up the box type which is at the root node, and remove it.
 *  - After removing the root node, the next node with maximum units would become the root bode.
 *  
 *  - This approach is not an optimization over Approach 2 but just another way to solve the problem.
 *  
 *  
 *  Algorithm:
 *   
 *   1.
 *   - The heap data structure can be implemented using the priority queue.
 *   - We must specify that the sort order must be based on the number of units in descending order.
 *   
 *   2.
 *   - Initially, the truck is empty, hence the remaining truck capacity that must be filled would be equal to the truck size.
 *   - Initialise variable remainingTruckSize to truckSize.
 *   
 *   3.
 *   - The element at the top of the queue is the one having maximum units.
 *   - Pick that element and remove it from the queue.
 *   
 *   4.
 *   - The number of boxes that can be put in the truck would be the minimum of remainingTruckSize and the number of boxes available of a given type.
 *   - Calculate the total number of units and reduce the truck's remaining capacity based on the number of boxes put in the truck.
 *   
 *   5.
 *   - The process of filling the truck with box types would continue until the truck is not full
 *     i.e remainingTruckSize is greater than 0.  
 *  
 *
 */
public class MaximumUnitsOnAStruck1710_3 {
	
	public int maximumUnits(int[][] boxTypes, int truckSize) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> b[1] - a[1]);
		queue.addAll(Arrays.asList(boxTypes)); // add the elements in the double array in the queue. 
		
		int unitCount = 0;
		
		while(!queue.isEmpty()) {
			// remove the head of the queue(maximum units)
			int[]top = queue.poll();
			// The minimum of truckSize and the number of the boxes will be the number of the box put on the truck.
			int boxCount = Math.min(truckSize, top[0]);
			// Count the unit of the box
			unitCount += boxCount * top[1];
			// reduce the space on the truck
			truckSize -= boxCount;
			// if truck space is 0, break
			if(truckSize == 0)
				break;
			
		}
		// return the total units of the box.
		return unitCount;
	}

}
/**
 * 
 * Complexity Analysis:
 * 
 *  - Time complexity:
 *    - O(n log n),
 *    - where n is the number of elements in array boxTypes.
 *    - We are adding all the elements of the array boxTypes in the priority queue, which takes O(n) time.
 *    
 *    - Post that, we would continue iteration until queue is not empty or remaining truck size is greater than 0.
 *    - In worst case, we might end up iterating n times.
 *    - Also, removing elements from queue would take (log n) time.
 *    - This gives us time complexity as O(n log n) + O(n) = O(n log n)
 *    
 *  - Space complexity:
 *    - O(n)
 *    - as we use a queue of size n.   
 * 
 */




