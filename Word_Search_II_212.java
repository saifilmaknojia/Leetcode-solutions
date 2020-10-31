import java.util.*;

public class Word_Search_II_212 {
    List<String> result = new ArrayList<>();
    int row;
    int col;
    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    Trie head;

    public List<String> findWords(char[][] board, String[] words) {
        row = board.length;
        col = board[0].length;
        if (row == 0)
            return new ArrayList<>();

        // construct the trie
        head = new Trie();
        // insert the words
        for (String w : words) {
            head.insert(w);
        }

        // now check the grid
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                backtrackDFS(board, i, j, head.top);
            }
        }
        return result;
    }

    private void backtrackDFS(char[][] grid, int r, int c, TrieNode root) {
        if (r < 0 || r >= row || c < 0 || c >= col)
            return;

        char curr_char = grid[r][c];

        // this means we have already used this letter
        if (curr_char == '-')
            return;

        TrieNode node_present = head.searchCharacter(curr_char, root);
        // the current character is not present in Trie, so we return
        if (node_present == null)
            return;

        grid[r][c] = '-';
        // formed += curr_char;
        // System.out.println(formed);

        // we have reached the end of the word in Trie, i.e. - it is present in board
        // also, we mark isLast as null, because we have found the word and added it in
        // our result list, so if we find the same word again we dont need to add it,
        // this way by marking isLast as null, we dont need to make use of set
        if (node_present.isLast != null) {
            result.add(node_present.isLast);
            node_present.isLast = null;
        }

        for (int[] dir : dirs) {
            int new_row = r + dir[0];
            int new_col = c + dir[1];

            backtrackDFS(grid, new_row, new_col, node_present);
        }

        grid[r][c] = curr_char;

        return;
    }

    public static void main(String[] args) {
        Word_Search_II_212 obj = new Word_Search_II_212();
        System.out.println(
                "Test case: board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, words = ['oath','pea','eat','rain'], Expected result = ['eat','oath'], Got result = "
                        + obj.findWords(new char[][] { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' },
                                { 'i', 'h', 'k', 'r' }, { 'i', 'f', 'l', 'v' } },
                                new String[] { "oath", "pea", "eat", "rain" }));
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

        TrieNode root = top;
        for (char x : word_arr) {
            TrieNode[] tree = root.tree;
            int curr_alp = x - 'a';
            if (tree[curr_alp] == null)
                tree[curr_alp] = new TrieNode();

            root = tree[curr_alp];
        }

        // we directly store the word at the last character in Trie, e.g - word = "dog",
        // at level "g" -> word dog will be stored, this way we dont need to form the
        // word while traversing the Trie, like we did in the 22ms submission
        root.isLast = word;
    }

    /**
     * Returns TrieNode the idx at curr_char is not null
     */
    public TrieNode searchCharacter(char first, TrieNode curr) {
        TrieNode[] tree = curr.tree;
        // System.out.println(first);
        int curr_alp = first - 'a';
        if (tree[curr_alp] == null)
            return null;

        return tree[curr_alp];
    }
}

class TrieNode {
    TrieNode[] tree;
    String isLast;

    public TrieNode() {
        tree = new TrieNode[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */