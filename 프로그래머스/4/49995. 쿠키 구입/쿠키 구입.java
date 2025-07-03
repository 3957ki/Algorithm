class Solution {
    public int solution(int[] cookie) {
        
        int L = cookie.length;
        int sum = 0;
        for(int i : cookie){
            sum += i;
        }
        
        sum /= 2;
        
        int answer = 0;
        
        for(int i = 1; i < L; i++){
            int left = cookie[i-1];
            int right = cookie[i];
            
            int l = i-1;
            int r = i;
            while(left <= sum && right <= sum){
                if(left == right){
                    answer = Math.max(answer, left);
                    if(++r < L){
                        right += cookie[r];
                    }
                    else break;
                }
                else if(left > right) {
                    if(++r < L){
                        right += cookie[r];
                    }
                    else break;
                }
                else{
                    if(--l >= 0){
                        left += cookie[l];
                    }
                    else break;
                }
            }
        }
        
        return answer;
    }
}