import java.util.*;

public class Remove_K_Digit_402 {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (k >= len)
            return "0";
        if (k == 0)
            return num;

        Deque<Integer> stk = new ArrayDeque<>();

        char[] arr = num.toCharArray();
        stk.offer(arr[0] - '0');
        int i = 1;
        for (; i < len; i++) {
            if (k == 0)
                break;
            int curr = arr[i] - '0';
            while (k > 0 && !stk.isEmpty() && stk.peekLast() > curr) {
                stk.pollLast();
                k--;
            }
            stk.offer(curr);
        }

        while (k > 0) {
            stk.pollLast();
            k--;
        }
        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            int polled = stk.pollFirst();
            // System.out.println(polled);
            if (res.length() > 0 || polled > 0)
                res.append(polled);
        }
        // System.out.println(res);
        res.append(num.substring(i));

        // for(;i<len; i++)
        // res.append(arr[i]);

        return res.length() == 0 ? "0" : res.toString();
    }
}
