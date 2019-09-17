import java.util.*;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*
         * Nice idea, traverse through the array, and negate the indexes of whichever
         * value we face in the end, all the indexes whose values are positive are the
         * result E.g - [4,1,3,1] start with 4, 4 means index would be 3, so mark value
         * at index 3 as negative, i.e 1 becomes -1 [4,1,3,-1] going further, 1 so index
         * would be 0, making it negative [-4,1,3,-1] now 3, index would be 2,
         * [-4,1,-3,-1] finally -1, we are bound to get negative values as and when we
         * get ahead thats y we take the absolute value and then subtract 1 to get the
         * index -1 would be Math.abs(-1) - 1 to get index, i.e 1-1= 0 0 is already
         * negative so no need to do anything
         * 
         * 
         * In the final for loop, we traverse through the array and whosever value is >
         * 0, we return its index+1, in this case index is 1 so 1+1 = 2 is the answer,
         * cross verifying the input array we dont see any 2 value so its correct
         */
        List<Integer> res = new ArrayList<Integer>();

        for (int x : nums) {
            int temp = Math.abs(x) - 1;
            if (nums[temp] > 0)
                nums[temp] = -nums[temp];
        }

        for (int j = 0; j < nums.length; j++)
            if (nums[j] > 0)
                res.add(j + 1);
        return res;
    }
}