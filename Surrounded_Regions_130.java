import java.util.*;

public class Surrounded_Regions_130 {
    public void solve(char[][] board) {
        // we need to consider all the cells with O's on the border and then the idea is
        // to mark all neighbors of these cells who also have O's with E
        // these cells will not be able to be surronded

        if (board.length < 1 || board[0].length < 1)
            return;

        int row_len = board.length;
        int col_len = board[0].length;

        Queue<int[]> tracker = new LinkedList<>();

        // adding border elements that have O
        // adding first column and last column border elements
        for (int r = 0; r < row_len; r++) {
            if (board[r][0] == 'O') {
                tracker.add(new int[] { r, 0 });
                board[r][0] = 'E';
            }

            if (board[r][col_len - 1] == 'O') {
                tracker.add(new int[] { r, col_len - 1 });
                board[r][col_len - 1] = 'E';
            }
        }

        // adding border elements that have O
        // adding first row and last row elements
        // we start from 1 and end at col_len - 1 because the elements on the 4 corners
        // are already added in the above for loop
        for (int c = 1; c < col_len - 1; c++) {
            if (board[0][c] == 'O') {
                tracker.add(new int[] { 0, c });
                board[0][c] = 'E';
            }

            if (board[row_len - 1][c] == 'O') {
                tracker.add(new int[] { row_len - 1, c });
                board[row_len - 1][c] = 'E';
            }
        }

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!tracker.isEmpty()) {
            int[] curr_cell = tracker.poll();
            int curr_row = curr_cell[0];
            int curr_col = curr_cell[1];

            // System.out.println(curr_row + " " + curr_col + " --> " +
            // board[curr_row][curr_col]);

            // checking the 4 directions
            for (int[] dir : dirs) {
                int new_row = curr_row + dir[0];
                int new_col = curr_col + dir[1];

                // System.out.println("New " + new_row + " " + new_col);
                // new cell is out of bound so we continue
                if (new_row < 0 || new_row >= row_len || new_col < 0 || new_col >= col_len)
                    continue;
                else {
                    if (board[new_row][new_col] == 'O') {

                        board[new_row][new_col] = 'E';
                        // System.out.println("added --> " + board[new_row][new_col]);
                        tracker.add(new int[] { new_row, new_col });
                    }
                }
            }
        }

        // mark all the cells which are 'E' with 'O' that means they are not surronded
        // by X
        // and all those which are marked as 'O' are surronded by 'X'

        for (int r = 0; r < row_len; r++) {
            for (int c = 0; c < col_len; c++) {
                if (board[r][c] == 'X')
                    continue;
                else if (board[r][c] == 'E')
                    board[r][c] = 'O';
                else if (board[r][c] == 'O')
                    board[r][c] = 'X';
            }
        }

    }
}