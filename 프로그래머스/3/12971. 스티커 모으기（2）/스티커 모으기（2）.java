import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        
        int N = sticker.length;
        
        if(N == 1) return sticker[0];
        if(N == 2) return Math.max(sticker[0], sticker[1]);
        
        int[][] dp = new int[N][2];
        dp[0][0] = sticker[0];
        dp[1][0] = dp[0][0];
        dp[1][1] = sticker[1];
        
        for(int i = 2; i < N-1; i++){
            int prev1 = (i + N - 1) % N;
            int prev2 = (i + N - 2) % N;
            
            dp[i][0] = Math.max(dp[prev2][0] + sticker[i], dp[prev1][0]);
            dp[i][1] = Math.max(dp[prev2][1] + sticker[i], dp[prev1][1]);
        }
        
        int prev1 = (2 * N - 2) % N;
        int prev2 = (2 * N - 3) % N;
        dp[N-1][0] = dp[prev1][0];
        dp[N-1][1] = Math.max(dp[prev2][1] + sticker[N-1], dp[prev1][1]);
        
        return Math.max(dp[N-1][0], dp[N-1][1]);
    }
}