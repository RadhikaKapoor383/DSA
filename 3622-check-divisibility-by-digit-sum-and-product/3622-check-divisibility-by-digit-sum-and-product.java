class Solution {
    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int num = n;
        
        while (num > 0) {
            int digit = num % 10;
            digitSum += digit;
            digitProduct *= digit;
            num /= 10;
        }
        
        int total = digitSum + digitProduct;
        return n % total == 0;
    }
}