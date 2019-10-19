import java.util.*;

class Solution {

    // This approach is similar to smallest window substring but it is not optimal
    // for this question since we are moving right pointer ahead till we get all the
    // characters of string t, but in our case we can check if curr character is
    // present in p, if not then we can move forward
    public List<Integer> findAnagrams1(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length())
            return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0)
                    counter--;
            }
            end++;

            while (counter == 0) {
                if (end - begin == t.length()) {
                    result.add(begin);
                }
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }

                begin++;
            }

        }
        return result;
    }

    // This approach is little better than the findAnagrams1, since here we first
    // check, if the length of our window is same as length of the string p, if yes
    // then we check if the count is 0, if not we move left pointer, here we are
    // working window wise, i.e length of string p
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return res;
        int count = p.length();
        int[] rec = new int[256];
        int l = 0, r = 0;
        for (char c : p.toCharArray()) {
            rec[c]++;
        }
        while (r < s.length()) {
            if (rec[s.charAt(r)] >= 1)
                count--;
            rec[s.charAt(r)]--;
            r++;
            if (r - l == p.length()) {
                if (count == 0)
                    res.add(l);
                if (rec[s.charAt(l)] >= 0)
                    count++;

                rec[s.charAt(l)]++;
                l++;
            }
        }
        return res;
    }

    // This is better approach, but a little tricky, we check if curr character of
    // string s is in p, if yes we move right pointer else we move left pointer and
    // at each step we check if our count is equal to p length

    public List<Integer> findAnagrams(String s, String p) {
        int left = 0;
        int right = 0;
        int sLen = s.length();
        int pLen = p.length();
        int[] hash = new int[26];
        List<Integer> pos = new ArrayList<Integer>();

        for (int i = 0; i < pLen; i++) {
            hash[p.charAt(i) - 'a']++;
        }
        int count = 0;

        while (right < sLen) {
            if (hash[s.charAt(right) - 'a'] > 0) {
                hash[s.charAt(right) - 'a']--;
                count++;
                right++;
            } else {
                hash[s.charAt(left) - 'a']++;
                count--;
                left++;
            }

            if (count == pLen) {
                pos.add(left);
            }

        }
        return pos;
    }

}