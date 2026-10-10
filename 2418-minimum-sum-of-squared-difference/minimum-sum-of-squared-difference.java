class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long)k1 + k2;
        int[] diff = new int[n];
        long sum = 0;
        int maxD = 0;
        for(int i=0;i<n;i++){
            int d = Math.abs(nums1[i]-nums2[i]);
            diff[i] = d;
            sum +=d;
            if(d>maxD) maxD = d;
        }
        if(sum <= k) return 0;
        long[] freq = new long[maxD +1];
        for(int d:diff)freq[d]++;
        for(int d=maxD;d>=1&&k>0;d--){
            if(freq[d]==0) continue;
            if(k>=freq[d]){
                k-=freq[d];
                freq[d-1]+=freq[d];
                freq[d]=0;
            }else{
                freq[d-1]+=k;
                freq[d]-=k;
                k=0;
                break;
            }
        }
        long ans =0;
        for(int d=0;d<=maxD;d++){
            if(freq[d]!=0){
                ans+=freq[d]*(long)d*d;
            }
        }
        return ans;
        
    }
}