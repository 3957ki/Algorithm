import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        int[] count = new int[50001];
        
        for(int now : works){
            count[now]++;
        }
        
        long answer = 0;
        
        for(int i = 50000; i > 0; i--){
            while(count[i] > 0){
                count[i]--;
                count[i-1]++;
                if(--n == 0) {
                    for(int j = 1; j <= i; j++){
                        if(count[j] > 0){
                            answer += (long)j*j*count[j];
                        }
                    }
                    return answer;
                }
            }
        }
        
        return answer;
    }
}