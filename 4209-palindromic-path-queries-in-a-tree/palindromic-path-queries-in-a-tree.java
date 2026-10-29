class Solution {
    int LOG=17,timer=0;
    List<Integer>[] adj;
    int[][] up;
    int[] depth,tin,tout,basePref;
    static class BIT{
        int n;int[] b;
        BIT(int n){this.n=n;b=new int[n+2];}
        void add(int i,int v){for(;i<=n;i+=i&-i)b[i]^=v;}
    void rangeXor(int l, int r, int v) { add(l + 1, v); add(r + 2, v); }
        int get(int i) { int r = 0; for (int x = i + 1; x > 0; x -= x & -x) r ^= b[x]; return r; }
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        int d = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) if (((d >> k) & 1) == 1) a = up[k][a];
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; k--) if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
        return up[0][a];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) { adj[e[0]].add(e[1]); adj[e[1]].add(e[0]); }

        up = new int[LOG][n];
        depth = new int[n]; tin = new int[n]; tout = new int[n]; basePref = new int[n];
        char[] cur = s.toCharArray();

        Deque<int[]> st = new ArrayDeque<>();
        st.push(new int[]{0, -1, 0});
        while (!st.isEmpty()) {
            int[] t = st.pop();
            int u = t[0], p = t[1], state = t[2];
            if (state == 0) {
                tin[u] = timer++;
                up[0][u] = (p == -1 ? u : p);
                depth[u] = (p == -1 ? 0 : depth[p] + 1);
                int m = 1 << (cur[u] - 'a');
                basePref[u] = (p == -1 ? m : basePref[p] ^ m);
                st.push(new int[]{u, p, 1});
                for (int i = adj[u].size() - 1; i >= 0; i--) {
                    int v = adj[u].get(i);
                    if (v != p) st.push(new int[]{v, u, 0});
                }
            } else {
                tout[u] = timer - 1;
            }
        }
        for (int k = 1; k < LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k-1][up[k-1][v]];

        BIT bit = new BIT(n + 2);
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                if (cur[u] != c) {
                    int delta = (1 << (cur[u] - 'a')) ^ (1 << (c - 'a'));
                    bit.rangeXor(tin[u], tout[u], delta);
                    cur[u] = c;
                }
            } else { // query
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int l = lca(u, v);
                int pu = basePref[u] ^ bit.get(tin[u]);
                int pv = basePref[v] ^ bit.get(tin[v]);
                int mask = pu ^ pv ^ (1 << (cur[l] - 'a'));
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
}