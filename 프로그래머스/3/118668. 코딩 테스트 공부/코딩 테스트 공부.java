import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int N = problems.length;
        
        int maxA = alp;
        int maxC = cop;
        
        for(int i = 0; i < N; i++){
            maxA = Math.max(maxA, problems[i][0]);
            maxC = Math.max(maxC, problems[i][1]);
        }
        
        int[][] dp = new int[maxA+1][maxC+1];
        for(int i = 0; i <= maxA; i++){
            Arrays.fill(dp[i], 200);
        }
        
        dp[alp][cop] = 0;
        
        for(int i = alp; i <= maxA; i++){
            for(int j = cop; j <= maxC; j++){
                int ni = Math.min(i+1, maxA);
                int nj = Math.min(j+1, maxC);
                
                // 알고력
                dp[ni][j] = Math.min(dp[ni][j], dp[i][j] + 1);
                
                // 코딩력
                dp[i][nj] = Math.min(dp[i][nj], dp[i][j] + 1);
                
                // 문제 풀어보기
                for(int[] problem : problems){
                    if(problem[0] > i || problem[1] > j) continue;
                    ni = Math.min(i+problem[2], maxA);
                    nj = Math.min(j+problem[3], maxC);
                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + problem[4]);
                }
            }
        }
        
        return dp[maxA][maxC];
    }
}