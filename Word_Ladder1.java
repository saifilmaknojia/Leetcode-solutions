import java.util.*;

// https://www.youtube.com/watch?v=PeyYhb8lJJU
// Bidirectional Search - https://leetcode.com/problems/word-ladder/discuss/281178/java-9ms-bidirection-bfs-solution-which-beats-100-with-detailed-explanation

class Word_Ladder1 {
    // Steps - We follow a BFS approach
    // Overview - We start by replacing, every character of beginWord by a - z, for
    // each updated combination we then we check if any of the updated word is
    // present in the set of wordlist, if yes, we add it to the queue
    // we follow the approach until our queue is empty
    // we add one level to our result everytime we have completed the iteration for
    // current number of elements in the queue

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // set for wordlist
        HashSet<String> tracker = new HashSet<>(wordList);
        if (!tracker.contains(endWord))
            return 0;

        // for BFS we use a queue datastructure
        Queue<String> traverse = new LinkedList<>();
        // add the begin word, because thats what we start with
        traverse.offer(beginWord);
        // our result variable
        int result = 0;

        // we go on till our queue is not empty
        while (!traverse.isEmpty()) {
            // we take size of the current queue, and those number of elements are in our
            // current level
            int currQueueSize = traverse.size();

            // here for each element in the current level, we poll them and perform the
            // required operations
            for (int i = 0; i < currQueueSize; i++) {
                String work_on = traverse.poll();
                // we check if the current string which we polled, is it our endWord, if yes, we
                // return result + 1;
                if (work_on.equals(endWord))
                    return result + 1;
                // if its not, we need to try different combinations for this word, and see if
                // it can form some new String which is present in the wordlist
                for (int j = 0; j < work_on.length(); j++) {
                    // we need to change single index character at a time, so everytime we need to
                    // get the orignal String back and then change index - 0, 1, 2 and so on
                    char[] work_on_array = work_on.toCharArray();
                    // for each index we check different a - z values in the following for loop if
                    // its in the wordlist, if it is we add them in the queue and remove it from the
                    // wordlist(since we dont want to go again to this wordlist and also since we
                    // have added it to the queue for future processing)
                    for (char c = 'a'; c <= 'z'; c++) {
                        work_on_array[j] = c;
                        String newCombination = new String(work_on_array);
                        // System.out.println(newCombination);
                        if (tracker.contains(newCombination)) {
                            traverse.add(newCombination);
                            tracker.remove(newCombination);
                        }
                    }
                }
            }

            // we increase the level here because we have finished the set of elements in
            // the queue for previous batch
            result++;
        }

        // if we are here then it means we were not able to reach teh endWord and return
        // 0
        return 0;
    }
}