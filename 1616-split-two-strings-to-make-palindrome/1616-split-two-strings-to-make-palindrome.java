class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return checkHelper(a, b) || checkHelper(b, a);
    }
    
    // checks if a[prefix] + b[suffix] can form a palindrome
    private boolean checkHelper(String a, String b) {
        int left = 0, right = a.length() - 1;
        
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        
        if (left >= right) {
            return true; // fully matched, entire thing is already palindromic
        }
        
        // remaining middle segment [left, right] must be a palindrome,
        // either within a or within b
        return isPalindrome(a, left, right) || isPalindrome(b, left, right);
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}