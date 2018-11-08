class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)
            return "";
        Arrays.sort(strs);
       // int i=1;
        String shortest=strs[0];
        
        //so, we sort the array first, now we know, the largest prefix possible is upto the
        //length of first element and the largest element would be last
        //so, we compare first and last string till we dont get a match
        //if there is no match we reduce the prefix length
        
        //BELOW is abdef in abdgklmsn means checking the start
        //eg. shortest=abdef and last element = abdgklmsn
        //first we check is abdef in abdgklmsn --> NO, then we remove last character from shortest 
        // next we check is abde in abdgklmsn --> NO, we remove last character from shortest
        //now we check is abd in abdgklmsn, YES then we return the result
        while(strs[strs.length-1].indexOf(shortest)!=0)
        {
            shortest=shortest.substring(0, shortest.length()-1);
        }
        
        return shortest;
        
    }
}