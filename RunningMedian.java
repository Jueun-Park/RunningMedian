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
	
	public static void balanceTwoHeap(PriorityQueue<Integer> min_heap, PriorityQueue<Integer> max_heap, int num)
	{
		/**
		 * This method gets the integer and put it in heaps and balance two heaps.
		 * @param min_heap	the left priority queue { @link java.util.PriorityQueue }
		 * @param max_heap	the right priority queue { @link java.util.PriorityQueue }
		 * @param num		an integer that has to be put into balanced two heaps
		 * @return			return void
		 */
		min_heap.offer(num);
		max_heap.offer(min_heap.poll());
		if (min_heap.size() < max_heap.size()) {
			min_heap.offer(max_heap.poll());
		}
	}
	
	public static double findMedian(PriorityQueue<Integer> min_heap, PriorityQueue<Integer> max_heap)
	{
		/**
		 * This method finds the median value in the number stream.
		 * @param min_heap	the balanced left priority queue to find median { @link java.util.PriorityQueue }
		 * @param max_heap	the balanced right priority queue to find median { @link java.util.PriorityQueue }
		 * @return			return median value of the stream
		 */
		if (min_heap.size() > max_heap.size()) {
			return (double)min_heap.peek();
		} else {
			return (min_heap.peek() + max_heap.peek()) / 2.0;
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
			
			balanceTwoHeap(min_heap, max_heap, current_number);
			
			System.out.print("Number Stream: ");
			for (int number: stream) {
				System.out.print(number + ", ");
			}			
			System.out.println("-> Median: " + findMedian(min_heap, max_heap));
			
		}
		scanner.close();

	}

}
