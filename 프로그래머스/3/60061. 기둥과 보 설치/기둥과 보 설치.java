import java.util.*;

class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        List<int[]> answer = new ArrayList<>();
        
        boolean[][] h = new boolean[n+3][n+3];
        boolean[][] w = new boolean[n+3][n+3];
        
        for(int[] now : build_frame){
            int x = now[0]+1;
            int y = now[1]+1;
            
            // 기둥
            if(now[2] == 0){
                // 삭제
                if(now[3] == 0){
                    if(!(h[x][y+1] && !w[x-1][y+1] && !w[x][y+1])){
                        if(!((w[x-1][y+1] && !h[x-1][y] && !(w[x-2][y+1] && w[x][y+1])) || (w[x][y+1] && !h[x+1][y] && !(w[x-1][y+1] && w[x+1][y+1])))){
                            h[x][y] = false;
                        }
                    }
                }
                
                // 설치
                else{
                    if(y == 1 || h[x][y-1] || w[x][y] || w[x-1][y]){
                        h[x][y] = true;
                    }
                }
            }
            
            // 보
            else{
                // 삭제
                if(now[3] == 0){
                    if(!(h[x][y] && !h[x][y-1] && !w[x-1][y]) && !(h[x+1][y] && !w[x+1][y] && !h[x+1][y-1]) && !(w[x-1][y] && !h[x-1][y-1] && !h[x][y-1]) && !(w[x+1][y] && !h[x+1][y-1] && !h[x+2][y-1])){
                        w[x][y] = false;
                    }
                }
                
                // 설치
                else{
                    if((w[x-1][y] && w[x+1][y]) || (h[x][y-1] || h[x+1][y-1])){
                        w[x][y] = true;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n+1; i++){
            for(int j = 1; j <= n+1; j++){
                if(h[i][j]) answer.add(new int[]{i-1, j-1, 0});
                if(w[i][j]) answer.add(new int[]{i-1, j-1, 1});
            }
        }
        
        int L = answer.size();
        int[][] res = new int[L][3];
        for(int i = 0; i < L; i++){
            res[i] = answer.get(i);
        }
        
        return res;
    }
}