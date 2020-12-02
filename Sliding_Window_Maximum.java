
import java.util.*;

class Sliding_Window_Maximum {

    // O(N) solution using deque
    // reference - https://www.youtube.com/watch?v=fbkvdWUS5Ic
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        if (nums == null || nums.length == 0 || k > nums.length)
            return new int[0];

        if (k == 1)
            return nums;

        // to store indexes, front indexes will always store index for the highest
        // element in the current window
        Deque<Integer> dq = new ArrayDeque<>();

        int move = 0;
        int idx = 0;
        while (move < n) {
            // 3 steps are involved
            // Mind it, indexes in dq are stored with elemnents in those going in descending
            // order
            // i.e element at front of dq is highest, than element second in dq and so on

            // 1. Before moving onto step 2, we first check if our highest element is going
            // out of the window, if yes, we remove it. i.e - since first index stores the
            // highest element we remove it
            if (!dq.isEmpty() && dq.peek() == move - k)
                dq.pollFirst();

            // 2. here is the core of the algo, we search from the back of the dq and remove
            // those indexes whose elements are smaller than the current element in
            // consideration
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[move])
                dq.pollLast();

            // 2.1 once we are done with removing those indexes, we add our current index at
            // the end of the dq
            dq.addLast(move);

            // 3. this step helps us to add elements in our result set. the front of the
            // queue will have index of highest element
            if (move - k + 1 >= 0)
                result[idx++] = nums[dq.peekFirst()];

            move++;
        }

        return result;
    }

    // O(N log k) solution using max heap
    public int[] maxSlidingWindow_MaxHeap(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        if (nums == null || nums.length == 0)
            return new int[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        int i = 0;
        for (; i < k; i++)
            pq.add(nums[i]);

        int ct = 0;
        result[ct++] = pq.peek();
        int l = 0;
        while (i < n) {
            pq.remove(nums[l++]);
            pq.add(nums[i]);
            result[ct++] = pq.peek();
            i++;
        }

        return result;
    }

}