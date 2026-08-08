class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = number of characters from the END of word2
         * that can be matched as a subsequence of word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        /*
         * Now greedily build the answer.
         *
         * We can have at most ONE mismatch.
         */
        int[] ans = new int[m];

        int i = 0;
        j = 0;

        while (i < n && j < m) {

            // Case 1: characters already match.
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            } else {

                /*
                 * Case 2: use this index as our ONE mismatch.
                 *
                 * After taking i, we still need:
                 * m - j - 1 characters.
                 *
                 * dp[i + 1] tells us how many characters from
                 * the end of word2 can be matched after i.
                 */
                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;

                    // Move past the chosen mismatch.
                    i++;

                    break;
                }
            }

            i++;
        }

        /*
         * If we have not selected all characters,
         * match the remaining characters exactly.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        /*
         * If we could not construct word2, no valid sequence exists.
         */
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}