class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0)
            return 0;
        
        if(haystack.length()==0)
            return -1;
            
        
         // i =0;
        for(int i=0; i<=haystack.length()-needle.length();i++)
        {
            if(haystack.substring(i, i+needle.length()).equals(needle))
                return i;
        }
        return -1;
        
        
    }
}