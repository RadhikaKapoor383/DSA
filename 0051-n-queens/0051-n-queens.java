class Solution {
    private List<List<String>> result;
    private boolean[] cols;
    private boolean[] diag1; 
    private boolean[] diag2; 
    private int[] queenCol; 
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        result = new ArrayList<>();
        cols = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];
        queenCol = new int[n];

        backtrack(0);
        return result;
    }

    private void backtrack(int row) {
        if (row == n) {
            result.add(buildBoard());
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue; 
            }

            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            queenCol[row] = col;

            backtrack(row + 1);

            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    private List<String> buildBoard() {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                sb.append(col == queenCol[row] ? 'Q' : '.');
            }
            board.add(sb.toString());
        }
        return board;
    }
}