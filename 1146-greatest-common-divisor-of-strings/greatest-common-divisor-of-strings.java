class Solution {
    public String gcdOfStrings(String str1, String str2) {

        // If they cannot be formed by repeating the same base string
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Find GCD of the two lengths
        int len = gcd(str1.length(), str2.length());

        // The prefix of GCD length is the answer
        return str1.substring(0, len);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}