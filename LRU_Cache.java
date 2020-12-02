
import java.util.HashMap;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int k, int v) {
        key = k;
        value = v;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    public DoublyLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public Node addFirst(int updateFirst, int value) {
        Node newFirst = new Node(updateFirst, value);

        Node prevFirst = head.next;
        // Node prevLast = tail.prev;

        head.next = newFirst;
        newFirst.prev = head;

        newFirst.next = prevFirst;
        prevFirst.prev = newFirst;
        return newFirst;
    }

    public void remove(Node n) {
        n.next.prev = n.prev;
        n.prev.next = n.next;
    }

    public int removeLast() {
        Node removing = tail.prev;
        Node secondLast = tail.prev.prev;
        secondLast.next = tail;
        tail.prev = secondLast;

        return removing.key;
    }
}

class LRUCache {
    DoublyLinkedList dll;
    HashMap<Integer, Node> tracker = new HashMap<>();
    int maxCapacity;
    int count = 0;

    public LRUCache(int capacity) {
        maxCapacity = capacity;
        dll = new DoublyLinkedList();
    }

    public int get(int key) {
        if (!tracker.containsKey(key))
            return -1;

        Node prev = tracker.get(key);
        dll.remove(prev);
        tracker.remove(key);
        Node newFirst = dll.addFirst(key, prev.value);
        tracker.put(key, newFirst);

        return tracker.get(key).value;
    }

    public void put(int key, int value) {
        if (tracker.containsKey(key)) {
            Node eliminate = tracker.get(key);
            tracker.remove(key);
            dll.remove(eliminate);
            Node update = dll.addFirst(key, value);
            tracker.put(key, update);
            return;
        }
        count++;
        Node newFirst = dll.addFirst(key, value);
        tracker.put(key, newFirst);
        if (count > maxCapacity) {
            int remove = dll.removeLast();
            // System.out.println(remove);
            tracker.remove(remove);
            count--;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */