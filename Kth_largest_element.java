import java.util.Random;

class Kth_largest_element {

	// https://medium.com/algorithm-problems/kth-largest-element-in-an-array-724a0b86d632
	// https://www.youtube.com/watch?v=hGK_5n81drs

	// The approach we follow is of finding a random pivot element and arranging
	// elements around it
	// i.e arrange elements less than pivot to the left side of pivot and elements
	// greater than pivot to the right // side
	// it is better to choose the pivot in a random fashion
	// once the elements are arranged accross the pivot, we then check if the index
	// at which pivot is present, is // it the Kth value we are looking for, if yes,
	// return it, else if the index of pivot is greater then, we need to //
	// search/recurse on the left side of pivot
	// this algo has an expected complexity of O(n)

	public int findKthLargest(int[] nums, int k) {
		if (nums.length == 1)
			return nums[0];

		return randomSelect(nums, 0, nums.length - 1, k);
	}

	int randomSelect(int[] nums, int start, int end, int k) {
		int idx = getRandomIndex(nums, start, end);
		// System.out.println(idx);
		if (idx == (k - 1))
			return nums[idx];
		else if (idx > (k - 1))
			return randomSelect(nums, start, idx - 1, k);
		else
			return randomSelect(nums, idx + 1, end, k);
	}

	int getRandomIndex(int[] arr, int s, int e) {
		int toChange = getRandomPivot(s, e);
		int pivot = arr[toChange];
		swapValue(arr, toChange, e);

		int play = s;

		for (int i = s; i < e; i++) {
			if (arr[i] >= pivot) {
				swapValue(arr, play, i);
				play++;
			}
		}
		swapValue(arr, play, e);
		return play;
	}

	int getRandomPivot(int min, int max) {
		int limit = (max - min) + 1;
		return new Random().nextInt(limit) + min;
	}

	void swapValue(int[] arr, int first, int second) {
		int temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}
}