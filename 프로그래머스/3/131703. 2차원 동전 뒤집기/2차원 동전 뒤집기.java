import java.util.*;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int N = beginning.length;
        int M = beginning[0].length;
        
        // 뒤집어야하면 1
        int[][] map = new int[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M ;j++){
                map[i][j] = beginning[i][j] ^ target[i][j];
            }
        }
        
        // 1행과 다르면 뒤집기
        int Rcnt = 0;
        int Ccnt = 0;
        for(int i = 1; i < N; i++){
            int num = map[i][0] ^ map[0][0];
            for(int j = 0; j < M; j++){
                if((map[i][j] ^ map[0][j]) != num) return -1;
            }
            Rcnt += num;
        }
        
        for(int j = 0; j < M; j++){
            if(map[0][j] == 1) Ccnt++;
        }
        
        return Math.min(Rcnt + Ccnt, N - Rcnt + M - Ccnt);
    }
}