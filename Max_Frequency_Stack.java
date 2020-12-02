
import java.util.*;

class FreqStack {
    Map<Integer, LinkedList<Integer>> tracker;
    Map<Integer, Integer> count;
    Stack<Integer> max_count;

    public FreqStack() {
        tracker = new HashMap<>();
        count = new HashMap<>();
        max_count = new Stack<>();
    }

    public void push(int x) {
        int prev = count.getOrDefault(x, 0);
        count.put(x, prev + 1);
        if (!tracker.containsKey(prev + 1)) {
            LinkedList<Integer> curr = new LinkedList<>();
            curr.addFirst(x);
            tracker.put(prev + 1, curr);
        } else {
            LinkedList<Integer> old = tracker.get(prev + 1);
            old.addFirst(x);
            tracker.put(prev + 1, old);
        }

        if (max_count.isEmpty() || prev + 1 > max_count.peek())
            max_count.push(prev + 1);
    }

    public int pop() {
        int max_freq = max_count.peek();
        LinkedList<Integer> top_list = tracker.get(max_freq);
        int pop = top_list.removeFirst();
        if (top_list.size() == 0) {
            max_count.pop();
            tracker.remove(max_freq);
        }

        count.put(pop, count.get(pop) - 1);

        return pop;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such: FreqStack obj
 * = new FreqStack(); obj.push(x); int param_2 = obj.pop();
 */