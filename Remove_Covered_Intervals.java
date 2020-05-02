import java.util.*;

class Remove_Covered_Intervals {

    // O (N log N) sorting solution
    public int removeCoveredIntervals(int[][] intervals) {
        int len = intervals.length;
        // using sorting

        // the main part of this problem is in the way that we sort, so we would like
        // our interval to be a large as possible
        // in order to do that, we keep the largest right_side for a particular
        // left_Side in the front
        // for e.g - if intervals = [[1,3], [1,2], [1,6], [1,4], [1,9]]
        // we sort it such that [1, 9] comes in the first position, then [1, 6] then [1,
        // 4]
        // final intervals after sorting = [[1, 9], [1, 6], [1, 4], [1, 3], [1, 2]]
        // this helps because, as you see, [1, 9] covers all the other intervals, and we
        // can make use of that, we can walk through the sorted array and check the
        // right_side (i.e 1st element in single cell, i.e intervals[i][1]), for all the
        // elements smaller than our current right, those all will be covered by our
        // curr

        // in this e.g [1, 9] covers all of them

        // once we find right greater than 9, we update our new_right, this will be for
        // numbers other than 1, like - [2, 13], [2, 11] and so on, once we reach [2,
        // 13]
        // we increase the count of our result, since [2, 13] is not contained in [1, 9]
        Arrays.sort(intervals, (x, y) -> {
            if (x[0] == y[0])
                return y[1] - x[1];
            else
                return x[0] - y[0];
        });

        int result = 0;
        int prev_right = intervals[0][1];

        for (int i = 1; i < len; i++) {
            int curr_right = intervals[i][1];

            if (curr_right > prev_right) {
                result++;
                prev_right = curr_right;
            }
        }

        return result;

    }

    public int removeCoveredIntervals_N2(int[][] intervals) {
        int len = intervals.length;
        boolean[] removed = new boolean[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j && !removed[i] && !removed[j]) {
                    if (intervals[i][0] <= intervals[j][0] && intervals[i][1] >= intervals[j][1])
                        removed[j] = true;
                    else if (intervals[j][0] <= intervals[i][0] && intervals[j][1] >= intervals[i][1]) {
                        removed[i] = true;
                        break;
                    }
                }
            }
        }

        int res = 0;
        for (boolean b : removed)
            if (!b)
                res++;

        return res;
    }
}