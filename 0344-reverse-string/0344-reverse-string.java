class Solution {
    public void reverseString(char[] s) {
        int top=0;
        int end = s.length-1;
        
        while(top<=end){
            char temp = s[top];
            s[top] = s[end];
            s[end] = temp;
            top++;
            end--;
        }
        
    }
}