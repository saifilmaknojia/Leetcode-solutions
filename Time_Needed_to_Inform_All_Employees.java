import java.util.*;

class Time_Needed_to_Inform_All_Employees {
    HashMap<Integer, List<Integer>> children = new HashMap<>();

    // int[] total_cost_till_now;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int result = 0;
        int len = manager.length;
        // total_cost_till_now = informTime.clone();
        for (int i = 0; i < len; i++) {
            int curr = manager[i];
            children.putIfAbsent(curr, new ArrayList<>());
            children.get(curr).add(i);
        }
        // System.out.println(children);
        Deque<Integer> traverse = new ArrayDeque<>();
        traverse.offer(headID);
        while (!traverse.isEmpty()) {
            int size = traverse.size();
            // int curr_max = 0;
            for (int i = 0; i < size; i++) {
                int now = traverse.pollFirst();
                // System.out.println(now);
                List<Integer> fetch = children.get(now);

                if (fetch != null) {
                    // int z = 0;
                    for (int arr : fetch) {
                        // System.out.println(arr[0]);
                        informTime[arr] += informTime[now];
                        traverse.offer(arr);
                        // System.out.println(total_cost_till_now[arr]);
                        result = Math.max(result, informTime[arr]);
                        // z++;
                    }
                }
            }
            // result += max;
            // result = Math.max(result, total_cost_till_now[arr]);
        }
        return result;
    }
}
