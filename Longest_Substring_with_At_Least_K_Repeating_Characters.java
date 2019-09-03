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
class Solution {
    public int longestSubstring(String s, int k) {
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
}