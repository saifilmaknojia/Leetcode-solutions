import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums.length < 4)
            return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2;) {
                    int l = j + 1;
                    int r = nums.length - 1;

                    while (l < r) {
                        int curr = nums[i] + nums[j] + nums[l] + nums[r];
                        // System.out.println(curr);
                        if (curr == target) {
                            // System.out.println(nums[i] + " " +nums[j]);
                            List<Integer> x = new ArrayList<>();
                            x.add(nums[i]);
                            x.add(nums[j]);
                            x.add(nums[l]);
                            x.add(nums[r]);
                            ans.add(x);
                            l++;
                            r--;

                            while (l < nums.length && nums[l] == nums[l - 1])
                                l++;
                            while (r >= 0 && nums[r] == nums[r + 1])
                                r--;

                        } else if (curr > target)
                            r--;
                        else
                            l++;
                    }
                    j++;
                    while (j < nums.length - 2 && nums[j] == nums[j - 1])
                        j++;
                }
            }
        }
        return ans;
    }
}