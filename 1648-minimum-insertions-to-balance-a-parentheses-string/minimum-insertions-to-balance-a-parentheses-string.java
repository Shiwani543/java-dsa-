class Solution {
    public int minInsertions(String s) {
        int insertion = 0;
        int need = 0;
        for(char c:s.toCharArray()){
            if(c=='('){
                need +=2;
                if(need%2==1){
                    insertion++;
                    need--;
                }
            }else{
                need--;
                if(need == -1){
                    insertion++;
                    need =1;
                }
            }
        }
        return insertion + need;
    }
}