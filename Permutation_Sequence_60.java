import java.util.*;

public class Permutation_Sequence_60 {
    public String getPermutation(int n, int k) {
        // refer orignal solution

        // things needed
        // factorial array from 0! to (n-1)!
        // numbers arraylist from 1 to n
        if (n == 0)
            return "";

        // creating factorial array
        int[] factorial_array = new int[n];
        factorial_array[0] = 1;
        for (int i = 1; i < n; i++)
            factorial_array[i] = factorial_array[i - 1] * i;

        // creating numbers list
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            numbers.add(i);

        // we subtract k by 1, because our permutation sequence will be from 0 to n - 1,
        // so Kth sequence will be at K - 1
        k -= 1;

        StringBuilder result = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            // below two are the main steps
            int idx = k / factorial_array[i];
            k -= idx * factorial_array[i];
            // can also use - k = k % factorial[i]; instead of above

            result.append(numbers.get(idx));

            numbers.remove(idx);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Permutation_Sequence_60 obj = new Permutation_Sequence_60();
        System.out.println("Test case: n = 3, k = 4, Expected result = 231, Got result = " + obj.getPermutation(3, 4));
        System.out.println(
                "Test case: n = 5, k = 18, Expected result = 14532, Got result = " + obj.getPermutation(5, 18));
    }
}