import java.util.*;

// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/322678/Java-recursive-solution-with-simple-explaining

// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87830/Java-solution-14-ms-Recursive-based-on-splitting-string-by-least-frequent-character

// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/319276/Java-recursive-solution-accepted-but-slow

/*
We follow the divide and conquer approach. i.e - find a character which doesnt satisfy >= k constraint and then split the string into 2 halves, i.e - left of that character and right of that character, we recurse on both of the halves and find max between them, we keep on following this way and finally we would get an ans

E.g - "aacbbbdc"
      k = 2

First we count the character count for each, i.e - a=2, c=2, b=3, d=1;
We then traverse the array and find the character which doesnt satisfy the k constraint
in this case that would be alphabet d at index 6, we recurse on left and right part of this index, i.e on left half = aacbbb and right half of c.

Again, we count character count of each substring, i.e for aacbbb it would be - a=2, b=3, c=1, so we perform the further steps, now c doesnt satisfy the criterion and hence we will split the it at character c i.e index - 2, we get 2 strings, aa and ccc, we recurse on them and find that both satisfy the k constraint but since bbb is of size 3 we return 3
*/
class Longest_Substring_with_At_Least_K_Repeating_Characters {
    public int longestSubstring1(String s, int k) {
        if (s == null || s.length() < k)
            return 0;

        return performDFS(s, k, 0, s.length());
    }

    private int performDFS(String curr, int k, int start, int end) {
        // if the curr substring which we are working on has size less than k than no
        // way we will find max, the ans would always be 0
        if (end - start < k)
            return 0;

        int max = Integer.MIN_VALUE;
        // char[] chars = curr.toCharArray();
        // character count tracker
        int[] alp = new int[26];
        // boolean isValid = true;
        for (int i = start; i < end; i++) {
            alp[curr.charAt(i) - 'a']++;
        }

        /*
         * for(int j = 0; j<26; j++) { if(alp[j]>0 && alp[j]<k) { isValid = false;
         * break; } }
         * 
         * if(isValid) return end - start;
         */
        // System.out.println(max);
        for (int i = start; i < end; i++) {
            // find the character which doesnt satisfy the k constraint and recurse on left
            // and right half of that index
            if (alp[curr.charAt(i) - 'a'] > 0 && alp[curr.charAt(i) - 'a'] < k) {
                // left half recurse, right half recurse
                max = Math.max(performDFS(curr, k, start, i), performDFS(curr, k, i + 1, end));
                return max;
            }
        }
        // if we return from this point that means that the current substring was valid
        // hence we return end - stary which would be the number of characters in the
        // substring
        return end - start;
    }

    // O(26N) = O(N) - Sliding window solution
    // https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/170010/Java-O(n)-Solution-with-Detailed-Explanation-Sliding-Window
    /*
     * 
     * this problem prompts us to use two pointer method, however it's quite
     * difficult to decide the condition to expand (j++) and shrink (i++) - that's
     * where the above posted solution rocks. thanks again!
     * 
     * if it helps, here are some explanations to understand above code: how do we
     * explore all possible solutions (substrings that satisfy given constraints) ?
     * 
     * this post's solution explores this way -- find all sub-strings which have
     * "h=1" unique character(s) and each character in the substring repeats at
     * least "k" times -- find all sub-strings which have "h=2" unique character(s)
     * and each character in the substring repeats at least "k" times -- .... --
     * find all sub-strings which have "h=26" unique character(s) and each character
     * in the substring repeats at least "k" times -- and we're done! at h = 26,
     * we're done. Take max of all the above valid substrings (by tracking with
     * --max-- variable) -- that'll be our answer. Details: (for a fixed h)
     * 
     * basically, we want to find a window (i to j) which has "h" unique chars and
     * if all h occur at least K times, we have a candidate solution [Rules for
     * window Expansion] so expand (j++) as long as the num of unique characters
     * between 'i' to 'j' are h or less (unique <= h) during expansion, also track
     * chars that are "noLessThanK" (which occur at least k) end of the loop update
     * max (max len of valid window which have h unique chars and all h have at
     * least k occurences) once we see -- unique = h + 1 -- , we just expanded our
     * window with (h+1)th unique char, so we should start to shrink now. [Rules to
     * window Shrink window] shrink as long as we have unique chars > h (update
     * counts, unique, NoLessThanK along the way)
     * 
     */

    public int longestSubstring(String s, int k) {
        int[] counts = new int[26];
        int max = 0;
        for (int u = 1; u <= 26; ++u) {
            Arrays.fill(counts, 0);
            int left = 0;
            int right = 0;
            int unique = 0;
            int kOrMore = 0;
            while (right < s.length()) {
                if (unique <= u) {
                    char c = s.charAt(right);
                    int idx = (int) c - (int) 'a';
                    if (counts[idx] == 0) {
                        ++unique;
                    }
                    counts[idx]++;
                    if (counts[idx] == k) {
                        ++kOrMore;
                    }
                    ++right;
                } else {
                    char o = s.charAt(left);
                    int idx = (int) o - (int) 'a';
                    counts[idx]--;
                    if (counts[idx] == 0) {
                        --unique;
                    }
                    if (counts[idx] == k - 1) {
                        --kOrMore;
                    }
                    ++left;
                }
                if (unique == u && kOrMore == unique) {
                    max = Math.max(max, right - left);
                }
            }
        }
        return max;
    }
}