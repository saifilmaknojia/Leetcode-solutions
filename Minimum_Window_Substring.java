// Here we are taking substring everytime we find a better result, but substring is a costly operation instead we can maintain a min length, which is initailized to Integer.MAX_VALUE and every time we check if r-l is less than minimum then we upadate the minlength and along with it we also have to store a minStart, so when we return the result we would be return s.substring(minstart, minstart + minlength)
// Look at the submission solution of 2 second

class Solution {
    public String minWindow(String s, String p) {
        // Initializing the result to be the whole string, we will return empty if we
        // are not able to find string p using a boolean varialble
        String res = new String(s);

        // base case
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return res;
        // initializing count to length of p
        int count = p.length();
        // this is our tracker
        int[] rec = new int[256];
        // two pointers left and right
        int l = 0, r = 0;
        // we use this boolean to figure out in the end should we return empty string or
        // some result
        boolean foundString = false;

        // tracking the character counts of string p
        for (char c : p.toCharArray()) {
            rec[c]++;
        }

        // here the main algorithm begins
        while (r < s.length()) {
            // read character at index r
            char curr = s.charAt(r);

            // if value at index of current character is greater than 0, it means it is a
            // part of string p, reduce count
            if (rec[curr] > 0)
                count--;

            // here we actually reduce the value in the array, because we are considering
            // the current character is string s in the window
            rec[curr]--;
            // increment right pointer
            r++;

            // when our count becomes 0, it means we have found all the characters present
            // in the string p
            while (count == 0) {
                // now we mark the boolean value to true, to indicate we have found some ans and
                // would be returning it and not an empty string
                foundString = true;

                // here we check if the current window that we have got is less than the
                // previous minimum window(which is initally set to the whole string), if yes
                // then we use this window as our result
                if (r - l < res.length())
                    res = s.substring(l, r);

                // left pointer is used to shrink the window
                char left = s.charAt(l);

                // if the value at index of left character is greater than equal to 0, it means
                // the character is present in p and s string both we try to remove the
                // character from the window and see if we can form a smaller substring
                if (rec[left] >= 0)
                    count++;

                // here we actually increase the value in the array, because we are removing the
                // current character at the left index from the window
                rec[left]++;
                // increment left pointer
                l++;
            }
        }

        // here if our foundString is still false it means, we never entered while(count
        // == 0) loop, hence we were not able to locate all the characters of string p
        // in string s, therefore we return empty string else we return the result which
        // would be the minimum substring
        return foundString ? res : "";
    }
}