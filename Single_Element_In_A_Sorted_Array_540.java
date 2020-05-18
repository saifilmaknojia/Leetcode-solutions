public class Single_Element_In_A_Sorted_Array_540 {
    // O(log n) - binary search
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 0)
            return -1;

        int len = nums.length - 1;

        int start = 0;
        int end = len;

        // we will end when there is a single element or the answer is returned from the
        // while loop
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if ((mid == 0 || nums[mid] != nums[mid - 1]) && (mid == len || nums[mid] != nums[mid + 1]))
                return nums[mid];
            else if (nums[mid] == nums[mid - 1]) {
                int onLeft = (mid - 2 - start) + 1;
                if (onLeft % 2 == 1)
                    end = mid - 2;
                else
                    start = mid + 1;
            } else if (nums[mid] == nums[mid + 1]) {
                int onRight = (end - mid + 2) + 1;
                if (onRight % 2 == 1)
                    start = mid + 2;
                else
                    end = mid - 1;
            }
        }
        return nums[start];
    }

    // O(N) - linear search
    public int singleNonDuplicate_linear(int[] nums) {
        if (nums.length == 0)
            return -1;

        int len = nums.length - 1;
        for (int i = 0; i < len; i += 2)
            if (nums[i] != nums[i + 1])
                return nums[i];

        return nums[len];
    }
}