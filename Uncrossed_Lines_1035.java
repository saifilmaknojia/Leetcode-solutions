public class Uncrossed_Lines_1035 {
    // same as - 1143. Longest Common Subsequence
    public int maxUncrossedLines(int[] A, int[] B) {
        int a_len = A.length;
        int b_len = B.length;

        if (a_len == 0 || b_len == 0)
            return 0;

        // O(N square) time and space
        int[][] dp = new int[a_len][b_len];
        // Row(i) represents A
        // Col(j) represents B
        for (int i = 0; i < a_len; i++)
            for (int j = 0; j < b_len; j++) {
                // if its the first row and first column
                if (i == 0 && j == 0)
                    dp[i][j] = A[i] == B[j] ? 1 : 0;
                else if (i == 0) // its the first row
                    dp[i][j] = A[i] == B[j] ? 1 : dp[0][j - 1];
                else if (j == 0) // its the first column
                    dp[i][j] = A[i] == B[j] ? 1 : dp[i - 1][0];
                else {
                    // if the number in A at ith position and the number in B at jth position match
                    // then we take the diagonal entry and add 1 to it
                    if (A[i] == B[j])
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else // else we take the max from the top row or the left column, i.e whichever gives
                         // us the max either A or B
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

        return dp[a_len - 1][b_len - 1];

    }

    public static void main(String[] args) {
        Uncrossed_Lines_1035 obj = new Uncrossed_Lines_1035();
        System.out.println("Expected result = 2, " + "Got result = "
                + obj.maxUncrossedLines(new int[] { 1, 4, 2 }, new int[] { 1, 2, 4 }));
        System.out.println("Expected result = 3, " + "Got result = "
                + obj.maxUncrossedLines(new int[] { 2, 5, 1, 2, 5 }, new int[] { 10, 5, 2, 1, 5, 2 }));
        System.out.println("Expected result = 2, " + "Got result = "
                + obj.maxUncrossedLines(new int[] { 1, 3, 7, 1, 7, 5 }, new int[] { 1, 9, 2, 5, 1 }));
    }
}