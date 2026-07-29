class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        if (countWays(half, halfLen) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt, int total) {
        long ans = 1;
        int rem = total;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0)
                continue;

            ans = multiplyCap(ans, comb(rem, cnt[i]));
            if (ans >= LIMIT)
                return LIMIT;

            rem -= cnt[i];
        }

        return ans;
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            if (res > LIMIT / num)
                return LIMIT;

            res *= num;

            if (den > 1)
                res /= den;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }

    private long multiplyCap(long a, long b) {
        if (a >= LIMIT || b >= LIMIT)
            return LIMIT;

        if (a > LIMIT / b)
            return LIMIT;

        long x = a * b;
        return Math.min(x, LIMIT);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}