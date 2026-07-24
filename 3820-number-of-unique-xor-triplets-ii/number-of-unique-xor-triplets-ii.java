class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAXX = 2048;

        boolean[] present = new boolean[MAXX];
        for (int num : nums) {
            present[num] = true;
        }

        boolean[][] dp = new boolean[4][MAXX];
        dp[0][0] = true;

        for (int t = 0; t < 3; t++) {
            for (int x = 0; x < MAXX; x++) {
                if (!dp[t][x]) continue;

                for (int v = 0; v < MAXX; v++) {
                    if (present[v]) {
                        dp[t + 1][x ^ v] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (boolean possible : dp[3]) {
            if (possible) ans++;
        }

        return ans;
    }
}