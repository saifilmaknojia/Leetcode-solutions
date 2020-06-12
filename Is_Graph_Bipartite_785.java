public class Is_Graph_Bipartite_785 {
    // same as 886 - Possible Bipartition
    // based on the concept of Graph coloring
    public boolean isBipartite(int[][] graph) {
        // let the two groups be 1 and -1
        // by default initially every one is in group 0

        int len = graph.length;

        int[] group = new int[len];

        for (int i = 0; i < len; i++) {
            int default_group = group[i];

            if (group[i] == 0)
                default_group = 1;

            if (!graphColoringDFS(graph, default_group, group, i))
                return false;

            // System.out.println(i);

        }
        return true;
    }

    private boolean graphColoringDFS(int[][] matrix, int alloted_group, int[] group_arr, int checking) {
        if (group_arr[checking] != 0)
            return group_arr[checking] == alloted_group;

        if (group_arr[checking] == 0)
            group_arr[checking] = alloted_group;

        for (int j = 0; j < matrix[checking].length; j++) {
            if (!graphColoringDFS(matrix, alloted_group * -1, group_arr, matrix[checking][j]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Is_Graph_Bipartite_785 obj = new Is_Graph_Bipartite_785();
        System.out.println("Test Case 1: { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } } , Expected result = true, "
                + " Got result = " + obj.isBipartite(new int[][] { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } }));
        System.out.println("Test Case 2: { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } }, Expected result = false, "
                + " Got result = " + obj.isBipartite(new int[][] { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } }));
    }
}