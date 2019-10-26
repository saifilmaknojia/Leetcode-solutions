import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

class Merge_Intervals {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new IntervalCompare());
        LinkedList<int[]> tracker = new LinkedList<>();
        // tracker.add(intervals[0]);
        for (int[] current : intervals) {
            if (tracker.isEmpty() || current[0] > tracker.getLast()[1])
                tracker.add(current);
            else {
                int[] Last = tracker.getLast();
                if (Last[1] < current[1])
                    tracker.getLast()[1] = current[1];
                // Last[1] = current[1];
                // tracker.add(updateLast); */
                // if(curre)

            }
        }
        return tracker.toArray(new int[tracker.size()][2]);
    }

}

class IntervalCompare implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        // if a and b are not same than we by start time
        if (a[0] != b[0])
            return a[0] - b[0];
        else
            return a[1] - b[1];
        // else, if a and b are same then we sort ascending according to end time

        // e.g - [[8,10],[1,3],[15,18],[1,1]] will give [[1,3],[1,1],[8,10],[15,18]], if
        // we only keep return a[0] - b[0];

        // e.g - [[8,10],[1,3],[15,18],[1,1]] will give [[1,1],[1,3],[8,10],[15,18]] if
        // we have if else in place, thats what we want
    }
}