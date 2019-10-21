// import java.util.*;

class Pivot_Index {
    public int pivotIndex(int[] nums) {
        // The idea I have used for this problem is to have a left sum value, which will
        // be the left side of the index and
        // right_sum value which will be the right side index
        // when we traverse the loop then we keep on decrementing left_sum from the
        // total_sum to get the right sum
        // we also update left_sum values as we move forward
        int result = -1;
        if (nums.length == 0)
            return result;
        int left_sum = nums[0];
        int right_sum = 0;
        int total_sum = 0;

        for (int i = 0; i < nums.length; i++) {
            total_sum = total_sum + nums[i];
        }

        if (total_sum - left_sum == 0)
            return 0;

        for (int i = 1; i < nums.length; i++) {
            right_sum = total_sum - left_sum - nums[i];
            if (left_sum == right_sum) {
                result = i;
                break;
            }
            left_sum = left_sum + nums[i];
        }
        return result;
    }
}