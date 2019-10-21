import java.util.*;

class Subarray_Sum_Equals_K {
    // concept of prefix sum
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int count = 0;
        HashMap<Integer, Integer> presum = new HashMap<>();
        presum.put(0, 1);
        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];

            if (presum.containsKey(runningSum - k))
                count += presum.get(runningSum - k);

            presum.put(runningSum, presum.getOrDefault(runningSum, 0) + 1);
        }
        // System.out.println(presum);
        return count;
    }
}