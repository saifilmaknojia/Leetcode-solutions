import java.util.*;
/* 
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> count = new HashMap<>();
        PriorityQueue<Pair> ans = new PriorityQueue<Pair>(new Pair());

        List<String> res = new ArrayList<>();

        for (String s : words)
            count.put(s, count.getOrDefault(s, 0) + 1);

        for (Map.Entry<String, Integer> e : count.entrySet()) {
            ans.offer(new Pair(e.getKey(), e.getValue()));
        }

        while (k > 0)
            res.add(ans.poll().word);

        return res;
    }
}

class Pair implements Comparator<Pair> {
    int ct;
    String word;

    public Pair(String w, int c) {
        ct = c;
        word = w;
    }

    public Pair() {

    }

    public int compare(Pair f, Pair s) {
        if (f.ct != s.ct)
            return s.ct - f.ct;

        return f.word.compareTo(s.word);
    }

}*/

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        if (n < 1)
            return 0;
        // int len = reservedSeats.length;

        /*
         * Arrays.sort(reservedSeats, (x, y) -> { if (x[0] != y[0]) return x[0] - y[0];
         * else return x[1] - y[1]; });
         */

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int[] curr : reservedSeats) {
            map.putIfAbsent(curr[0], new ArrayList<>());
            map.get(curr[0]).add(curr[1]);
        }

        boolean[] track;
        int result = 0;
        // HashSet<Integer> not_empty_rows = new HashSet<>();
        // for (int i = 0; i < len;) {
        // int row = reservedSeats[i][0];
        // not_empty_rows.add(row);
        for (int x : map.keySet()) {
            List<Integer> curr_list = map.get(x);
            track = new boolean[4];
            for (int col : curr_list) {
                // int col = reservedSeats[i][1];
                if (col > 1 && col < 4)
                    track[0] = true;
                else if (col > 3 && col < 6)
                    track[1] = true;
                else if (col > 5 && col < 8)
                    track[2] = true;
                else if (col > 7 && col < 10)
                    track[3] = true;

                // i++;
            }
            int false_count = 0;
            for (boolean curr : track) {
                // System.out.println(curr);
                if (!curr)
                    false_count++;
            }
            // System.out.println(" FC "+false_count);
            if (false_count == 4) {
                // System.out.println("hello ");
                result += 2;
            } else if (false_count >= 2
                    && ((!track[0] && !track[1]) || (!track[2] && !track[3]) || (!track[1] && !track[2])))
                result += 1;
            // else if()
        }

        return result + ((n - map.size()) * 2);
    }
}