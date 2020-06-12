public class Is_Subsequence_392 {
    public boolean isSubsequence(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();

        if (s_len > t_len)
            return false;

        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();

        int s_idx = 0;

        for (int i = 0; i < t_len; i++) {
            if (s_idx == s_len)
                return true;

            if (t_arr[i] == s_arr[s_idx]) {
                s_idx++;
            }
        }
        return s_idx == s_len;
    }

    public static void main(String[] args) {
        Is_Subsequence_392 obj = new Is_Subsequence_392();
        System.out.println(
                "Test case: s=abc, t=ahbgdc, Expected result=true, Got result= " + obj.isSubsequence("abc", "ahbgdc"));
        System.out.println(
                "Test case: s=axc, t=ahbgdc, Expected result=false, Got result= " + obj.isSubsequence("axc", "ahbgdc"));
    }
}