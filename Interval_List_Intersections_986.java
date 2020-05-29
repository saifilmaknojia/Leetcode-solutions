import java.util.*;

public class Interval_List_Intersections_986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int a_len = A.length;
        int b_len = B.length;

        if (a_len == 0 || b_len == 0)
            return result.toArray(new int[0][]);

        int left_A = 0;
        int left_B = 0;

        while (left_A < a_len && left_B < b_len) {
            int[] a_curr = A[left_A];
            int[] b_curr = B[left_B];

            if (a_curr[1] >= b_curr[0] && a_curr[0] <= b_curr[1]) {
                int left_side = Math.max(a_curr[0], b_curr[0]);
                int right_side = Math.min(a_curr[1], b_curr[1]);

                result.add(new int[] { left_side, right_side });
            }
            /*
             * for (int[] x : result) { System.out.println(x[0] + " " + x[1]); }
             */

            if (a_curr[1] <= b_curr[1])
                left_A++;
            else
                left_B++;

        }

        return result.toArray(new int[result.size()][]);
    }
}