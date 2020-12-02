
import java.util.*;

class Path_With_Maximum_Minimum_Value {

    public int maximumMinimumPath(int[][] A) {
        if (A == null)
            return 0;

        int m = A.length - 1;
        int n = A[0].length - 1;
        if (m == 1 && n == 1)
            return A[0][0];

        // taking min of start and destination nodes
        int max_min = Math.min(A[0][0], A[m][n]);
        // max heap
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] first, int[] second) {
                return second[2] - first[2];
            }
        });

        int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        heap.add(new int[] { 0, 0, max_min });

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            max_min = curr[2];
            int row = curr[0];
            int col = curr[1];

            // if we reach our destination node, return the val
            if (row == m && col == n)
                return max_min;

            // mark the current working node as visited by changing its value to -1
            A[row][col] = -1;

            for (int[] direction : dirs) {
                int new_row = row + direction[0];
                int new_col = col + direction[1];

                if (new_row < 0 || new_row > m || new_col < 0 || new_col > n)
                    continue;

                if (A[new_row][new_col] != -1)
                    heap.add(new int[] { new_row, new_col, Math.min(max_min, A[new_row][new_col]) });
            }
        }
        return -1;
    }
}