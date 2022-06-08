public class Predict_The_Winner_486 {
    class Solution {
        public boolean PredictTheWinner(int[] nums) {
            if (nums.length < 3)
                return true;

            int sum_p1 = 0;
            int sum_p2 = 0;

            int l = 0;
            int r = nums.length - 1;

            boolean isPlayerOneTurn = true;

            while (r - l > 2) {
                int taking_left = Math.max(nums[l + 1], Math.max(nums[l + 2], Math.max(nums[r], nums[r - 1])));
                int taking_right = Math.max(nums[r - 1], Math.max(nums[r - 2], Math.max(nums[l], nums[l + 1])));

                if (nums[l] + taking_left > nums[r] + taking_right) {
                    if (isPlayerOneTurn) {
                        sum_p1 += nums[l];
                        isPlayerOneTurn = false;
                    } else {
                        sum_p2 += nums[l];
                        isPlayerOneTurn = true;
                    }
                    l++;
                } else {
                    if (isPlayerOneTurn) {
                        sum_p1 += nums[r];
                        isPlayerOneTurn = false;
                    } else {
                        sum_p2 += nums[r];
                        isPlayerOneTurn = true;
                    }
                    r--;
                }
            }

            while (l <= r) {
                if (nums[l] > nums[r]) {
                    if (isPlayerOneTurn) {
                        sum_p1 += nums[l];
                        isPlayerOneTurn = false;
                    } else {
                        sum_p2 += nums[l];
                        isPlayerOneTurn = true;
                    }
                    l++;
                } else {
                    if (isPlayerOneTurn) {
                        sum_p1 += nums[r];
                        isPlayerOneTurn = false;
                    } else {
                        sum_p2 += nums[r];
                        isPlayerOneTurn = true;
                    }
                    r--;
                }
            }

            return sum_p1 >= sum_p2 ? true : false;
        }
    }

}
