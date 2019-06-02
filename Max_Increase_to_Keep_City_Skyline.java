/**
 * @author Shaifil
 *
 */
class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] test = new int[][] { { 3, 0, 8, 4 }, { 2, 4, 5, 7 }, { 9, 2, 6, 3 }, { 0, 3, 1, 0 } };
        int x = s.maxIncreaseKeepingSkyline(test);
        System.out.println("Result = " + x);
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int res = 0;
        // System.out.println(grid.length);
        // System.out.println(grid[0].length);
        int sum = 0;
        int[] row = new int[grid.length];
        int[] col = new int[grid[0].length];

        // row wise
        int curr_row = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
                if (grid[i][j] > curr_row)
                    curr_row = grid[i][j];
            }
            row[i] = curr_row;
            curr_row = Integer.MIN_VALUE;
        }

        // col wise
        int curr_col = Integer.MIN_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > curr_col)
                    curr_col = grid[j][i];
            }
            col[i] = curr_col;
            curr_col = Integer.MIN_VALUE;
        }
        for (int x : row) {

            for (int i = 0; i < col.length; i++) {
                // System.out.println("Res "+res);
                res += Math.min(x, col[i]);
            }
        }
        return res - sum;
    }
}
