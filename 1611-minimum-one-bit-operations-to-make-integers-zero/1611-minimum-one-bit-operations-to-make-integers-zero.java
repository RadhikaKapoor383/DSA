class Solution {
    public int minimumOneBitOperations(int n) {
        int result = n;
        result ^= (result >> 16);
        result ^= (result >> 8);
        result ^= (result >> 4);
        result ^= (result >> 2);
        result ^= (result >> 1);
        return result;
    }
}