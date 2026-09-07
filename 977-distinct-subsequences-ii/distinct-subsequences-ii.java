class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long total = 1;

            for (long x : dp) {
                total = (total + x) % MOD;
            }

            dp[index] = total;
        }

        long answer = 0;

        for (long x : dp) {
            answer = (answer + x) % MOD;
        }

        return (int) answer;
    }
}