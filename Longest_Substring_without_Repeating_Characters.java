class Longest_Substring_without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() <= 1)
            return s.length();

        char[] ch = s.toCharArray();

        int[] tracker = new int[256];

        int l = 0;
        int r = 0;

        int len = ch.length;
        int ans = Integer.MIN_VALUE;
        int count = 0;
        while (r < len) {
            char curr = ch[r];
            if (tracker[curr] == 0) {
                tracker[curr]++;
                count++;
                r++;
                continue;
            }
            // tracker[curr - 'a']++;
            ans = Math.max(count, ans);

            // System.out.println(r);
            while (tracker[curr] != 0) {
                tracker[ch[l]]--;
                l++;
                count--;
            }
        }
        ans = Math.max(count, ans);
        return ans;
    }
}