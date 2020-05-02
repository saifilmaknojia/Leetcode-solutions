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
}

class Custom {
    int idx;
    int val;

    public Custom(int i, int v) {
        idx = i;
        val = v;
    }
}