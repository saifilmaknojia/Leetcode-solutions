class Maximum_Sum_Circular_Subarray_918 {
    // https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/633401/Kadane-Algorithm-Trick-beats-100-Java-Explained
    // https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
    public int maxSubarraySumCircular(int[] A) {
        int len = A.length;
        if (len == 1)
            return A[0];

        int min_curr = 0;
        int max_curr = 0;
        int min_overall = Integer.MAX_VALUE;
        int max_overall = Integer.MIN_VALUE;
        int total_sum = 0;

        for (int x : A) {
            total_sum += x;

            min_curr = Math.min(x, x + min_curr);
            max_curr = Math.max(x, x + max_curr);

            min_overall = Math.min(min_overall, min_curr);
            max_overall = Math.max(max_overall, max_curr);
        }

        // means all numbers were negative, then we return max_overall, because our
        // max_overall will have the smallest negative number
        // e.g - [-3,-1,-5] = total_sum = -9, min_overall = -9, and max_overall = -1, so
        // we return -1, because we dont want to return 0
        if (min_overall == total_sum)
            return max_overall;

        // max_overall = max sum of non circular array
        // total_sum - min_overall = max sum of non circular array
        return Math.max(max_overall, total_sum - min_overall);
    }
}