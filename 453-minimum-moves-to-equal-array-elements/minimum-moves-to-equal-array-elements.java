class Solution {
    public int minMoves(int[] nums) {
        int mn = Integer.MAX_VALUE;
        for (int x : nums) mn = Math.min(mn, x);
        int ans = 0;
        for (int x : nums) ans += x - mn;
        return ans;
    }
}