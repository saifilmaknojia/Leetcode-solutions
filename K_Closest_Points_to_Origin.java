package Amazon;

import java.util.*;

class K_Closest_Points_to_Origin {
    // use distance formula
    // create custom class
    // add elements of custom class to Priority Queue which is sorted in ascending
    // order since we want the closest points to origin
    // add required number of elements to result and then return
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length <= K)
            return points;

        if (K == 0)
            return null;

        Queue<PQ> sorted = new PriorityQueue<>();
        for (int[] curr : points) {
            int x = curr[0];
            int y = curr[1];

            // distance formula = sqrt((x2−x1)^2+(y2−y1)^2)
            int distance = (int) (Math.pow(Math.abs(x - 0), 2) + Math.pow(Math.abs(y - 0), 2));
            sorted.offer(new PQ(curr, distance));
        }

        int[][] result = new int[K][2];
        int ct = 0;
        while (K > 0) {
            result[ct] = sorted.poll().arr;
            ct++;
            K--;
        }
        return result;
    }

    class PQ implements Comparable<PQ> {
        int[] arr;
        int distance;

        public PQ(int[] temp, int d) {
            arr = temp;
            distance = d;
        }

        @Override
        public int compareTo(PQ obj) {
            // sorting in ascending order since we want nearest distance
            return this.distance - obj.distance;
        }
    }
}