class Valid_mountain_array {
    public boolean validMountainArray(int[] A) {
        // boolean result = false;
        int len = A.length;

        if (len < 3 || A == null)
            return false;

        int high = 0;

        // Approach
        // first walk through the array. walk up operation that is go till u can rise
        // i.e next element is greater than u
        // i.e go till we rise i.e till first i whose value is greater than i+1
        // this means all i-1 satisfy mountain property
        // then we traverse down, i.e all should satisy A[i] > A[i+1] > ... > A[B.length
        // - 1] property

        for (int i = 0; i < len - 1; i++) {
            if (A[i] > A[i + 1]) {
                high = i;
                break;
            }
        }
        // System.out.println(high);
        if (high == 0 || high == len - 1)
            return false;

        for (int i = high; i < len - 1; i++) {
            if (A[i] <= A[i + 1])
                return false;
        }

        return true;

    }
}
