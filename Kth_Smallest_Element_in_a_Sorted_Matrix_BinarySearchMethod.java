// Concept - https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/301357/Java-0ms-(added-Python-and-C++):-Easy-to-understand-solutions-using-Heap-and-Binary-Search
// Implementation - Discuss board top thread

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        int col = matrix[0].length - 1;
        int low = matrix[0][0];
        int high = matrix[len - 1][len - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            // Reducing rows and adding number of rows
            int count = 0;
            int row = matrix.length - 1;
            for (int i = 0; i <= col; i++) {
                // once we come out this while loop, we now row+1, number of values are <= mid
                while (row >= 0 && matrix[row][i] > mid)
                    row--;

                count += (row + 1);
            }
            // System.out.println(count);
            if (count < k)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}