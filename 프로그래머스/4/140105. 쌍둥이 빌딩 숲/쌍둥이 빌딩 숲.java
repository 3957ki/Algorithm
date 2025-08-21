class Solution {
    
    static final int MOD = 1000000007;
    
    public int solution(int n, int count) {
        
        int[][] dp = new int[n+1][count+1];
        dp[1][1] = 1;
        
        for(int i = 2; i <= n; i++){
            int L = Math.min(i, count);
            for(int j = 1; j <= L; j++){
                dp[i][j] = (int)((dp[i-1][j-1] + ((long)(dp[i-1][j])*(2*i-2)) % MOD) % MOD);
            }
        }
        
        return dp[n][count];
    }
}