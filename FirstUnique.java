import java.util.*;

class FirstUnique {
    HashSet<Integer> count = new HashSet<>();
    HashMap<Integer, Node> tracker = new HashMap<>();

    DLL list = new DLL();

    public FirstUnique(int[] nums) {
        for (int x : nums) {
            if (!count.contains(x)) {
                count.add(x);
                Node added = list.addEntry(x);
                tracker.put(x, added);
            } else if (tracker.containsKey(x)) {
                Node remove = tracker.get(x);
                tracker.remove(x);
                list.removeNodeFromDLL(remove);
            }
        }
    }

    public int showFirstUnique() {
        return list.getHead();
    }

    public void add(int x) {

        if (!count.contains(x)) {
            count.add(x);
            Node added = list.addEntry(x);
            tracker.put(x, added);
        } else if (tracker.containsKey(x)) {
            Node remove = tracker.get(x);
            tracker.remove(x);
            list.removeNodeFromDLL(remove);
        }
    }
}

// Doubly linkedlist
class DLL {
    Node head, enterAt, tail;

    public DLL() {
        enterAt = new Node(0);
        tail = new Node(0);

        enterAt.next = tail;
        tail.prev = enterAt;

        head = enterAt;
    }

    public Node addEntry(int num) {
        Node attach = new Node(num);

        enterAt.next = attach;
        attach.prev = enterAt;

        attach.next = tail;
        tail.prev = attach;

        enterAt = enterAt.next; // or enterAt = attach;

        return attach;
    }

    public void removeNodeFromDLL(Node toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;

        if (toRemove == enterAt) {
            // it can be that, the node which we are removing is the last node. i.e the node
            // at which place we attach new nodes
            // so in order to take care of it, we use the below while loop to find the new
            // insertion point
            while (enterAt != tail && enterAt == toRemove) {
                enterAt = enterAt.prev;
            }

        }
    }

    public int getHead() {
        // System.out.println("ans " + head.next.val);
        return head.next.val != 0 ? head.next.val : -1;
    }
}

// Data structure
class Node {
    int val;
    Node prev;
    Node next;

    public Node(int v) {
        val = v;
    }
}

/**
 * Your headUnique object will be instantiated and called as such: headUnique
 * obj = new headUnique(nums); int param_1 = obj.showheadUnique();
 * obj.add(value);
 */