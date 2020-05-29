public class Edit_distance_72 {

    // done in Bioinformatics class - chapter 2
    public int minDistance(String word1, String word2) {
        int len_1 = word1.length();
        int len_2 = word2.length();

        if (len_1 == 0)
            return len_2;
        else if (len_2 == 0)
            return len_1;

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        // row = word1
        // col = word2
        int[][] dp = new int[len_1 + 1][len_2 + 1];

        // lets assume first character in both words to be #, so # wont match with any
        // words in word2 except first character
        // similarly hash wont match with any word in word1 except first character

        // e.g - word1 - #horse
        // - word2 - #ros

        // filling first row
        // with the indexes because thats many alphabets need to be deleted since # wont
        // match with any char in word2 except for the first
        for (int i = 0; i <= len_2; i++)
            dp[0][i] = i;

        // fill first column
        // same logic as above
        for (int j = 0; j <= len_1; j++)
            dp[j][0] = j;

        // now we fill rest of the dp array, i.e from [1, 1] to [len_1, len_2]
        // using DP relation
        // if char at i of word1 matches char at j in word 2 we take the diagonal entry
        // else we take the minimum of top, left and diagonal and add 1 to it
        // if(char[i] == char[j]) -> dp[i][j] = dp[i-1][j-1]
        // else dp[i][j] = min(top, left, diagonal) + 1

        for (int i = 1; i <= len_1; i++)
            for (int j = 1; j <= len_2; j++) {
                if (w1[i - 1] == w2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    // Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))
                    int min_of_3_directions = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                    dp[i][j] = 1 + min_of_3_directions;
                }
                // System.out.println("Value at Position - "+i+","+j+ " = "+dp[i][j]);
            }

        return dp[len_1][len_2];
    }

    // below code doesnt make use of 1 extra row and 1 extra column
    // done in Bioinformatics class - chapter 2
    public int minDistance_Orignal(String word1, String word2) {
        int len_1 = word1.length();
        int len_2 = word2.length();

        if (len_1 == 0)
            return len_2;
        else if (len_2 == 0)
            return len_1;

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        // row = word1
        // col = word2
        int[][] dp = new int[len_1][len_2];

        // first entry - i.e if first char in both words are same, then we dont need to
        // perform any action hence 0, else if they are different we need to replace
        // first character of word1 to match that of word2 hence 1
        dp[0][0] = w1[0] == w2[0] ? 0 : 1;
        boolean foundBefore = w1[0] == w2[0] ? true : false;

        // fill first column
        // same logic as above loop but now we compare first char of word2 to all chars
        // in word1
        for (int i = 1; i < len_1; i++) {
            if (foundBefore || w1[i] != w2[0])
                dp[i][0] = dp[i - 1][0] + 1;
            else if (w1[i] == w2[0]) {
                foundBefore = true;
                dp[i][0] = dp[i - 1][0];
            }
        }

        foundBefore = w1[0] == w2[0] ? true : false;
        // filling first row
        // if first alphabet of word1 matches jth character of word2 then we take the
        // left entry as it is else we add 1 to the left entry
        for (int j = 1; j < len_2; j++) {
            if (foundBefore || w1[0] != w2[j])
                dp[0][j] = dp[0][j - 1] + 1;
            else if (w1[0] == w2[j]) {
                foundBefore = true;
                dp[0][j] = dp[0][j - 1];
            }
        }

        // now we fill rest of the dp array, i.e from [1, 1] to [len_1, len_2]
        // using DP relation
        // if char at i of word1 matches char at j in word 2 we take the diagonal entry
        // else we take the minimum of top, left and diagonal and add 1 to it
        // if(char[i] == char[j]) -> dp[i][j] = dp[i-1][j-1]
        // else dp[i][j] = min(top, left, diagonal) + 1

        for (int i = 1; i < len_1; i++)
            for (int j = 1; j < len_2; j++) {
                if (w1[i] == w2[j])
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    // Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))
                    int min_of_3_directions = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                    dp[i][j] = 1 + min_of_3_directions;
                }
                // System.out.println("Value at Position - "+i+","+j+ " = "+dp[i][j]);
            }

        return dp[len_1 - 1][len_2 - 1];
    }

    public static void main(String[] args) {
        Edit_distance_72 obj = new Edit_distance_72();
        System.out.println("Expected result = 27" + ", Got result = "
                + obj.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
        System.out.println("Expected result = 5" + ", Got result = " + obj.minDistance("intention", "execution"));
        System.out.println("Expected result = 3" + ", Got result = " + obj.minDistance("horse", "ros"));
    }
}
