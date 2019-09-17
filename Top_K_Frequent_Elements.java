import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] test = { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        System.out.println(s.topKFrequent(test, k));
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> tracker = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                new Comparator<Map.Entry<Integer, Integer>>() {
                    public int compare(Map.Entry<Integer, Integer> n1, Map.Entry<Integer, Integer> n2) {
                        return n2.getValue() - n1.getValue();
                    }
                });

        for (int x : nums)
            tracker.put(x, tracker.getOrDefault(x, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : tracker.entrySet())
            pq.add(entry);

        // int ct = k;
        while (k-- > 0)
            res.add(pq.poll().getKey());

        return res;
    }
}