class Solution {
    /*
     * AND OPERATION WILL GIVE US THE CARRY, THAT CARRY WE NEED TO LEFT SHIFT SO AS
     * TO PLACE IT AT ITS PROPER POSITION OR OPERATION GIVES US THE SUM, the ans
     * will be stored in a and then we continue till b is not equal to 0
     */
    public int getSum(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}