class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long lowerBound = 1; // Represents 10^(3k)
        long upperBound = 999; // Represents 10^(3(k+1)) - 1
        int k = 0; // Number of commas

        while (true) {
            // Adjust bounds for the current 'k'
            if (k > 0) { // For k=0, lowerBound is 1, upperBound is 999 already set.
                         // For k>0, lowerBound starts from 10^(3k)
                lowerBound = (long)Math.pow(10, 3L * k);
                // Handle potential overflow for upperBound if 10^(3(k+1)) is too large
                // For a long, max value is around 9 * 10^18.
                // 10^(3*(k+1)) - 1. Max k is 5, so 3*(5+1) = 18. 10^18 - 1 is fine.
                upperBound = (long)Math.pow(10, 3L * (k + 1)) - 1;
            }

            // If lowerBound itself exceeds n, then we won't find any more numbers.
            if (lowerBound > n) {
                break;
            }

            // Calculate the actual range for numbers with 'k' commas within [1, n]
            long effectiveLower = Math.max(1, lowerBound);
            long effectiveUpper = Math.min(n, upperBound);

            // If the effective range is valid, add to total commas
            if (effectiveLower <= effectiveUpper) {
                totalCommas += (effectiveUpper - effectiveLower + 1) * k;
            }
            
            // Increment k for the next iteration
            k++;

            // Break if upperBound is already very large, to prevent overflow issues in Math.pow
            // or if we've already covered beyond n.
            // A more robust way to check: if lowerBound for next k would overflow or exceed n significantly
            // 10^(3 * (k+1)) needs to be checked carefully.
            if (lowerBound > (Long.MAX_VALUE / 1000) ) { // If lowerBound is already large, next lowerBound (lowerBound * 1000) might overflow.
                 break;
            }
        }
        return totalCommas;
    }
}