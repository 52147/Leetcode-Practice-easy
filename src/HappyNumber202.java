import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 202. Happy Number
 * 
 * Q: 
 *  - Write an algorithm to determine if a number n is happy.
 *  - A happy number is a number defined by the following process:
 *   
 *   - Starting with any positive integer, replace the number by the sum of the squares of its digits.
 *   - Repeat the process until the number equals 1 (where it will stay),
 *     or it loop endlessly in a cycle which does not include 1.
 *   - Those numbers for which this process ends in 1 are happy.
 *   
 *   - Return true if n is a happy number, and false if not.
 *
 * Example 1:
 * 
 *  Input: n = 19  
 *  
 *  Output: true
 *  
 *  Explanation:
 *   
 *   1^2 + 9^2 = 82
 *   8^2 + 2^2 = 68
 *   6^2 + 8^2 = 100
 *   1^2 + 0^2 + 0^2 = 1 -> Happy number
 *   
 *   
 * Example 2:
 * 
 *   Input: n = 2
 *   
 *   Output: false
 *   
 * Constraints:
 * 
 *   1 <= n <= 2^31 -1
 *   
 *   
 * Solution:
 * 
 * Approach 1: Detect Cycles with a HashSet
 * 
 * Intuition:
 * 
 *  - A good way to get started with a question like this is to make a couple of examples.
 *  
 *  - Let's start with the number 7.
 *  - The next number will be 49(as 7^2 =49),
 *    and then the next after that will be 97 (4^2 + 9^2 = 97).
 *  - We can continually repeat the process of squaring and then adding the digits until we get to 1.
 *  - Because we got to 1, we know that 7 is a happy number, and the function return true.
 *  
 *    7 -> 49 -> 97 -> 130 -> 10 -> 1
 *    
 *    
 *    
 *  - As another example, let's start with 116.
 *  - By repeatedly applying the squaring and adding process,
 *    we eventually get to 58, and then a bit after that, we get back to 58.
 *    - Because we are back at a number we've already seen, we know that there is a cycle,
 *      and it is impossible to ever reach 1.
 *    - So for 116, the function should return false. 
 *     
 *  116 -> 38 ->73 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 (back to 58)
 *  
 *     58 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 (a cycle)
 *     
 *  - Based on our exploration so far, we'd expect continually following links to end in one of 3 ways.   
 *    
 *    - 1. It eventually gets to 1.
 *    - 2. It eventually gets stuck in a cycle.
 *    - 3. It keeps going higher and higher, up towards infinity.
 *    
 *    -> - The 3rd option sounds really annoying to detect and handle.
 *       - How would we even know that it is going to continue going up, rather than eventually going back down, possibly to 1?
 *       - Luckily, it turns out we don't need to worry about it.
 *       - Think carefully about what the largest next number we could get for each number of digits is.
 *       
 *       Digits           Largest     		   Next
 *			  1			        9				 81						
 *			  2		           99    			162
 *			  3		          999				243
 *			  4		         9999				324
 *			 13		9999999999999		       1853
 *  
 *  - For a number with 3 digits, it is impossible for it to ever go larger than 243.
 *  - This means it will have to either get stuck in a cycle below 243 or go down to 1.
 *  
 *  - Numbers with 4 or more digits will always lose a digit at each step until they are down to 3 digits.
 *  - So we know that at worst, the algorithm might cycle around all the numbers under 243
 *    and then go back to one it's already been to(a cycle) or go to 1.
 *  - But it won't go on indefinitely, allowing us to rule out the 3rd option.  
 *  
 *  - Even though you don't need to handle the 3rd case in the code, you still need to understand why it can never happen,
 *    so that you can justify why you didn't handle it.
 *    
 *    
 * Algorithm:
 *  
 *  - There are 2 parts to the algorithm we'll need to design and code.
 *   
 *    - 1. Given a number n, what is its next number?
 *    - 2. Follow a chain of numbers and detect if we've entered a cycle.
 *  
 *  Part 1:
 *  
 *  - Part 1 can be done by using the division and modulus operators to repeatedly take digits off the number until none remain,
 *    and then squaring each removed digit and adding then together.
 *  - Have a careful look at the code for this, "picking digits off one-by-one" is a useful technique 
 *    you'll use for solving a lot of different problems.
 *  
 *  Part 2:
 *          
 *  - Part 2 can be done using HashSet.
 *  - Each time we generate the next number in the chain, we check if it's already in our HashSet.
 *  
 *    - If it is not in the HashSet, we should add it.
 *    - If it is in the HashSet, that means we're in a cycle and so should return false.
 *  
 * - The reason we use HashSet and not a Vector, List, or Array is because we're repeatedly checking whether or not numbers are in it.
 * - Checking if a number is in a HashSet takes O(1) time, whereas for the other data structures it takes O(n) time.   
 * - Choosing the correct data structures is an essential part of solving there problems.
 * 
 * 
 */
public class HappyNumber202 {
	
	// Squaring each digits and add it together.
	// For example:
	// 1.
	// n = 45
	// 45 % 10 = 5 ; d = 5
	// 45 / 10 = 4 ; n = 4
	// 5*5 = 25
	// 2.
	// 4 % 10 = 4 ; d=4
	// 4/10 = 0 ; n = 0 (because n is int not double)
	// 4*4 = 16 
	// 25 + 16 = 41
	// totalSum = 41
	
	private int getNext(int n) {
		int totalSum = 0;
		
		while(n>0) {
			int d = n % 10; 
			n = n/10; 
			
			totalSum += d * d;
			
		}
		return totalSum;
	}
	
	// Use HashSet to detect the cycle.
	public boolean isHappy(int n) {
		Set<Integer> seen = new HashSet<>();
		
		// if n is not 1 and hash set is not contain(cycle), add the getNext(n)=totalSum in the hash set.		
		while (n!=1 && ! seen.contains(n)) {
			seen.add(n);
			n = getNext(n); // keep loop the n in getNext method until it become 1.
		}
		return n == 1;
	}
	
	
		
	}
	


