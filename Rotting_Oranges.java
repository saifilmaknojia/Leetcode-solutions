
import java.util.*;

class Solution {
    int result;
    int fresh;

    public int orangesRotting(int[][] grid) {
        if (grid == null)
            return result;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
                if (grid[i][j] == 2)
                    q.add(new Pair(i, j));
            }

        while (!q.isEmpty()) {
            int curr_size = q.size();
            result++;
            for (int m = 0; m < curr_size; m++) {
                Pair work = q.poll();
                performBFS(grid, work.row, work.col, q);
            }
        }
        /*
         * for (int i = 0; i < grid.length; i++) for (int j = 0; j < grid[0].length;
         * j++) { if (grid[i][j] == 2 && fresh != 0) { grid[i][j] = -2; performBFS(grid,
         * i, j); } }
         */
        return fresh == 0 ? result : -1;
    }

    private void performBFS(int[][] arr, int r, int c, Queue<Pair> que) {
        if (r > 0) {
            int temp = r - 1;
            if (arr[temp][c] == 1) {
                arr[temp][c] = 2;
                que.add(new Pair(temp, c));
                fresh--;
            }
        }
        if (r < arr.length - 1) {
            int temp = r + 1;
            if (arr[temp][c] == 1) {
                arr[temp][c] = 2;
                que.add(new Pair(temp, c));
                fresh--;
            }
        }
        if (c > 0) {
            int temp = c - 1;
            if (arr[r][temp] == 1) {
                arr[r][temp] = 2;
                que.add(new Pair(r, temp));
                fresh--;
            }
        }

        if (c < arr[0].length - 1) {
            int temp = c + 1;
            if (arr[r][temp] == 1) {
                arr[r][temp] = 2;
                que.add(new Pair(r, temp));
                fresh--;
            }
        }

    }

    public int orangesRotting_ChangeValues(int[][] grid) {
        boolean change = true;
        int time = -1;
        int height = grid.length;
        int width = grid[0].length;
        boolean freshIsPresent = true;
        while (change) {
            change = false;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == 2) {

                        grid[i][j] = 4;

                        // top
                        if (i > 0 && grid[i - 1][j] == 1) {
                            grid[i - 1][j] = 3;
                            change = true;
                        } // bottom
                        if (i < height - 1 && grid[i + 1][j] == 1) {
                            grid[i + 1][j] = 3;
                            change = true;
                        } // left
                        if (j > 0 && grid[i][j - 1] == 1) {
                            grid[i][j - 1] = 3;
                            change = true;
                        } // right
                        if (j < width - 1 && grid[i][j + 1] == 1) {
                            grid[i][j + 1] = 3;
                            change = true;
                        }

                    }
                }
            }

            freshIsPresent = false;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == 3) {
                        grid[i][j] = 2;
                    }
                    if (grid[i][j] == 1) {
                        freshIsPresent = true;
                    }
                }
            }
            time++;
        }
        if (freshIsPresent) {
            return -1;
        }
        return time;
    }
}

class Pair {
    int row;
    int col;

    public Pair(int r, int c) {

    }
}