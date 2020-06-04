import java.util.LinkedList;
import java.util.Queue;

public class Zero1_Matrix_542 {
    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int row;
    int col;

    public int[][] updateMatrix(int[][] matrix) {
        // bfs trying
        row = matrix.length;
        col = matrix[0].length;

        Queue<Pair_01> tracker = new LinkedList<>();
        int one_count = 0;

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0)
                    tracker.offer(new Pair_01(i, j));
                else
                    one_count++;
            }

        int[][] result = new int[row][col];

        while (!tracker.isEmpty()) {
            int size = tracker.size();
            for (int i = 0; i < size; i++) {
                Pair_01 curr = tracker.poll();

                int curr_row = curr.x;
                int curr_col = curr.y;

                for (int[] dir : dirs) {
                    int new_row = curr_row + dir[0];
                    int new_col = curr_col + dir[1];

                    if (isSafe(new_row, new_col) && matrix[new_row][new_col] == 1) {
                        result[new_row][new_col] = result[curr_row][curr_col] + 1;
                        tracker.offer(new Pair_01(new_row, new_col));
                        matrix[new_row][new_col] = 0;
                        one_count--;
                    }
                }

                if (one_count == 0)
                    return result;
            }
        }
        return result;
    }

    private boolean isSafe(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}

class Pair_01 {
    int x;
    int y;

    public Pair_01(int f, int s) {
        x = f;
        y = s;
    }
}