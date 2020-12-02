
import java.util.*;

class Search_Suggestions_System_1268 {

    // easy to understand, basic solution
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            List<String> list = new ArrayList<>();
            int index = Arrays.binarySearch(products, searchWord.substring(0, i));
            if (index < 0) {
                index = Math.abs(index) - 1;
            }
            for (int j = index; j < index + 3 && j < products.length; j++) {
                if (products[j].startsWith(searchWord.substring(0, i)))
                    list.add(products[j]);
            }
            res.add(list);
        }
        return res;
    }

    // SOLUTION USING TRIE
    // Trie data structure and priority queue
    public List<List<String>> suggestedProducts_Trie(String[] products, String searchWord) {
        // add all products in trie
        int p_length = products.length;
        List<List<String>> result = new ArrayList<>();

        Trie ds = new Trie();
        ds.insert(searchWord);

        Arrays.sort(products);
        // HashMap<String, List<String>> map = new HashMap<>();
        String prefix = "";
        int prefix_length = 0;

        int startSearchidx = 0;
        int idx = 0;
        int count;

        for (char c : searchWord.toCharArray()) {
            prefix += c;
            prefix_length++;

            startSearchidx = Arrays.binarySearch(products, startSearchidx, p_length, prefix);
            if (startSearchidx < 0)
                startSearchidx = (-1 * startSearchidx) - 1; // converting negative insertion point to positive, we
                                                            // can also simply make use of ~ which gives us 2s
                                                            // complement, i.e if startSearchIdx is -4, ~-4 will
                                                            // give 3, which is what we get from (-1 *
                                                            // startSearchidx) - 1

            idx = startSearchidx;

            if (idx < 0)
                idx = (-1 * idx) - 1;

            if (idx < p_length) {
                count = 3;
                while (idx < p_length && count > 0) {
                    ds.search(products[idx], prefix_length);
                    idx++;
                    count--;
                }
            }
        }

        TrieNode head = ds.top;
        TrieNode[] tree = null;
        int curr_alp = -1;
        for (char c : searchWord.toCharArray()) {
            tree = head.tree;
            curr_alp = c - 'a';

            result.add(tree[curr_alp].searchList);

            head = tree[curr_alp];
        }
        return result;

    }
}

class Trie {

    /** Initialize your data structure here. */
    TrieNode top;

    public Trie() {
        top = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] word_arr = word.toCharArray();
        // System.out.println(word);
        TrieNode root = top;
        for (char x : word_arr) {
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';
            if (tree[curr_alp] == null)
                tree[curr_alp] = new TrieNode();

            root = tree[curr_alp];
        }
    }

    /** Returns if the word is in the trie. */
    public void search(String word, int length) {
        TrieNode root = top;

        for (int i = 0; i < length; i++) {
            char x = word.charAt(i);
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';

            if (tree[curr_alp] == null)
                return;
            else
                root = tree[curr_alp];
        }
        root.addWord(word);
    }
}

class TrieNode {
    TrieNode[] tree;
    List<String> searchList;

    public TrieNode() {
        tree = new TrieNode[26];
        searchList = new ArrayList<>();
    }

    public void addWord(String word) {
        searchList.add(word);
    }
}