class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find sum of the longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Find the smallest missing integer >= sum
        int x = sum;

        while (true) {
            boolean found = false;

            for (int num : nums) {
                if (num == x) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return x;
            }

            x++;
        }
    }
}