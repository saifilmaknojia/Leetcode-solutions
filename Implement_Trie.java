class TrieNode {
    TrieNode[] helper;
    int number;
    boolean isEnd;

    public TrieNode() {
        number = 26;
        helper = new TrieNode[26];
    }
}

class Trie {

    /** Initialize your data structure here. */
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0)
            return;

        TrieNode r = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (r.helper[curr - 'a'] == null)
                r.helper[curr - 'a'] = new TrieNode();

            r = r.helper[curr - 'a'];
        }
        r.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return true;

        TrieNode r = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (r.helper[curr - 'a'] == null)
                return false;

            r = r.helper[curr - 'a'];
        }

        return r.isEnd ? true : false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0)
            return true;

        TrieNode r = root;
        for (int i = 0; i < prefix.length(); i++) {
            char curr = prefix.charAt(i);
            if (r.helper[curr - 'a'] == null)
                return false;

            r = r.helper[curr - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */