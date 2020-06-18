import java.util.*;

public class Largest_Divisible_Subset_378 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // its DP bro :)
        // did it meself
        // O(N^2) time and space

        List<Integer> result = new ArrayList<>();

        int max_len_subset = 0;
        int len = nums.length;

        Arrays.sort(nums);

        List<Integer>[] tracker = new ArrayList[len];

        for (int i = 0; i < len; i++)
            tracker[i] = new ArrayList<Integer>();

        for (int i = 0; i < len; i++) {
            int max_len = 0;
            int max_idx = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (tracker[j].size() > max_len) {
                        max_idx = j;
                        max_len = tracker[j].size();
                    }
                }
            }
            if (max_idx != -1)
                tracker[i] = new ArrayList<>(tracker[max_idx]);

            tracker[i].add(nums[i]);

            // System.out.println(tracker[i]);

            if (tracker[i].size() > max_len_subset) {
                result = tracker[i];
                max_len_subset = tracker[i].size();
            }
        }
        return result;
    }
}