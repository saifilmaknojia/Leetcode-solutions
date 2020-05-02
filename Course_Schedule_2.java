import java.util.*;

class Course_Scheule_2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // topological sorting

        int len = prerequisites.length;
        int[] in_degree = new int[numCourses];
        List<List<Integer>> outgoing_edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            outgoing_edges.add(new ArrayList<Integer>());

        for (int i = 0; i < len; i++) {
            int parent = prerequisites[i][1];
            int child = prerequisites[i][0];
            outgoing_edges.get(parent).add(child);

            in_degree[child]++;
        }

        // boolean[] visited = new boolean[numCourses];
        Deque<Integer> traverse = new ArrayDeque<>();
        for (int j = 0; j < numCourses; j++) {
            if (in_degree[j] == 0)
                traverse.offer(j);
        }

        // core algo
        int[] result = new int[numCourses];
        int idx = 0;
        while (!traverse.isEmpty()) {
            int poll = traverse.pollFirst();
            // System.out.println(poll);
            // if(visited[poll] == true)
            // return new int[0];
            result[idx] = poll;
            idx++;
            if (outgoing_edges.get(poll) == null)
                continue;
            for (int y : outgoing_edges.get(poll)) {
                in_degree[y]--;
                if (in_degree[y] == 0)
                    traverse.offer(y);
            }
            // visited[poll] = true;

        }

        return idx != numCourses ? new int[0] : result;
    }
}