import java.util.Arrays;
import java.util.HashMap;

public class Sell_Diminishing_Valued_Colored_Balls_1648 {
    class Solution {
        static final int MOD = 1000000007;

        public int maxProfit(int[] inventory, int orders) {
            Arrays.sort(inventory);

            long sum = 0;
            int times = 1;
            int curr_last = inventory.length - 1;

            // System.out.println("****************");

            while (curr_last > 0) {
                int prev = inventory[curr_last - 1];
                int curr = inventory[curr_last];

                // System.out.println("Inner while " + curr + ", Orders = " + prev);

                if (orders <= 0)
                    return (int) sum % MOD;
                else if (curr == prev) {
                    times++;
                } else if ((curr - prev) * times > orders) {
                    while (orders > 0) {
                        boolean equal_distribution = orders % times == 0;
                        System.out.println("Times " + times + ", Orders " + orders);
                        int go_till = curr - orders / times;
                        System.out.println("Go till " + go_till + " Curr " + curr);
                        if (equal_distribution)
                            orders = 0;
                        else
                            orders = orders % times;

                        sum += diff(curr, go_till) * times;
                        if (orders > 0) {
                            times = orders;
                            curr = go_till;
                        }
                    }
                    System.out.println("MID ELSE IF " + sum + ", Orders = " + orders);

                } else {
                    sum += diff(curr, prev) * times;
                    orders -= (curr - prev) * times;
                    times++;
                    System.out.println("LAST ELSE " + sum + ", Orders = " + orders + ", times: " + times);
                }
                sum = sum % MOD;
                curr_last--;
            }
            // System.out.println("MIDDLE while " + sum + ", ORders: " + orders);
            while (orders > 0) {

                System.out.println("Outer while " + sum);
                boolean equal_distribution = orders % times == 0;
                int go_till = orders / times;
                if (equal_distribution)
                    orders = 0;
                else
                    orders -= orders % times;

                sum += diff(inventory[0], inventory[0] - go_till) * times;
                // System.out.println(sum);
                if (orders > 0) {
                    times = orders;
                    inventory[0] = go_till;
                }
            }

            return (int) (sum % MOD);
        }

        private long diff(long n, long x) {
            return (n * (n + 1)) / 2 - (x * (x + 1)) / 2;
        }
    }
}
