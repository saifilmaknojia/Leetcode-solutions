public class Number_Of_Dice_Rolls_With_Target_Sum_1155 {
    int[][] dp;
    int k;
    int MOD = 1000000000 + 7;

    public int numRollsToTarget(int n, int k, int target) {
        // if the target is greater than the max possible sum on all the dices combined,
        // we return 0
        if (target > n * k)
            return 0;
        dp = new int[target + 1][n + 1];
        this.k = k;

        for (int i = 0; i <= target; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;

        return topDownDP(target, n);
    }

    private int topDownDP(int tgt, int n) {
        if (tgt == 0 && n == 0)
            return 1;

        if (tgt < 0 || n < 0)
            return 0;

        if (dp[tgt][n] != -1) {
            // System.out.println("Found in cache = "+tgt +", n = "+n +", value -
            // "+dp[tgt][n]);
            return dp[tgt][n];
        }

        int sum = 0;
        for (int i = 1; i <= k; i++) {
            if (tgt < i) {
                break;
            }
            // be careful while doing MOD
            // if we do MOD of the recursion result, it gives a wrong answer (sum = sum +
            // ((topDownDP(tgt - i, n - 1)) % MOD);)
            // We need to first add the recursion result to the sum and then MOD
            sum += topDownDP(tgt - i, n - 1);
            sum = sum % MOD;
        }
        dp[tgt][n] = sum;
        return dp[tgt][n];
    }
}