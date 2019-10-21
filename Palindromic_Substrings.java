class Palindromic_Substrings {
    // Dynamic Programming Solution
    // https://leetcode.com/problems/palindromic-substrings/discuss/105714/Share-3-methods-DP-method-and-extend-method-Java
    public int countSubstrings(String s) {
        int count = 0;
        int l = s.length();
        boolean[][] dp = new boolean[l][l];

        // 1 character length palindromes
        for (int i = 0; i < l; i++) {
            count++;
            dp[i][i] = true;
        }

        // for higher length palindromes, we start from the bottom since last character
        // substring has only one possibility, we build on top of that
        for (int i = l - 2; i >= 0; i--)
            for (int j = i + 1; j < l; j++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i) < 2 || dp[i + 1][j - 1] == true)) {
                    count++;
                    dp[i][j] = true;
                }
            }

        return count;
    }

    // EXPAND AROUND CENTER METHOD

    /*
     * class Solution { int count = 0; String temp; public int
     * countSubstrings(String s) { // using extend around center concept //
     * https://medium.com/@bhprtk/longest-palindromic-substring-a8190fab03ff //
     * https://leetcode.com/problems/palindromic-substrings/discuss/105688/Very-
     * Simple-Java-Solution-with-Detail-Explanation temp = s; for(int i = 0;
     * i<s.length(); i++) { expandCenter(i, i); expandCenter(i, i+1); }
     * 
     * return count; }
     * 
     * public void expandCenter(int left, int right) { while(left>=0 &&
     * right<temp.length() && (temp.charAt(left)==temp.charAt(right))) { count++;
     * left--; right++; } }
     * 
     * 
     * }
     */
}