import java.util.*;

public class Cheapest_Flights_Within_K_Stops {
    /*
     * 1. We won't change the min-heap's priority which is to pick nodes with the
     * shortest distance from the source. 2. However, if we ever encounter a node
     * that has already been processed before but the number of stops from the
     * source is lesser than what was recorded before, we will add it to the heap so
     * that it gets considered again! That's the only change we need to make to make
     * Dijkstra's compliant with the limitation on the number of stops.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // similar to Dijkstra
        // x[0] = edge, x[1] = cost, x[2] = stops
        PriorityQueue<int[]> min_heap = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        List<int[]>[] graph = new ArrayList[n];

        // initializing arraylist for the graph
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int[] price_tracker = new int[n];
        int[] stops_tracker = new int[n];

        for (int i = 0; i < n; i++) {
            price_tracker[i] = Integer.MAX_VALUE;
            stops_tracker[i] = Integer.MAX_VALUE;
        }

        price_tracker[src] = 0;
        stops_tracker[src] = 0;

        // creating the graph
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            int[] temp = { to, cost, 0 };
            graph[from].add(temp);
        }

        // adding source to our min heap
        min_heap.offer(new int[] { src, 0, 0 });

        while (!min_heap.isEmpty()) {
            int[] curr_flight = min_heap.poll();
            int curr_node = curr_flight[0];
            int curr_cost = curr_flight[1];
            int curr_stops = curr_flight[2];

            if (curr_node == dst)
                return curr_cost;

            if (curr_stops == K + 1)
                continue;

            // System.out.println("curr_node = "+curr_node+" curr_cost = "+curr_cost+"
            // curr_stops = "+curr_stops);
            // if (curr_node == dst) {
            // if (curr_stops <= K)
            // result = Math.min(result, curr_cost);
            // }
            for (int[] neighbors : graph[curr_node]) {
                int neighbor_node = neighbors[0];
                // int neightbor_stops = neighbors.stops;
                int neighbor_cost = neighbors[1];
                // System.out.println("neighbor = "+neighbor_node+" neighbor cost =
                // "+neighbor_cost);
                int total_cost = neighbor_cost + curr_cost;
                if (total_cost < price_tracker[neighbor_node]) {
                    price_tracker[neighbor_node] = total_cost;
                    stops_tracker[neighbor_node] = curr_stops + 1;
                    int[] connect_flight = new int[] { neighbor_node, total_cost, curr_stops + 1 };
                    min_heap.offer(connect_flight);

                } else if (curr_stops + 1 < stops_tracker[neighbor_node]) {
                    int[] connect_flight = new int[] { neighbor_node, total_cost, curr_stops + 1 };
                    stops_tracker[neighbor_node] = curr_stops + 1;
                    price_tracker[neighbor_node] = total_cost;
                    min_heap.offer(connect_flight);
                }
            }
        }
        return price_tracker[dst] == Integer.MAX_VALUE ? -1 : price_tracker[dst];
    }
}