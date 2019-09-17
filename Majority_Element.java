class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int currMajor = nums[0];
        int count = 0;

        for (int x : nums) {
            if (count == 0)
                currMajor = x;
            count = x == currMajor ? count + 1 : count - 1;
        }
        return currMajor;
    }
}