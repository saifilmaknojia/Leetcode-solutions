import java.util.*;

/*
Idea to reduce operations is to perform pruning, for eg, if we know that the sum of current number at ith index and the last 3 numbers, i.e -
largest sum of 3 numbers, is less than targetsum, then we can continue and increase i since in no way we can achieve the target sum, with current value at ith index(check line 22), similarly, if sum of ith index + (i+1)st+(i+2)nd+(i+3)rd values is greater than target sum then we break (check line 20), coz no way we could then achieve 4 sum for given target because all the sum values would be greater only.
*/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> solutions = new ArrayList<>();
        if (nums.length < 4)
            return solutions;
        Arrays.sort(nums);
        int largestTwoSum = nums[nums.length - 1] + nums[nums.length - 2];
        int largestThreeSum = largestTwoSum + nums[nums.length - 3];
        int targetSum;
        for (int i = 0; i < nums.length - 3; i++) {
            int num1 = nums[i];
            if (i > 0 && num1 == nums[i - 1])
                continue;
            targetSum = target - num1;
            if (nums[i + 1] + nums[i + 2] + nums[i + 3] > targetSum)
                break;
            if (largestThreeSum < targetSum)
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                int num2 = nums[j];
                if (j > i + 1 && num2 == nums[j - 1])
                    continue;
                targetSum = target - num1 - num2;
                if (nums[j + 1] + nums[j + 2] > targetSum)
                    break;
                if (largestTwoSum < targetSum)
                    continue;
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int num3 = nums[start];
                    int num4 = nums[end];
                    int currentSum = num3 + num4;
                    if (currentSum < targetSum)
                        start++;
                    else if (currentSum > targetSum)
                        end--;
                    else {
                        solutions.add(Arrays.asList(num1, num2, num3, num4));
                        while (start < end && nums[start] == nums[start + 1])
                            start++;
                        while (start < end && nums[end] == nums[end - 1])
                            end--;
                        start++;
                        end--;
                    }
                }
            }
        }
        return solutions;
    }
}