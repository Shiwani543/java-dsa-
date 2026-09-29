class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 1. length m+n-1 must be even
        if ((m + n - 1) % 2 == 1) return false;
        // 2. start '(' end ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        // dp[i][j][b] = can reach (i,j) with balance b
        // max balance <= m+n
        boolean[][][] dp = new boolean[m][n][m + n];
        dp[0][0][1] = true; // grid[0][0] == '('

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int delta = (grid[i][j] == '(') ? 1 : -1;
                int rem = (m - 1 - i) + (n - 1 - j);

                for (int b = 0; b < m + n; b++) {
                    boolean fromTop = i > 0 && dp[i - 1][j][b];
                    boolean fromLeft = j > 0 && dp[i][j - 1][b];
                    if (!fromTop && !fromLeft) continue;

                    int nb = b + delta;
                    if (nb >= 0 && nb <= rem) {
                        dp[i][j][nb] = true;
                    }
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}