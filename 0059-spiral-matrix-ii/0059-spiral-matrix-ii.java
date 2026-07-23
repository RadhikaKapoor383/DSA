class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {
            
            for (int i = left; i <= right; i++)
                ans[top][i] = num++;
            top++;

            for (int i = top; i <= bottom; i++)
                ans[i][right] = num++;
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    ans[bottom][i] = num++;
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    ans[i][left] = num++;
                left++;
            }
        }

        return ans;
    }
}