public class Unique_Paths_II_63 {
    public int uniquePathsWithObstacles(int[][] grid) {
        int len = grid.length;

        if (len == 0)
            return 0;

        // no way possible, since we have an obstacle at the starting cell only
        if (grid[0][0] == 1)
            return 0;

        int row = len;
        int col = grid[0].length;

        grid[0][0] = 1;

        for (int c = 1; c < col; c++) {
            if (grid[0][c] == 1 || grid[0][c - 1] == 0)
                grid[0][c] = 0;
            else
                grid[0][c] = 1;
        }

        for (int r = 1; r < row; r++) {
            if (grid[r][0] == 1 || grid[r - 1][0] == 0)
                grid[r][0] = 0;
            else
                grid[r][0] = 1;
        }

        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == 1)
                    grid[i][j] = 0;
                else {
                    int from_top = grid[i - 1][j];
                    int from_left = grid[i][j - 1];

                    grid[i][j] = from_top + from_left;
                }
            }

        return grid[row - 1][col - 1];
    }
}
