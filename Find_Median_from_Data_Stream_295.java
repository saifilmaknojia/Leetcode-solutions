import java.util.*;

public class Find_Median_from_Data_Stream_295 {

}

class MedianFinder {

    /**
     * @author Shaifil
     *
     */

    /** initialize your data structure here. */
    // Wonder full concept, inorder to find median we use 2 heaps, one min and one
    // max
    // we know that the median is the middle of a sorted array
    // so what we can do is we can use 2 priority queues, the max heap will hold the
    // left half of the array, so the top element of the max_heap would hold the
    // middle_left i.e the largest value in the left half
    // similarly the min_heap will hold the middle_right i.e the smallest value in
    // the right half
    // eg - if the input is like 1,2,3,4,5,6,7,8,find median,9,10,11,find median
    // before the first median call, the queues would be - max_heap = [4,3,2,1] -
    // i.e - 4 is the max and the min heap would be [5, 6, 7, 8] - i.e min is 5 then
    // we take average of both, similarly when we move forward the max and min heap
    // are updated
    PriorityQueue<Integer> max_heap;
    PriorityQueue<Integer> min_heap;

    public MedianFinder() {

        min_heap = new PriorityQueue<>();
        // max_heap = new PriorityQueue<>(10, Collections.reverseOrder());
        max_heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }

    public void addNum(int num) {
        // steps
        // first add the element to the max_heap
        max_heap.offer(num);

        // remove the max from the max_heap
        int move = max_heap.poll();

        // add it to min_heap
        min_heap.offer(move);

        // Now to balance the heap we check if min heap has more elements than max heap
        // we move min from min heap to max heap
        // Note - our max heap can contain 1 more element than the min heap, that would
        // be for odd length inputs, in that case we return the peek value of max_heap
        // else we return the peek values of (min_heap and max_heap)/2
        if (min_heap.size() > max_heap.size()) {
            int move_min = min_heap.poll();
            max_heap.offer(move_min);
        }

    }

    public double findMedian() {
        int size = max_heap.size() + min_heap.size();
        if (size % 2 != 0)
            return max_heap.peek();
        else
            return (max_heap.peek() + min_heap.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */