import java.util.*;

class Meeting_Rooms_1 {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0])
                    return x[0] - y[0];
                else
                    return x[1] - y[1];
            }
        });

        int[] prev = null;
        for (int[] arr : intervals) {
            if (prev != null && arr[0] < prev[1])
                return false;
            else
                prev = arr;
        }

        return true;
    }
}