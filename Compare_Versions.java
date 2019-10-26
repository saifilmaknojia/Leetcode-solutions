class compareVersions {
    public int compareVersion(String version1, String version2) {

        // split
        // convert string to integer, it will automatically remove leading zeroes/=, i.e
        // if - 0007 then Integer.parseInt will return 7
        // compare two values and process accordingly

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int j = 0;
        while (j < v1.length && j < v2.length) {
            int a = Integer.parseInt(v1[j]);
            int b = Integer.parseInt(v2[j]);
            if (a > b)
                return 1;
            else if (a < b)
                return -1;

            j++;
        }

        if (v1.length == v2.length)
            return 0;

        if (j == v1.length) {
            while (j < v2.length)
                if (Integer.parseInt(v2[j++]) > 0)
                    return -1;

        } else {
            while (j < v1.length)
                if (Integer.parseInt(v1[j++]) > 0)
                    return 1;
        }

        return 0;
    }

    /*
     * SHORTER VERSION public int compareVersion(String version1, String version2) {
     * String[] str1 = version1.split("\\."); String[] str2 = version2.split("\\.");
     * 
     * for(int i = 0; i < Math.max(str1.length, str2.length); i++){ int num1 = i <
     * str1.length? Integer.parseInt(str1[i]) : 0; int num2 = i < str2.length?
     * Integer.parseInt(str2[i]) : 0;
     * 
     * if(num1 < num2) return -1; else if(num1 > num2) return 1; } return 0; }
     */
}