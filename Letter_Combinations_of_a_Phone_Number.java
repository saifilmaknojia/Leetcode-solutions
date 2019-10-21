import java.util.*;

// did myself using backtracking :)

class Letter_Combinations_of_a_Phone_Number {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        String[] map = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        // int len = digits.length();
        char[] arr = digits.toCharArray();

        performBackTrack(arr, map, res, new StringBuilder(), 0);
        return res;

    }

    private void performBackTrack(char[] digits, String[] map, List<String> ans, StringBuilder temp, int idx) {

        if (temp.length() == digits.length) {
            ans.add(temp.toString());
            temp = new StringBuilder();
            return;
        }
        for (int i = idx; i < digits.length; i++) {
            int getFrom = digits[i] - '2';
            for (int j = 0; j < map[getFrom].length(); j++) {
                temp.append(map[getFrom].charAt(j));
                performBackTrack(digits, map, ans, temp, i + 1);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
}