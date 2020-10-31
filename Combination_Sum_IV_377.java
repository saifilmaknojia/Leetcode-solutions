import java.util.*;

public class Combination_Sum_IV_377 {
    public int combinationSum4(int[] nums, int target) {
        // I think it is same as 518. Coin Change 2 problem
        int len = nums.length;
        if (len == 0)
            return 0;

        Arrays.sort(nums);

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int make = 1; make <= target; make++)
            for (int using : nums) {
                // if the current number i.e using is greater than what we want to make
                // currently, then there is no way we can achieve this hence we break
                // e.g - if make is 5 and currently our using is 7, we know all numbers after 7
                // will be greater since we have sorted the nums and we wont be able to make 5
                // from any of it, therefore we break
                if (using > make)
                    break;

                // now, the core logic is making the current_sum i.e - make by making use of
                // current_num i.e using, therefore the logic is we can make current_Sum i.e
                // make by number of ways we can make current_Sum - current_num
                // for e.g we want to make 5, i.e current_Sum is 5, and current_num is 3, i.e
                // using is 3, therefore number of ways we can make 5 is number of ways we can
                // make 5-3, i.e - 2
                // we do this for all the numbers in the array
                dp[make] += dp[make - using];
            }

        return dp[target];
    }

    public static void main(String[] args) {
        Combination_Sum_IV_377 obj = new Combination_Sum_IV_377();
        System.out.println("Test case: nums = [1, 2, 3], target = 5, Expected result = 13, Got result = "
                + obj.combinationSum4(new int[] { 1, 2, 3 }, 5));
        System.out.println("Test case: nums = [1, 2, 3, 6], target = 11, Expected result = 560, Got result = "
                + obj.combinationSum4(new int[] { 1, 2, 3, 6 }, 11));
    }
}