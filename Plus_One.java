class Solution {
    public int[] plusOne(int[] digits) {
    	// idea used
        // start from the last element in an array
        
        //below condition is we get empty array as an input we need to return array with value 1 as output
        if(digits.length==0)
        {
            return new int[]{1};
        }
    	boolean needIncrease=false;
    	for(int i=digits.length-1; i>=0; i--)
    	{
            //check if its less than 9, if yes, increment it and break
    		if(digits[i]<9)
    		{
    			digits[i]++;
    			break;
    		}
            // else check if its a 9 and its not the first element of the list, if its not first element
            // change its value to 8 and move backward
    		else if(i!=0 && digits[i]==9)
    		{
    			digits[i]=0;
    			// digits[i-1]++;	
    		}
            // this condition is used when we need to increment the array size, i.e - 99, 999 etc
    		else
    		{
    			needIncrease=true;
    		}
    	}
    	
    	if(needIncrease)
    	{
    		digits = new int[digits.length+1];
    		digits[0]=1;
    		for(int i=1;i<digits.length;i++)
    			digits[i]=0;
    	}
    	
    	return digits;
        
    }
}