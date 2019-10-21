import java.util.*;

class Group_Anagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> temp = new ArrayList<>();
        HashMap<String, List<String>> tracker = new HashMap<>();
        if (strs == null || strs.length == 0)
            return res;
        if (strs.length == 1) {
            temp.add(strs[0]);
            res.add(temp);
            return res;
        }

        for (String curr : strs) {
            char[] tempArray = curr.toCharArray();
            Arrays.sort(tempArray);
            String addString = new String(tempArray);
            // System.out.println(addString);
            temp = tracker.getOrDefault(addString, new ArrayList<>());
            temp.add(curr);
            tracker.put(addString, temp);
        }

        for (List<String> a : tracker.values())
            res.add(a);
        return res;

        /*
         * for (Map.Entry<String, List<String>> a : tracker.entrySet()) {
         * 
         * }
         */
    }
}