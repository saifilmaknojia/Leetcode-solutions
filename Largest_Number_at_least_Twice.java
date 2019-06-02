class Solution {
	public int dominantIndex(int[] nums) {
		int result = -1;
		/*
		 * int[] track=nums.clone(); Arrays.sort(nums);
		 * 
		 * if(nums[nums.length-2]*2<=nums[nums.length-1]) { int find =
		 * nums[nums.length-1]; System.out.println("Find ="+find);
		 * 
		 * for(int i=0;i<track.length;i++) { System.out.println("track[i] = "+track[i]);
		 * if(track[i]==find) { result=i; break; } } }
		 * 
		 * return result;
		 */

		if (nums.length == 1)
			return 0;

		int largest = 0;
		int secondLargest = 0;
		// int max=0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[largest])
				largest = i;
			// if(nums[i]>nums[secondLargest] && nums[i]<nums[largest])
			// secondLargest=i;
		}
		if (largest == 0) {
			secondLargest = 1;
		}

		for (int i = secondLargest + 1; i < nums.length; i++) {
			if (nums[i] > nums[secondLargest] && nums[i] < nums[largest])
				secondLargest = i;
		}

		System.out.println("Largest = " + nums[largest]);
		System.out.println("Second largest = " + nums[secondLargest]);
		if (nums[secondLargest] * 2 <= nums[largest])
			result = largest;

		return result;
	}
}