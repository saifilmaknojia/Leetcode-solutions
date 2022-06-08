public class Reshape_the_Matrix {
    class Solution {
        public int[][] matrixReshape(int[][] mat, int r, int c) {
            int size = mat.length * mat[0].length;

            if (size != r * c)
                return mat;

            int row = 0, col = 0;
            int[][] reshaped = new int[r][c];
            for (int[] is : mat) {
                for (int curr : is) {
                    reshaped[row][col] = curr;
                    col++;
                    if (col == c) {
                        row++;
                        col = 0;
                    }
                }
            }
            return reshaped;
        }
    }
}
