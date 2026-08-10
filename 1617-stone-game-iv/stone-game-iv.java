class Solution {
    public boolean winnerSquareGame(int n) {

        // dp[i] = true if the current player can win
        // when there are i stones
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones means no move is possible, so current player loses.

        for (int i = 1; i <= n; i++) {

            // Try removing every possible square number
            for (int j = 1; j * j <= i; j++) {

                int remaining = i - j * j;

                // If opponent reaches a losing position,
                // current player can win.
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}