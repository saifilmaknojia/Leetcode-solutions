package Amazon;

import java.util.*;

// This is similar to meeting rooms 1
// the difference here is since we need to find minimum rooms required
// we create a min heap and add the end timings of rooms
// so then we can check if the minimum end time of our rooms is more than the current meeting time, if yes then we need to add a room
// else we know that the last meeting was over so we dont add a room instead we poll the element and add the current one
class Meeting_Rooms_2 {
    public int minMeetingRooms(int[][] intervals) {
        int rooms = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0])
                    return x[0] - y[0];
                else
                    return x[1] - y[1];
            }
        });

        PriorityQueue<Integer> timeTracker = new PriorityQueue<>();
        for (int[] curr : intervals) {
            if (timeTracker.isEmpty() || timeTracker.peek() > curr[0]) {
                timeTracker.add(curr[1]);
                rooms++;
            } else if (timeTracker.peek() <= curr[0]) {
                timeTracker.poll();
                timeTracker.add(curr[1]);
            }
        }

        return rooms;
    }
}

/*
 * // using 2 pointers public class Solution { public int
 * minMeetingRooms(Interval[] intervals) { int[] starts = new
 * int[intervals.length]; int[] ends = new int[intervals.length]; for(int i=0;
 * i<intervals.length; i++) { starts[i] = intervals[i].start; ends[i] =
 * intervals[i].end; } Arrays.sort(starts); Arrays.sort(ends); int rooms = 0;
 * int endsItr = 0; for(int i=0; i<starts.length; i++) {
 * if(starts[i]<ends[endsItr]) rooms++; else endsItr++; } return rooms; } }
 */
