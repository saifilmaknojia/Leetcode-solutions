import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> final_res = new ArrayList<List<Integer>>();
        List<Integer> mid = new ArrayList<Integer>();
        if (numRows == 0)
            return final_res;
        mid.add(1);
        final_res.add(mid);
        if (numRows == 1)
            return final_res;
        // System.out.println(final_res);
        mid = new ArrayList<Integer>();
        mid.add(1);
        mid.add(1);
        final_res.add(mid);
        // System.out.println(mid);
        // System.out.println(final_res);
        for (int i = 3; i <= numRows; i++) {
            Integer[] calc = mid.toArray(new Integer[mid.size()]);
            // System.out.println(mid);
            mid = new ArrayList<Integer>();
            // System.out.println("MID = "+mid);
            mid.add(1);
            for (int j = 0; j < calc.length - 1; j++) {
                mid.add(calc[j] + calc[j + 1]);
                // System.out.println(mid);
            }
            mid.add(1);
            final_res.add(mid);
            // System.out.println(final_res);
            // mid=new ArrayList<Integer>();
        }
        return final_res;
    }
}