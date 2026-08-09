class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M] = maximum stones current player can get
        // starting from index i with current M
        int[][] dp = new int[n][n + 1];

        for (int i = n - 1; i >= 0; i--) {

            for (int M = 1; M <= n; M++) {

                // Can take all remaining piles
                if (2 * M >= n - i) {
                    dp[i][M] = suffix[i];
                    continue;
                }

                int best = 0;

                // Try taking X piles
                for (int X = 1; X <= 2 * M && i + X <= n; X++) {

                    int nextM = Math.max(M, X);

                    // Stones current player can get
                    int current = suffix[i]
                            - dp[i + X][nextM];

                    best = Math.max(best, current);
                }

                dp[i][M] = best;
            }
        }

        return dp[0][1];
    }
}