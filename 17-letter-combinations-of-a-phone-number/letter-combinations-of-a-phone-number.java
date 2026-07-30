class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return ans;

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), map);
        return ans;
    }

    private void backtrack(String digits, int index, StringBuilder curr, String[] map) {
        if (index == digits.length()) {
            ans.add(curr.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            curr.append(ch);
            backtrack(digits, index + 1, curr, map);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}