class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int temp = n;
        n = m;
        m = temp;
        
        int[][][] dp = new int[n+2][m+2][2];
        dp[1][1][0] = 1;
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(cityMap[i-1][j-1] == 0){
                    dp[i+1][j][0] = (dp[i+1][j][0] + dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i][j+1][1] = (dp[i][j+1][1] + dp[i][j][0] + dp[i][j][1]) % MOD;
                }
                else if(cityMap[i-1][j-1] == 2){
                    dp[i][j+1][1] = (dp[i][j+1][1] + dp[i][j][1]) % MOD;
                    dp[i+1][j][0] = (dp[i+1][j][0] + dp[i][j][0]) % MOD;
                }
                else{
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                }
            }
        }
        
        return (dp[n][m][0] + dp[n][m][1]) % MOD;
    }
}