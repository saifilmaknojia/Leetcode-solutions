import java.util.*;

class Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int len = nums.length;
        Set<Integer> elements = new HashSet<>();
        for (int x : nums)
            elements.add(x);

        int max_len = 1;
        for (int i = 0; i < len; i++) {
            // if the previous number is present then we skip it for now, because it will be
            // covered in future
            // e.g [100, 200, 4, 3, 1, 2] when we reach 4, 4-1 = 3, 3 is present so we skip
            // it because in future when we find 3, it will check for 2, which is present,
            // then comes 1 in iteration but 0 is not present, so we move in the while part,
            // wherein it checks for elements 1,2,3,4 which are indeed present, after that 5
            // is not there so it comes out of the while part, then max_len = Math.max(5-1,
            // max_len), in this case max_len = Math.max(4, max_len) = 4
            if (elements.contains(nums[i] - 1))
                continue;

            int curr = nums[i];

            while (elements.contains(curr))
                curr += 1;

            max_len = Math.max(curr - nums[i], max_len);
        }
        return max_len;

    }

    public int longestConsecutive_N2(int[] nums) {
        // sorting n log n solution
        // if(nums == null || nums.length == 0)
        // return 0;
        Arrays.sort(nums);

        int res = 0;
        int len = nums.length;
        int size = 0;
        int prev = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            if (nums[i] - nums[prev] == size) {
                // System.out.println("diff "+nums[i]);
                size++;
            } else {
                res = Math.max(size, res);
                // System.out.println(nums[i]);
                size = 0;
                prev = i;
                i--;
            }
        }
        return Math.max(res, size);
    }
}