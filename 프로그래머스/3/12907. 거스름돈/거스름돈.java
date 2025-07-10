import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int L = money.length;
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int i = 0; i < L; i++){
            int now = money[i];
            for(int j = now; j <= n; j++){
                dp[j] += dp[j-now];
            }
        }
        return dp[n];
    }
}