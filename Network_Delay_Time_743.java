import java.util.*;

// Dijkstra's Algorithm
public class Network_Delay_Time_743 {
    public int networkDelayTime(int[][] times, int N, int K) {
        // will need PQ<Network_Node>
        // will need graph made of adjacency list which consist of Network_Node

        // increasing N because, it is 1 based so initially if n is 3 then N + 1 = 4, so
        // our array will be 0-1-2-3
        N = N + 1;

        // reached is to check if all nodes are visited, if all nodes are not visited we
        // have to return -1;
        HashSet<Integer> reached = new HashSet<>();
        reached.add(K);

        int min_time_result = 0;
        List<List<Network_Node>> graph = new ArrayList<>();
        PriorityQueue<Network_Node> min_heap = new PriorityQueue<>(N - 1, new Network_Node());

        int[] min_time_tracker = new int[N];

        // initialize the graph
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<Network_Node>());

        for (int[] time : times) {
            int src = time[0];
            int dest = time[1];
            int cost = time[2];

            reached.add(dest);
            Network_Node temp_node = new Network_Node(dest, cost);

            graph.get(src).add(temp_node);
        }

        // not all nodes can be visited
        if (reached.size() != N - 1)
            return -1;
        // K is our source
        min_time_tracker[K] = 0;
        for (int i = 1; i < N; i++) {
            if (i != K)
                min_time_tracker[i] = Integer.MAX_VALUE;
        }

        min_heap.offer(new Network_Node(K, 0));

        while (!min_heap.isEmpty() && reached.size() > 0) {
            Network_Node curr = min_heap.poll();
            reached.remove(curr.outgoing_edge);
            for (Network_Node neighbors : graph.get(curr.outgoing_edge)) {
                int cost = neighbors.cost;
                int edge = neighbors.outgoing_edge;

                if (!reached.contains(edge))
                    continue;

                int new_cost = min_time_tracker[curr.outgoing_edge] + cost;

                if (new_cost < min_time_tracker[edge]) {
                    min_time_tracker[edge] = new_cost;
                }
                // System.out.println(edge + "--> "+new_cost);
                min_heap.offer(new Network_Node(edge, new_cost));
            }

        }

        for (int x : min_time_tracker)
            min_time_result = Math.max(min_time_result, x);

        return min_time_result;
    }

    public static void main(String[] args) {
        Network_Delay_Time_743 obj = new Network_Delay_Time_743();
        System.out.println(
                "Test Case = times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2, Expected Result = 2, Got Result = "
                        + obj.networkDelayTime(new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } }, 4, 2));

        System.out.println(
                "Test Case = times = [[2,1,1],[2,3,1],[3,4,1],[4,5,11],[4,6,5],[3,7,4],[7,4,4]], N = 7, K = 2, Expected Result = 13, Got Result = "
                        + obj.networkDelayTime(new int[][] { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 }, { 4, 5, 11 },
                                { 4, 6, 5 }, { 3, 7, 4 }, { 7, 4, 4 } }, 7, 2));
    }
}

class Network_Node implements Comparator<Network_Node> {

    int outgoing_edge;
    int cost;

    public Network_Node(int edge, int cst) {
        outgoing_edge = edge;
        cost = cst;
    }

    public Network_Node() {

    }

    @Override
    public int compare(Network_Node o1, Network_Node o2) {
        return o1.cost < o2.cost ? -1 : 1;
    }
}