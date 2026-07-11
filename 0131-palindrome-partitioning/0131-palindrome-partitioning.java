import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                    (end - start < 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                }
            }
        }

        List<String> path = new ArrayList<>();
        backtrack(s, 0, dp, path, result);
        return result;
    }

    private void backtrack(String s, int start, boolean[][] dp,
                            List<String> path, List<List<String>> result) {
        int n = s.length();
        if (start == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < n; end++) {
            if (dp[start][end]) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, dp, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}