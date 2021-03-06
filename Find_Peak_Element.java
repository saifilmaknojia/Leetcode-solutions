class Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        else if (nums.length == 2)
            return nums[0] > nums[1] ? 0 : 1;
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(int[] arr, int s, int e) {
        int mid = ((e - s) / 2) + s;

        if ((mid == 0 || arr[mid] > arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] > arr[mid + 1]))
            return mid;
        else if (mid != 0 && arr[mid] < arr[mid - 1])
            mid = binarySearch(arr, s, mid - 1);
        else
            mid = binarySearch(arr, mid + 1, e);

        return mid;
    }
}