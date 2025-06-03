import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        
        // 깊이
        int N = triangle.length;
        
        int[][] dp = new int[N][];
        for(int i = 0; i < N; i++){
            dp[i] = new int[i+1];
        }
        
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < N; i++){
            for(int j = 0; j < i; j++){
                dp[i][j] = Math.max(triangle[i][j] + dp[i-1][j], dp[i][j]);
                dp[i][j+1] = Math.max(triangle[i][j+1] + dp[i-1][j], dp[i][j+1]);
            }
        }
        
        int answer = 0;
        for(int i = 0; i < N; i++){
            answer = Math.max(dp[N-1][i], answer);
        }
        return answer;
    }
}