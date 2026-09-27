public class Solution{
    public String reverseParentheses(String s){
        while(s.contains("(")){
            int left = s.lastIndexOf('(');
            int right = s.indexOf(')',left);
            String inside = s.substring(left+1,right);
            String reversed = new StringBuilder(inside).reverse().toString();
            s=s.substring(0,left)+reversed +s.substring(right+1);

        }
        return s;

    }
}