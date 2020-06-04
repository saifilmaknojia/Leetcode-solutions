import java.util.Arrays;
import java.util.PriorityQueue;

public class Two_City_Scheduling_1029 {
    public int twoCitySchedCost(int[][] costs) {
        int len = costs.length;

        // sort in descending order, i.e the cities with max differences will be
        // considered first
        PriorityQueue<Custom> difference = new PriorityQueue<>((x, y) -> y.val - x.val);

        for (int i = 0; i < len; i++) {
            int diff = Math.abs(costs[i][0] - costs[i][1]);
            difference.offer(new Custom(i, diff));
        }

        int i = 0;
        int sum = 0;

        int city_1_consumed = len / 2;
        int city_2_consumed = len / 2;

        while (i < len) {
            Custom current = difference.poll();
            int index = current.idx;

            if (city_1_consumed == 0) {
                sum += costs[index][1];
                city_2_consumed--;
            } else if (city_2_consumed == 0) {
                sum += costs[index][0];
                city_1_consumed--;
            } else if (costs[index][0] > costs[index][1]) {
                city_2_consumed--;
                sum += costs[index][1];
            } else {
                city_1_consumed--;
                sum += costs[index][0];
            }
            i++;
        }
        return sum;
    }

    public int twoCitySchedCost_sorting(int[][] costs) {
        int len = costs.length;
        if (len == 0)
            return 0;

        // Arrays.sort(costs, (x, y) -> x[0] < y[0] ? -1 : (x[0] == y[0] ? (y[1] > x[1]
        // ? -1 : 1) : 1));

        Arrays.sort(costs, (x, y) -> (x[0] - x[1]) - (y[0] - y[1]));
        /*
         * Arrays.sort(costs, new Comparator<int[]>() {
         * 
         * @Override public int compare(int[] x, int[] y) { return (x[0] - x[1]) - (y[0]
         * - y[1]); } });
         */

        int min_cost = 0;

        // add cost to visiting city 1

        for (int i = 0; i < len / 2; i++)
            min_cost += costs[i][0];

        for (int i = len / 2; i < len; i++)
            min_cost += costs[i][1];

        return min_cost;
    }
}

class Custom {
    int idx;
    int val;

    public Custom(int i, int v) {
        idx = i;
        val = v;
    }

}
