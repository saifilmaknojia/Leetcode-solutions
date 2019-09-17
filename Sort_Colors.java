class Solution {
    public void sortColors(int[] A) {
        // DUTCH NATIONAL FLAG PROBLEM - https://www.youtube.com/watch?v=BOt1DAvR0zI
        // http://buttercola.blogspot.com/2014/09/leetcode-sort-colors.html
        // https://www.youtube.com/watch?v=iNNszqTonu4
        /*
         * Since the array is only composed of 0, 1, and 2, we can naturally think of
         * using two pointers approach. We use two pointers, red and blue, points to the
         * starting and end of the array initially. Then iterate index i from 0 to the
         * end of the array. If A[i] == 0, move it to red pointer. If A[i] == 2, move it
         * to blue pointer. Else move on.
         */
        if (A == null || A.length <= 1)
            return;

        int red = 0;
        int blue = A.length - 1;

        int i = 0;
        while (i <= blue) {
            if (A[i] == 0) {
                swap(A, i, red);
                red++;
                i++;
            } else if (A[i] == 2) {
                swap(A, i, blue);
                blue--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}