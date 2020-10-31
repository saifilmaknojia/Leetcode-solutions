import java.util.*;

public class Non_Overlapping_Intervals_435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len <= 1)
            return 0;

        // sort in ascending order of end, if both are same than sort in ascending order
        // of x
        Arrays.sort(intervals, (x, y) -> {
            return x[1] - y[1];
        });

        int[] prev = intervals[0];
        int removed = 0;

        // condition, if our current interval's has start time greater than previous's
        // end time, it means both intervals are valid, hence we change our prev to curr
        // else we know that both overlap so we increment our removed parameter, and
        // dont change the prev because we have sorted in ascending order of end time,
        // so it is better to keep using the one with the smaller end time, which is
        // already the prev
        for (int i = 1; i < len; i++) {
            int[] curr = intervals[i];
            if (curr[0] >= prev[1])
                prev = curr;
            else
                removed++;
        }
        return removed;
    }
}