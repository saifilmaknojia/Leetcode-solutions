import java.util.*;

class Four_Sum_II {
    // My Approach
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        HashMap<Integer, Integer> x = new HashMap<>();
        int l = A.length;
        int[] firstTwo = new int[l * l];
        int idx = 0;
        for (int a : A)
            for (int b : B)
                firstTwo[idx++] = a + b;

        for (int c : C)
            for (int d : D) {
                int s = c + d;
                x.put(s, x.getOrDefault(s, 0) + 1);
            }

        Arrays.sort(firstTwo);

        for (int i : firstTwo) {
            int neg = -i;
            if (x.containsKey(neg))
                res += x.get(neg);
        }

        return res;
    }
}

/*
 * Better approach class Solution { public int fourSumCount(int[] A, int[] B,
 * int[] C, int[] D) { Map<Integer,Integer> result = new
 * HashMap<Integer,Integer>(); for(int i=0;i<A.length;i++) { for(int
 * j=0;j<B.length;j++) { int sum = A[i]+B[j];
 * result.put(sum,result.getOrDefault(sum,0)+1); } } int res =0; for(int
 * i=0;i<C.length;i++) { for(int j=0;j<D.length;j++) { res +=
 * result.getOrDefault(-(C[i]+D[j]),0); } } return res; } }
 */