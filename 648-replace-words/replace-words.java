class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root = new TrieNode();

    public String replaceWords(List<String> dictionary, String sentence) {

        // 1. Build Trie
        for (String word : dictionary) {
            insert(word);
        }

        // 2. Process sentence
        String[] words = sentence.split(" ");
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
            if (ans.length() > 0) {
                ans.append(" ");
            }

            ans.append(findRoot(word));
        }

        return ans.toString();
    }

    void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.child[index] == null) {
                curr.child[index] = new TrieNode();
            }

            curr = curr.child[index];
        }

        curr.isEnd = true;
    }

    String findRoot(String word) {
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {

            int index = c - 'a';

            if (curr.child[index] == null) {
                return word;
            }

            prefix.append(c);
            curr = curr.child[index];

            if (curr.isEnd) {
                return prefix.toString();
            }
        }

        return word;
    }
}