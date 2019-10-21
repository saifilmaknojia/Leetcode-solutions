import java.util.*;

// https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java:-Backtracking-solution.
// https://leetcode.com/problems/palindrome-partitioning/discuss/182307/Java:-Backtracking-Template-General-Approach
class Palindrome_Partitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        // pre-check, if empty string
        if (s == null || s.length() == 0)
            return result;

        exploreString(s, new ArrayList<String>(), result);

        return result;
    }

    // backtracking part
    private void exploreString(String curr, List<String> temp, List<List<String>> set) {

        // as we explore out, we will complete a set when the string which we pass
        // becomes empty
        // once we reach there, add the list to the result set
        if (curr.equals("")) {
            set.add(new ArrayList<>(temp));
            return;
        }

        // main logic of backtracking, choose, explore, recurse, unchoose
        for (int i = 0; i < curr.length(); i++) {
            // CHOOSE
            String work_on = curr.substring(0, i + 1);

            // Explore - see if the chosen string is a palindrome
            if (isPalindrome(work_on)) {
                // add chosen string to temp set
                temp.add(work_on);
                // recurse, backtrack other options
                exploreString(curr.substring(i + 1), temp, set);
                // unchoose
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String track) {
        // boolean res;
        int l = 0, r = track.length() - 1;

        while (l < r) {
            if (!(track.charAt(l) == track.charAt(r)))
                return false;
            l++;
            r--;
        }
        return true;
    }
}