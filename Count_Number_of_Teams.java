
class Count_Number_of_Teams {
    // O(N^2) solution - DP
    public int numTeams(int[] rating) {
        if (rating.length < 3)
            return 0;
        int len = rating.length;

        int[] smaller = new int[len];
        int[] bigger = new int[len];

        for (int s = 1; s < len - 1; s++) {
            int ct = 0;
            for (int t = s + 1; t < len; t++) {
                if (rating[t] > rating[s])
                    ct++;
            }
            bigger[s] = ct;
            int numberOfElementsOnTheRight = len - 1 - s;
            smaller[s] = numberOfElementsOnTheRight - ct;
        }

        int result = 0;
        for (int i = 0; i < len - 1; i++)
            for (int j = i + 1; j < len; j++) {
                // System.out.println(smaller[j]);
                if (rating[j] > rating[i] && bigger[j] > 0)
                    result += bigger[j];
                else if (rating[j] < rating[i] && smaller[j] > 0)
                    result += smaller[j];
            }
        return result;
    }

    // O(N^3) solution
    public int numTeams_N3(int[] rating) {
        int result = 0;
        if (rating.length < 3)
            return 0;
        int len = rating.length;
        for (int i = 0; i < len - 2; i++)
            for (int j = i + 1; j < len - 1; j++)
                for (int k = j + 1; k < len; k++) {
                    if (rating[j] > rating[i] && rating[k] > rating[j])
                        result++;
                    else if (rating[i] > rating[j] && rating[j] > rating[k])
                        result++;
                }
        return result;
    }
}