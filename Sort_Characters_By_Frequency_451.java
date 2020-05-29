import java.util.*;

class Sort_Characters_By_Frequency_451 {
    public String frequencySort(String s) {
        int len = s.length();

        if (len == 0)
            return "";

        PriorityQueue<Map.Entry<Character, Integer>> max_heap = new PriorityQueue<>(
                (x, y) -> y.getValue() - x.getValue());
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // System.out.println(map);
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            max_heap.offer(entry);

        StringBuilder result = new StringBuilder();
        StringBuilder b;

        while (!max_heap.isEmpty()) {
            Map.Entry<Character, Integer> curr_entry = max_heap.poll();
            char character = curr_entry.getKey();
            int times = curr_entry.getValue();

            b = new StringBuilder();
            for (int i = 0; i < times; ++i) {
                b.append(character);
            }

            result.append(b);
        }

        return result.toString();
    }
}