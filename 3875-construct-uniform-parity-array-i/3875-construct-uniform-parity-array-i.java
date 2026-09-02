class Solution {
    public boolean uniformArray(int[] nums1) {

        int oddCount = 0, evenCount = 0;
        
        for (int num : nums1) {
            if (num % 2 == 0) evenCount++;
            else oddCount++;
        }
        
        boolean canAllEven = (oddCount == 0 || oddCount >= 2);
        boolean canAllOdd = (evenCount == 0 || oddCount >= 1);
        
        return canAllEven || canAllOdd;
    }
}