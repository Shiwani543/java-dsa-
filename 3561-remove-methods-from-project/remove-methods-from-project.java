class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods
        boolean[] suspicious = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(k);
        suspicious[k] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int next : graph[node]) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    stack.push(next);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious one
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // Remove suspicious methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}