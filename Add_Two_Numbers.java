
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        int carry = 0;
        int sum = 0;
        // int add = 0;

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                sum = l2.val + carry;
                dummy.next = new ListNode(sum % 10);
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val + carry;
                dummy.next = new ListNode(sum % 10);
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val + carry;
                dummy.next = new ListNode(sum % 10);
                l1 = l1.next;
                l2 = l2.next;
            }

            dummy = dummy.next;
            if (sum >= 10)
                carry = 1;
            else
                carry = 0;
        }

        if (carry == 1)
            dummy.next = new ListNode(1);

        return head.next;
    }
}