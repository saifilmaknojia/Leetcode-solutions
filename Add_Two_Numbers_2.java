package Amazon;

import java.util.*;

// Definition for singly-linked list. 
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// Stack Solution
class Add_Two_Numbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode prev = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = 0;
            if (!s1.isEmpty())
                sum += s1.pop().val;

            if (!s2.isEmpty())
                sum += s2.pop().val;

            sum += carry;
            ListNode curr = new ListNode(sum % 10);
            curr.next = prev;
            prev = curr;
            carry = sum >= 10 ? 1 : 0;
        }

        if (carry == 1) {
            ListNode curr = new ListNode(1);
            curr.next = prev;
            prev = curr;
        }

        return prev;
    }
}