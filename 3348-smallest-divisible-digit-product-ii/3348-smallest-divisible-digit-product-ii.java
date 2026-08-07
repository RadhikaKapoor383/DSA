class Solution {
    private static final int[][] EXP = {
        {0,0,0,0}, {0,0,0,0}, {1,0,0,0}, {0,1,0,0}, {2,0,0,0},
        {0,0,1,0}, {1,1,0,0}, {0,0,0,1}, {3,0,0,0}, {0,2,0,0},
    };
    private int[][] fTable;

    public String smallestNumber(String num, long t) {
        long tt = t;
        int a = 0, b = 0, c = 0, d = 0;
        while (tt % 2 == 0) { tt /= 2; a++; }
        while (tt % 3 == 0) { tt /= 3; b++; }
        while (tt % 5 == 0) { tt /= 5; c++; }
        while (tt % 7 == 0) { tt /= 7; d++; }
        if (tt != 1) return "-1";

        fTable = new int[a + 1][b + 1];
        for (int ra = 0; ra <= a; ra++) {
            for (int rb = 0; rb <= b; rb++) {
                int best = Integer.MAX_VALUE;
                int mn = Math.min(ra, rb);
                for (int x6 = 0; x6 <= mn; x6++) {
                    int a2 = ra - x6, b2 = rb - x6;
                    int x8 = (a2 + 2) / 3, x9 = (b2 + 1) / 2;
                    best = Math.min(best, x6 + x8 + x9);
                }
                fTable[ra][rb] = best;
            }
        }

        int n = num.length();
        int z = n;
        for (int i = 0; i < n; i++) if (num.charAt(i) == '0') { z = i; break; }

        int[] pa = new int[z + 1], pb = new int[z + 1], pc = new int[z + 1], pd = new int[z + 1];
        for (int i = 1; i <= z; i++) {
            int dig = num.charAt(i - 1) - '0';
            pa[i] = pa[i-1] + EXP[dig][0];
            pb[i] = pb[i-1] + EXP[dig][1];
            pc[i] = pc[i-1] + EXP[dig][2];
            pd[i] = pd[i-1] + EXP[dig][3];
        }

        if (z == n && pa[n] >= a && pb[n] >= b && pc[n] >= c && pd[n] >= d) return num;

        int startP = (z == n) ? n - 1 : z;
        for (int p = startP; p >= 0; p--) {
            int baseA = pa[p], baseB = pb[p], baseC = pc[p], baseD = pd[p];
            int low = num.charAt(p) - '0';
            for (int dp = low + 1; dp <= 9; dp++) {
                int A2 = baseA + EXP[dp][0], B2 = baseB + EXP[dp][1];
                int C2 = baseC + EXP[dp][2], D2 = baseD + EXP[dp][3];
                int ra = Math.max(0, a - A2), rb = Math.max(0, b - B2);
                int rc = Math.max(0, c - C2), rd = Math.max(0, d - D2);
                int k = n - 1 - p;
                if (feasible(ra, rb, rc, rd, k)) {
                    char[] res = new char[n];
                    for (int i = 0; i < p; i++) res[i] = num.charAt(i);
                    res[p] = (char) ('0' + dp);
                    fillSuffix(res, p + 1, k, ra, rb, rc, rd);
                    return new String(res);
                }
            }
        }

        int Lmin = c + d + fTable[a][b];
        int L = Math.max(n + 1, Lmin);
        char[] res = new char[L];
        fillSuffix(res, 0, L, a, b, c, d);
        return new String(res);
    }

    private boolean feasible(int ra, int rb, int rc, int rd, int k) {
        if (rc + rd > k) return false;
        return (k - rc - rd) >= fTable[ra][rb];
    }

    private void fillSuffix(char[] res, int offset, int k, int ra, int rb, int rc, int rd) {
        for (int pos = 0; pos < k; pos++) {
            int remaining = k - 1 - pos;
            for (int dg = 1; dg <= 9; dg++) {
                int a2 = Math.max(0, ra - EXP[dg][0]), b2 = Math.max(0, rb - EXP[dg][1]);
                int c2 = Math.max(0, rc - EXP[dg][2]), d2 = Math.max(0, rd - EXP[dg][3]);
                if (feasible(a2, b2, c2, d2, remaining)) {
                    res[offset + pos] = (char) ('0' + dg);
                    ra = a2; rb = b2; rc = c2; rd = d2;
                    break;
                }
            }
        }
    }
}