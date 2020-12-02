
class Word_Search {
    // concept used - DFS, backtracking (just to mark current cell as visited)
    // we could have made use of a boolean[][] visited array to keep track of
    // visited cells for a given recursion call
    // or we could just save the variable in a temp and then replace the cell with '
    // ' for the recursion, so it doesnt get reused in our recursion
    // once we complete the recursion, assign the temp variable to the cell again
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        if (word == null || board == null || board.length == 0)
            return false;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (performDFS(i, j, board, word, 0))
                        return true;
                }
            }

        return false;
    }

    private boolean performDFS(int r, int c, char[][] grid, String orignal, int idx) {
        if (idx == orignal.length())
            return true;
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != orignal.charAt(idx))
            return false;

        char temp = grid[r][c];
        grid[r][c] = ' ';

        boolean result = performDFS(r + 1, c, grid, orignal, idx + 1) || performDFS(r - 1, c, grid, orignal, idx + 1)
                || performDFS(r, c + 1, grid, orignal, idx + 1) || performDFS(r, c - 1, grid, orignal, idx + 1);

        grid[r][c] = temp;
        return result;
    }
}