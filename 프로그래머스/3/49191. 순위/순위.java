import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        List<Integer>[] win = new List[n+1];
        List<Integer>[] lose = new List[n+1];
        for(int i = 1; i <= n; i++){
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }
        
        for(int[] res : results){
            win[res[0]].add(res[1]);
            lose[res[1]].add(res[0]);
        }
        
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int i = 1; i <= n; i++){
            queue.add(i);
            boolean[] visited = new boolean[n+1];
            visited[i] = true;
            
            int cnt = 0;
            while(!queue.isEmpty()){
                int now = queue.poll();
                
                for(int next : win[now]){
                    if(visited[next]) continue;
                    visited[next] = true;
                    cnt++;
                    queue.add(next);
                }
            }
            
            queue.add(i);
            while(!queue.isEmpty()){
                int now = queue.poll();
                
                for(int next : lose[now]){
                    if(visited[next]) continue;
                    visited[next] = true;
                    cnt++;
                    queue.add(next);
                }
            }
            
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}