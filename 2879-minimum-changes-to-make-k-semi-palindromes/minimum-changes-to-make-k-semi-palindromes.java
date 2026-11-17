class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000_000;

        // cost[l][r]
        int[][] cost = new int[n][n];
        char[] ch = s.toCharArray();

        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                int len = r - l + 1;
                if (len == 1) {
                    cost[l][r] = INF;
                    continue;
                }
                int best = INF;
                // try all divisors d < len
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;
                    int cur = 0;
                    for (int start = 0; start < d; start++) {
                        // collect indices: l+start, l+start+d, ...
                        // count mismatches for palindrome directly
                        // without building list
                        int left = l + start;
                        int right = l + start + ((len - 1 - start) / d) * d;
                        while (left < right) {
                            if (ch[left] != ch[right]) cur++;
                            left += d;
                            right -= d;
                        }
                        if (cur >= best) break; // prune
                    }
                    best = Math.min(best, cur);
                }
                cost[l][r] = best;
            }
        }

        // dp[i][t] : first i chars into t parts
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int t = 0; t <= k; t++) {
                dp[i][t] = INF;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int t = 1; t <= k && t <= i; t++) {
                for (int j = t - 1; j < i; j++) {
                    // last part is [j, i-1]
                    if (dp[j][t - 1] == INF) continue;
                    if (cost[j][i - 1] == INF) continue;
                    dp[i][t] = Math.min(dp[i][t], dp[j][t - 1] + cost[j][i - 1]);
                }
            }
        }

        return dp[n][k];
    }
}