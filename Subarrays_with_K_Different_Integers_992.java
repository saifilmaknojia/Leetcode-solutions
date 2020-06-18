import java.util.*;

public class Subarrays_with_K_Different_Integers_992 {

}

class Solution_1 {
    public int subarraysWithKDistinct(int[] A, int K) {
        // sliding window and 2 pointer concept
        int len = A.length;

        HashSet<Integer> unique_elements = new HashSet<>();
        int max_number = 0;

        for (int x : A) {
            if (x > max_number)
                max_number = x;
            unique_elements.add(x);
        }

        if (unique_elements.size() < K)
            return 0;

        // Array of hashSet of length = A.length - K + 1
        int arr_len = len - K + 1;
        int[] result_arr = new int[arr_len];
        HashSet<Integer>[] tracker = new HashSet[arr_len];
        int result = 0;

        // initializing the array of hashset and
        // filling array of hashset cell by considering window of size K
        for (int i = 0; i < arr_len; i++) {
            tracker[i] = new HashSet<>();
            for (int j = i; j < j + K; j++) {
                tracker[i].add(A[j]);
            }
            if (tracker[i].size() == K)
                result_arr[i]++;
        }

        // filling array of hashset cell by considering window of size K
        for (int window_size = K; window_size < arr_len; window_size++) {
            for (int i = window_size; i < arr_len; i++) {
                int add_element_to_hashset_at_idx = i - window_size;
                if (tracker[add_element_to_hashset_at_idx].size() > K)
                    continue;
                tracker[add_element_to_hashset_at_idx].add(A[i]);
                if (tracker[add_element_to_hashset_at_idx].size() == K)
                    result_arr[add_element_to_hashset_at_idx]++;
            }
        }

        // calculate final result
        for (int r : result_arr)
            result += r;

        return result;

    }
}