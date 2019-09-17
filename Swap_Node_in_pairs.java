class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    // Iterative approach
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) {
            // E.g - 1->2->3->4->null

            // here - tmp = 1;
            ListNode tmp = cur;
            // curr will become 2, since curr = curr.next
            cur = cur.next;
            // now, we change tmps next pointer i.e change 1s next pointer to point to 2s
            // next, i.e 3, so we get, 1->3, curr = 2
            tmp.next = cur.next;
            // now we point curr.next to tmp, i.e our curr is 2, so curr.next which intially
            // points to 3, we change it to temp, in this case 1, so we get 2->1
            cur.next = tmp;
            // now we change our curr, which was 2 previously to point to 3, since tmp.next
            // = 3
            cur = tmp.next;

            // this is the main step, we need to change 1s next to point to 4 instead of 3,
            // so we first check, if curr which is 3 in this case is null or curr.next which
            // is 4 in this case is null or not, if not then we change 1->4
            if (cur != null && cur.next != null)
                tmp.next = cur.next;
        }
        return newHead;
    }

    // Recursive approach
    public ListNode swapPairs1(ListNode head) {
        // If our head is null || head.next is null it means we are at last element
        if (head == null || head.next == null)
            return head;

        // we store nextP so that we can change its next after recursion
        ListNode nextP = head.next;
        // we recurse, such that our list is arranged
        head.next = swapPairs(nextP.next);
        // after recursion we point the next pointer to current head
        // i.e if list is 1-> 2, we point 2->1 in next step
        nextP.next = head;

        // return nextP
        return nextP;
    }
}