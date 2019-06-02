import java.util.*;

class Solution {
    // similar to 3 sum, use 2 fixed pointers instead of 1
    // Time complexity is O(N^(K-1)) -> 4Sum O(N^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return res;

        Arrays.sort(nums);
        kSum(nums, 0, 4, target, res);
        return res;
    }

    private void kSum(int[] nums, int start, int k, int target, List<List<Integer>> res) {
        int len = nums.length;
        // 剪枝
        int max = nums[len - 1];
        if (nums[start] * k > target || max * k < target)
            return;

        // divide into 2Sum
        if (k == 2) {
            // two pointers from left and right
            twoSum(nums, start, target, res);

        } else {
            // reduce it to (k-1)Sum until 2Sum
            for (int i = start; i < len - (k - 1); i++) {
                // de-dupe
                if (i > start && nums[i] == nums[i - 1])
                    continue;
                List<List<Integer>> tmp = new ArrayList<>();
                kSum(nums, i + 1, k - 1, target - nums[i], tmp);
                // add the current num
                for (List<Integer> l : tmp) {
                    l.add(0, nums[i]);
                }
                res.addAll(tmp);
            }

        }
    }

    private void twoSum(int[] nums, int start, int target, List<List<Integer>> res) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                // remove duplicate
                while (left < right && nums[left] == nums[left + 1])
                    left++;
                while (left < right && nums[right] == nums[right - 1])
                    right--;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}