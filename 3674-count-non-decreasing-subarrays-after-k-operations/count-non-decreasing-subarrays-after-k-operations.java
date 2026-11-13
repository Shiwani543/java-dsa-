class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        // block starts, front = rightmost, back = leftmost
        // values nums[dq] are strictly increasing from back to front
        java.util.Deque<Integer> dq = new java.util.ArrayDeque<>();
        int j = n - 1;

        for (int i = n - 1; i >= 0; --i) {
            int num = nums[i];
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.offerLast(i);

            while (cost > k) {
                cost -= (long) nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }
            ans += (long) (j - i + 1);
        }
        return ans;
    }
}