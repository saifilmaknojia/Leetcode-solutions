
import java.util.*;

// DP - https://www.youtube.com/watch?v=kSDI_iOiGQY

class Super_Ugly {
    // Min_heap method
    public int nthSuperUglyNumber_Heap(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : primes) {
            pq.offer(x);
        }
        for (int i = 0; i < n - 2; ++i) {
            int cur = pq.poll();
            for (int x : primes) { // each one pop out should multiply the array
                long mult = (long) cur * (long) x;
                if (mult < Integer.MAX_VALUE) {
                    pq.offer((int) mult);
                }
            }
            while (pq.peek() == cur) { // It is sorted, so if duplicate, only happen at root, poll it
                pq.poll();
            }
        }
        return pq.poll();
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n];
        int[] idx = new int[primes.length];
        dp[0] = 1;

        for (int j = 1; j < n; j++) {
            int curr_min = Integer.MAX_VALUE;

            for (int i = 0; i < primes.length; i++)
                curr_min = Math.min(curr_min, primes[i] * dp[idx[i]]);

            // this loop is to avoid duplicates, i,e in case of 2,3,5 - we get 6 for both 2
            // and 3, so to avoid that
            dp[j] = curr_min;
            for (int i = 0; i < primes.length; i++)
                while (primes[i] * dp[idx[i]] <= curr_min)
                    idx[i]++;
        }

        return dp[n - 1];

    }
}