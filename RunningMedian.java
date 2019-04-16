/**
 * SWE2023-43 Java Programming Lab 2019 Spring
 * Priority Queue Practice Assignment
 * Running Median
 * @author Ju-eun Park
 */

package priority_queue_assignment;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

public class RunningMedian {
	
	public static void balanceTwoHeap(PriorityQueue<Integer> left, PriorityQueue<Integer> right, int num)
	{
		/**
		 * This method gets the integer and put it in heaps and balance two heaps.
		 * @param left		max heap { @link java.util.PriorityQueue }
		 * @param right		min heap { @link java.util.PriorityQueue }
		 * @param num		an integer that has to be put into balanced two heaps
		 * @return			return void
		 */
		right.offer(num);
		left.offer(right.poll());
		if (left.size() > right.size()) {
			right.offer(left.poll());
		}
	}
	
	public static double findMedian(PriorityQueue<Integer> left, PriorityQueue<Integer> right)
	{
		/**
		 * This method finds the median value in the number stream.
		 * @param left		the balanced max heap to find median { @link java.util.PriorityQueue }
		 * @param right		the balanced min heap to find median { @link java.util.PriorityQueue }
		 * @return			return median value of the stream
		 */
		if (left.size() < right.size()) {
			return (double)right.peek();
		} else {
			return (right.peek() + left.peek()) / 2.0;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		ArrayList<Integer> stream = new ArrayList<Integer>();
		int current_number;
		
		while (true) {
			System.out.print("Please enter a positive integer (0 to quit): ");
			current_number = scanner.nextInt();
			if (current_number <= 0) {
				break;
			}
			stream.add(current_number);
			
			balanceTwoHeap(max_heap, min_heap, current_number);
			
			System.out.print("Number Stream: ");
			for (int number: stream) {
				System.out.print(number + ", ");
			}			
			System.out.println("-> Median: " + findMedian(max_heap, min_heap));
		}
		scanner.close();
	}
}
