class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, visited, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] nums, boolean[] visited, List<Integer> current) {

        // Base Case
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // Skip already used numbers
            if (visited[i])
                continue;

            // Choose
            visited[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, visited, current);

            // Undo (Backtrack)
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }
}