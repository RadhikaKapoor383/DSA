import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] height = new int[m][n];

        int maxArea = 0;

        for (int i = 0; i < m; i++) {

            // Calculate consecutive 1s
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0) {
                        height[i][j] = 1;
                    } else {
                        height[i][j] = height[i - 1][j] + 1;
                    }
                }
            }

            // Copy current row and sort descending
            int[] curr = height[i].clone();

            Arrays.sort(curr);

            // Traverse from largest to smallest
            for (int j = n - 1; j >= 0; j--) {

                int h = curr[j];

                // Number of columns being used
                int width = n - j;

                maxArea = Math.max(maxArea, h * width);
            }
        }

        return maxArea;
    }
}