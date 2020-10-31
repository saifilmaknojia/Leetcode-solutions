public class Galatea {
    public int solution_dice(int[] A) {
        /*
         * int result = 0; int[] frequency = new int[7]; int[] pairs = new int[4];
         * 
         * for (int x : A) { frequency[x]++; if (x > 3) { pairs[7 - x]++; } else {
         * pairs[x]++; } }
         * 
         * int max_pair_idx = -1; int max_pair_count = Integer.MIN_VALUE;
         * 
         * for (int i = 1; i < 4; i++) { if (max_pair_count < pairs[i]) { max_pair_idx =
         * i; max_pair_count = pairs[i]; } }
         * 
         * int convert_to = -1; if (frequency[max_pair_idx] > frequency[7 -
         * max_pair_idx]) convert_to = max_pair_idx; else convert_to = 7 - max_pair_idx;
         */

        int min_res = Integer.MAX_VALUE;
        for (int convert_to = 1; convert_to < 7; convert_to++) {
            int result = 0;
            for (int x : A) {
                if (x == convert_to)
                    continue;
                else if (convert_to + x == 7)
                    result += 2;
                else
                    result += 1;
            }
            min_res = Math.min(min_res, result);
        }

        return min_res;
    }

    public boolean solution_sameString(String S, String T) {
        // write your code in Java SE 8
        // if length not same, return false
        // if both char and not same, return false
        StringBuilder s = convertString(S);
        StringBuilder t = convertString(T);
        // System.out.println(s);
        // System.out.println(t);
        if (s.length() != t.length())
            return false;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char s1 = s.charAt(i);
            char t1 = t.charAt(i);

            if (s1 == '?' || t1 == '?' || s1 == t1)
                continue;
            else
                return false;

        }
        return true;

    }

    private StringBuilder convertString(String curr) {
        StringBuilder building = new StringBuilder();
        int digits = 0;
        int len = curr.length();
        for (int i = 0; i < len; i++) {
            char now = curr.charAt(i);
            if (Character.isAlphabetic(now)) {
                while (digits > 0) {
                    building.append('?');
                    digits--;
                }
                building.append(now);
            } else {
                digits = (digits * 10) + (now - '0');
            }
        }

        // for last number
        while (digits > 0) {
            building.append('?');
            digits--;
        }

        return building;
    }
}
