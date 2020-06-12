import java.util.HashSet;

public class Delete_Columns_to_Make_Sorted_II_955 {
    public int minDeletionSize(String[] A) {
        int len = A.length;
        if (len == 0)
            return 0;

        HashSet<Integer> deleted_columns = new HashSet<>();

        int word_len = A[0].length();

        search: for (int i = 1; i < len; i++) {
            for (int j = 0; j < word_len; j++) {
                char at_j = A[i].charAt(j);
                char at_j_minus_1 = A[i - 1].charAt(j);

                if (deleted_columns.contains(j) || at_j == at_j_minus_1)
                    continue;

                if (at_j > at_j_minus_1)
                    continue search;
                else {
                    deleted_columns.add(j);
                    i = 0;
                    break;
                }
            }
            if (i == len)
                return deleted_columns.size();
        }
        return deleted_columns.size();
    }
}