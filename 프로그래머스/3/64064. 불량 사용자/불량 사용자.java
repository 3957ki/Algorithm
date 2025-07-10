import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        
        return DFS(user_id, banned_id, user_id.length, banned_id.length, 0, new String[banned_id.length], 0);
    }
    
    static int DFS(String[] user_id, String[] banned_id, int uL, int bL, int now, String[] arr, int cnt){
        if(cnt == bL){
            if(solve(arr, banned_id, bL, new boolean[bL], 0)) return 1;
            return 0;
        }
        
        int sum = 0;
        for(int i = now; i < uL; i++){
            arr[cnt] = user_id[i];
            sum += DFS(user_id, banned_id, uL, bL, i+1, arr, cnt+1);
        }
        
        return sum;
    }
    
    static boolean solve(String[] arr, String[] banned_id, int bL, boolean[] visited, int now){
        
        if(now == bL) {
            return true;
        }
        String user = arr[now];

        A: for(int j = 0; j < bL; j++){
            if(user.length() != banned_id[j].length() || visited[j]) continue;
            for(int k = 0; k < user.length(); k++){
                if(banned_id[j].charAt(k) == '*') continue;
                if(user.charAt(k) != banned_id[j].charAt(k)) continue A;
            }
            visited[j] = true;
            if(solve(arr, banned_id, bL, visited, now+1)) return true;
            visited[j] = false;
        }
        
        return false;
    }
}