import java.util.*;

class Solution {
    private char[] dc = {'d', 'l', 'r', 'u'};
    private int[] dy = {1, 0, 0, -1};
    private int[] dx = {0, -1, 1, 0};
    private int n, m, r, c;
    private String answer = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder();
        int L = Math.abs(x - r) + Math.abs(y - c);
        if(L > k || L % 2 != k % 2){
            return "impossible";
        }
        
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        
        return DFS(x, y, 0, k, sb) ? answer : "impossible";
    }
    
    private boolean DFS(int i, int j, int sum, int k, StringBuilder sb){
        if(sum == k){
            if(i == r && j == c) {
                answer = sb.toString();
                return true;
            }
            return false;
        }
        
        if(Math.abs(i - r) + Math.abs(j - c) > k - sum) return false;
        
        for(int d = 0; d < 4; d++){
            int y = i + dy[d];
            int x = j + dx[d];
            
            if(y <= 0 || y > n || x <= 0 || x > m) continue;
            sb.append(dc[d]);
            if(DFS(y, x, sum+1, k, sb)) return true;
            sb.deleteCharAt(sum);
        }
        
        return false;
    }
}