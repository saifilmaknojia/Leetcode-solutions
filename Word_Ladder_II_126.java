import java.util.*;

public class Word_Ladder_II_126 {

}

class Solution_WL2 {
    List<List<String>> result = new ArrayList<>();
    HashMap<String, List<String>> graph = new HashMap<>();
    HashSet<String> words;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // A combination of BFS AND DFS
        // Use BFS to create the adjacency graph or the neighbors which will be used to
        // find the sequence of transformations
        // use DFS to go through and create the answer

        words = new HashSet<>(wordList);
        words.add(beginWord);

        for (String x : words)
            graph.put(x, new ArrayList<>());

        bfs(words, beginWord, endWord);
        dfs(beginWord, endWord, new ArrayList<>());
        // System.out.println(graph);

        return result;

    }

    private void bfs(HashSet<String> words, String start, String end) {
        boolean foundEndWord = false;
        Queue<String> track = new LinkedList<>();
        track.offer(start);

        HashSet<String> visited = new HashSet<>();
        HashSet<String> toVisit = new HashSet<>();

        toVisit.add(start);

        while (!track.isEmpty()) {
            // System.out.println(track);
            visited.addAll(toVisit);
            toVisit.clear();

            int size = track.size();

            for (int i = 0; i < size; i++) {
                String work = track.poll();
                words.remove(work);

                int word_len = work.length();

                for (int j = 0; j < word_len; j++) {
                    char[] word_arr = work.toCharArray();
                    for (char k = 'a'; k <= 'z'; k++) {
                        word_arr[j] = k;

                        String newFormed = new String(word_arr);
                        if (words.contains(newFormed) && !visited.contains(newFormed)) {
                            graph.get(work).add(newFormed);
                        }

                        if (!toVisit.contains(newFormed) && !visited.contains(newFormed) && words.contains(newFormed)) {
                            toVisit.add(newFormed);
                            track.offer(newFormed);
                        }

                        if (newFormed.equals(end))
                            foundEndWord = true;
                    }
                }
            }

            if (foundEndWord == true)
                break;
        }

    }

    private void dfs(String traverse, String end, List<String> inter) {
        inter.add(traverse);
        if (traverse.equals(end)) {
            result.add(new ArrayList<String>(inter));
            return;
        }
        for (String child : graph.get(traverse)) {
            dfs(child, end, inter);
            inter.remove(inter.size() - 1);
        }

    }
}
