import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        int L = money.length;
        
        int[] dp1 = new int[L]; // 첫 번째 포함
        int[] dp2 = new int[L]; // 첫 번째 미포함
        
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        
        dp2[1] = money[1];
        
        for(int i = 2; i < L; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        int answer = Math.max(dp1[L-2], dp2[L-1]);
        return answer;
    }
    
}