public class Burst_Balloons_312 {
    // from tushar's video - https://www.youtube.com/watch?v=IFNibRVgFBo
    public int maxCoins(int[] nums) {
        int size = nums.length;
        if (size == 0)
            return 0;

        int[][] dp = new int[size][size];

        // O(n^3)

        // first for loop is for considering length, we start by considering length of 1
        // till length of size of array
        for (int len = 1; len <= size; len++)
            // the second for loop is for the left_most index in current array in
            // consideration
            for (int left = 0; left <= size - len; left++) {
                // right is the rightmost index in our current considering array, i.e if len = 3
                // and left = 0, then right = 2, i.e - left + len - 1, i.e - 0 + 3 - 1, 2
                int right = left + len - 1;

                // curr_burst is the index of balloon which we consider to burst last
                int max = 0;
                for (int curr_burst = left; curr_burst <= right; curr_burst++) {
                    int max_left = 0;
                    if (curr_burst != left)
                        max_left = dp[left][curr_burst - 1];

                    int max_right = 0;
                    if (curr_burst != right)
                        max_right = dp[curr_burst + 1][right];

                    int to_multiply_left = left > 0 ? nums[left - 1] : 1;
                    int to_multiply_right = right < size - 1 ? nums[right + 1] : 1;

                    int multiplication = to_multiply_left * nums[curr_burst] * to_multiply_right;

                    max = Math.max(max, multiplication + max_left + max_right);
                }
                dp[left][right] = max;
            }

        return dp[0][size - 1];
    }
}
