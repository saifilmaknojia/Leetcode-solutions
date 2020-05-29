public class Permutation_in_String_567 {
    // Same concept as - 438. Find All Anagrams in a String

    // best solution
    public boolean checkInclusion(String s1, String s2) {
        char[] s1_arr = s1.toCharArray();
        char[] s2_arr = s2.toCharArray();

        int[] s1_map = new int[26];

        for (char c : s1_arr)
            s1_map[c - 'a']++;

        int count = 0;

        int l = 0;
        int r = 0;

        int s2_len = s2.length();
        int s1_len = s1.length();
        while (r < s2_len) {
            int curr = s2_arr[r] - 'a';
            if (s1_map[curr] > 0) {
                count++;
                s1_map[curr]--;
                r++;
            } else {
                count--;
                int at_left = s2_arr[l] - 'a';
                s1_map[at_left]++;
                l++;
            }

            if (count == s1_len)
                return true;
        }
        return false;
    }

    // this is also a linear solution
    public boolean checkInclusion_other_linear(String s1, String s2) {
        char[] s1_arr = s1.toCharArray();
        char[] s2_arr = s2.toCharArray();

        int[] s1_map = new int[26];

        for (char c : s1_arr)
            s1_map[c - 'a']++;

        int count = s1_arr.length;

        int l = 0;
        int r = 0;

        int s2_len = s2.length();
        int s1_len = s1.length();
        while (r < s2_len) {
            int curr = s2_arr[r] - 'a';
            if (s1_map[curr] > 0)
                count--;

            s1_map[curr]--;
            if (count == 0)
                return true;

            r++;
            if (r - l >= s1_len) {
                int at_left = s2_arr[l] - 'a';
                s1_map[at_left]++;

                if (s1_map[at_left] > 0)
                    count++;

                l++;
            }

        }
        return false;
    }
}