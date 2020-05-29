public class Count_Squares_1277 {
    public int countSquares(int[][] matrix) {
        int result = 0;

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                // if its either 1st row or 1st column then square of size 1 is only possible
                if (i == 0 || j == 0) {
                    if (matrix[i][j] == 1)
                        result++;

                    continue;
                }

                // or, we find the max length of square which is possible and we add that many
                // numbers to the result
                if (matrix[i][j] == 1) {
                    int min_length_square = Math.min(matrix[i - 1][j - 1],
                            Math.min(matrix[i - 1][j], matrix[i][j - 1]));
                    matrix[i][j] += min_length_square;
                    result += matrix[i][j];
                }
            }
        return result;
    }
}
