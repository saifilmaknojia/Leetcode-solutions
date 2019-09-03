class Solution {
    // O(M+N) solution
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        // mark corresponding rows and columns as 1 indicating that complete row or
        // column needs to be marked as 0
        for (int i = 0; i < row.length; i++)
            for (int j = 0; j < col.length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }

        // Here, we are marking the rows as 0
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                for (int k = 0; k < col.length; k++) {
                    matrix[i][k] = 0;
                }
            }
        }

        // Here, we are marking the columns as 0
        for (int i = 0; i < col.length; i++) {
            if (col[i] == 1) {
                for (int k = 0; k < row.length; k++) {
                    matrix[k][i] = 0;
                }
            }
        }
    }
}