import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        
        int N = board.length;
        int M = board[0].length;
        
        int[][] dp = new int[N+1][M+1];
        
        for(int[] query : skill){
            int diff = query[0] == 1 ? -query[5] : query[5];
            
            dp[query[1]][query[2]] += diff;
            dp[query[3]+1][query[2]] -= diff;
            dp[query[1]][query[4]+1] -= diff;
            dp[query[3]+1][query[4]+1] += diff;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                dp[i][j+1] += dp[i][j];
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                dp[i+1][j] += dp[i][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] + dp[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
}