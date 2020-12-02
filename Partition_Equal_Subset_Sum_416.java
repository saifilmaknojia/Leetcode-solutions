
public class Partition_Equal_Subset_Sum_416 {
    public boolean canPartition(int[] nums) {
        // Its a 0-1 Knapsack problem, done with DP
        // https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
        // https://www.youtube.com/watch?v=8LusJS5-AGo

        // using O(sum/2) space, previously the answer submitted used 2d array which is
        // not required since we only need the previous row so in this case a 1d array
        // will suffice along with a temp 1d array
        if (nums == null || nums.length == 0)
            return false;

        int sum = 0;

        for (int x : nums)
            sum += x;

        if (sum % 2 == 1)
            return false;

        int required = sum / 2;
        int len = nums.length;

        int[] knapsack_dp = new int[required + 1];

        for (int curr = 1; curr <= len; curr++) {
            int[] temp_dp = new int[required + 1];
            for (int weight = 1; weight <= required; weight++) {
                if (weight >= nums[curr - 1]) {
                    int prev_row_jump = weight - nums[curr - 1];
                    temp_dp[weight] = Math.max(nums[curr - 1] + knapsack_dp[prev_row_jump], knapsack_dp[weight]);
                } else
                    temp_dp[weight] = knapsack_dp[weight];

                if (temp_dp[weight] == required)
                    return true;
            }
            knapsack_dp = temp_dp;
        }
        return false;
    }
}