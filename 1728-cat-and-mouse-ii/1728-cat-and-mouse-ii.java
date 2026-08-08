class Solution {
    int rows, cols;
    int[][] grid;
    int foodCell;
    int catJumpMax, mouseJumpMax;
    int[] memo;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    // state encoding: mousePos * 64 * 8 * 2 + catPos * 8 * 2 + turnCount(capped) * 2 + turn
    // We'll use: mouse (0-63), cat(0-63), turn(0=mouse,1=cat), moveCount capped at 2*rows*cols (draw->cat wins)

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        rows = grid.length;
        cols = grid[0].length();
        this.grid = new int[rows][cols];
        int catPos = -1, mousePos = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = grid[i].charAt(j);
                int cell = i * cols + j;
                if (ch == '#') this.grid[i][j] = -1;
                else this.grid[i][j] = 0;
                if (ch == 'C') catPos = cell;
                else if (ch == 'M') mousePos = cell;
                else if (ch == 'F') foodCell = cell;
            }
        }
        catJumpMax = catJump;
        mouseJumpMax = mouseJump;

        int maxTurns = 2 * rows * cols; // upper bound before declaring draw (cat wins)
        int totalCells = rows * cols;
        // dp[mouse][cat][turn][moveCount] -> 0 unknown, 1 mouse win, 2 cat win
        // moveCount capped at maxTurns
        int[][][][] dp = new int[totalCells][totalCells][2][maxTurns + 1];

        return dfs(mousePos, catPos, 0, 0, maxTurns, dp) == 1;
    }

    private int dfs(int mouse, int cat, int turn, int moveCount, int maxTurns, int[][][][] dp) {
        if (moveCount >= maxTurns) return 2; // cat wins by draw rule
        if (mouse == cat) return 2; // cat catches mouse
        if (mouse == foodCell) return 1; // mouse wins
        if (cat == foodCell) return 2; // cat wins

        if (dp[mouse][cat][turn][moveCount] != 0) return dp[mouse][cat][turn][moveCount];

        dp[mouse][cat][turn][moveCount] = 2; // temp marker to avoid infinite recursion (treat as loss for mover)

        int result;
        if (turn == 0) {
            // mouse's turn: mouse wants result = 1
            result = 2;
            int mr = mouse / cols, mc = mouse % cols;
            // stay in place
            int stayRes = dfs(mouse, cat, 1, moveCount + 1, maxTurns, dp);
            if (stayRes == 1) result = 1;

            outer:
            for (int d = 0; d < 4 && result != 1; d++) {
                int r = mr, c = mc;
                for (int step = 1; step <= mouseJumpMax; step++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == -1) break;
                    r = nr; c = nc;
                    int newMouse = r * cols + c;
                    int res = dfs(newMouse, cat, 1, moveCount + 1, maxTurns, dp);
                    if (res == 1) { result = 1; break outer; }
                }
            }
        } else {
            // cat's turn: cat wants result = 2
            result = 1;
            int cr = cat / cols, cc = cat % cols;
            // stay in place
            int stayRes = dfs(mouse, cat, 0, moveCount + 1, maxTurns, dp);
            if (stayRes == 2) result = 2;

            outer2:
            for (int d = 0; d < 4 && result != 2; d++) {
                int r = cr, c = cc;
                for (int step = 1; step <= catJumpMax; step++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == -1) break;
                    r = nr; c = nc;
                    int newCat = r * cols + c;
                    int res = dfs(mouse, newCat, 0, moveCount + 1, maxTurns, dp);
                    if (res == 2) { result = 2; break outer2; }
                }
            }
        }

        dp[mouse][cat][turn][moveCount] = result;
        return result;
    }
}