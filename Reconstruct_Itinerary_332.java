import java.util.*;

public class Reconstruct_Itinerary_332 {
    HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
    List<String> result = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // sort and remove
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!graph.containsKey(from)) {
                PriorityQueue<String> temp = new PriorityQueue<>();
                temp.offer(to);
                graph.put(from, temp);
                continue;
            }
            PriorityQueue<String> fetched = graph.get(from);
            fetched.offer(to);
            graph.put(from, fetched);
        }

        dfs("JFK");

        return result;
    }

    private void dfs(String curr_journey_from) {
        if (graph.containsKey(curr_journey_from)) {
            PriorityQueue<String> all_children = graph.get(curr_journey_from);
            while (!all_children.isEmpty()) {
                String curr_to = all_children.poll();
                dfs(curr_to);
            }
        }
        result.add(0, curr_journey_from);
    }

    public static void main(String[] args) {
        Reconstruct_Itinerary_332 obj = new Reconstruct_Itinerary_332();
        List<List<String>> test_case = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("JFK");
        temp.add("KUL");
        test_case.add(temp);
        temp = new ArrayList<>();
        temp.add("JFK");
        temp.add("NRT");
        test_case.add(temp);
        temp = new ArrayList<>();
        temp.add("NRT");
        temp.add("JFK");
        test_case.add(temp);
        System.out.println(
                "Test case: [[JFK,KUL],[JFK,NRT],[NRT,JFK]], Expected result = [JFK, NRT, JFK, KUL], Got result = "
                        + obj.findItinerary(test_case));
    }
}