
import java.util.*;

class combination_Sum {
    int ta;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        ta = target;
        if (target <= 0)
            return result;

        Arrays.sort(candidates);

        List<Integer> temp = new ArrayList<>();

        backTrack(candidates, 0, temp, result, 0);
        return result;

    }

    private void backTrack(int[] arr, int sum, List<Integer> t, List<List<Integer>> res, int idx) {
        if (sum > ta)
            return;
        if (sum == ta) {
            // System.out.println(sum);
            res.add(new ArrayList<>(t));
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            int curr = arr[i];
            t.add(curr);
            // System.out.println(t +" ---> " + sum);
            // // here we pass i as the next index because, each number in the candidate can
            // be used any number of times
            backTrack(arr, sum + curr, t, res, i);
            t.remove(t.size() - 1);
        }
    }
}