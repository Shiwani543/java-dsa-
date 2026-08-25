import java.util.*;

class Solution {

    private int m;
    private Map<Integer, Integer> map;
    private Random random;

    public Solution(int n, int[] blacklist) {

        m = n - blacklist.length;

        map = new HashMap<>();
        random = new Random();

        Set<Integer> black = new HashSet<>();

        // Store blacklisted numbers
        for (int num : blacklist) {
            black.add(num);
        }

        // Numbers in [m, n-1] that are NOT blacklisted
        int last = n - 1;

        for (int b : blacklist) {

            // Only remap blacklist numbers inside [0, m-1]
            if (b < m) {

                while (black.contains(last)) {
                    last--;
                }

                map.put(b, last);
                last--;
            }
        }
    }

    public int pick() {

        int x = random.nextInt(m);

        if (map.containsKey(x)) {
            return map.get(x);
        }

        return x;
    }
}