
class Number_of_Islands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int islands = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    performDFS(i, j, grid);
                }
            }
        return islands;
    }

    private void performDFS(int r, int c, char[][] arr) {

        if ((r >= 0 && r < arr.length) && (c >= 0 && c < arr[0].length) && arr[r][c] == '1') {
            arr[r][c] = '0';
            performDFS(r + 1, c, arr);
            performDFS(r - 1, c, arr);
            performDFS(r, c + 1, arr);
            performDFS(r, c - 1, arr);
        }
    }
}