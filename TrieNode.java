class Trie {

    /** Initialize your data structure here. */
    TrieNode top;

    public Trie() {
        top = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] word_arr = word.toCharArray();

        TrieNode root = top;
        for (char x : word_arr) {
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';
            if (tree[curr_alp] == null)
                tree[curr_alp] = new TrieNode();

            root = tree[curr_alp];
        }
        root.isLast = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] search_arr = word.toCharArray();

        TrieNode root = top;

        for (char x : search_arr) {
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';
            if (tree[curr_alp] == null)
                return false;
            else
                root = tree[curr_alp];
        }
        return root.isLast;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] search_arr = prefix.toCharArray();

        TrieNode root = top;

        for (char x : search_arr) {
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';
            if (tree[curr_alp] == null)
                return false;
            else
                root = tree[curr_alp];
        }
        return true;
    }
}

public class TrieNode {
    TrieNode[] tree;
    boolean isLast;

    public TrieNode() {
        tree = new TrieNode[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */