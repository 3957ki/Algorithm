import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        List<Integer>[] edges = new List[n+1];
        
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] now : edge){
            edges[now[0]].add(now[1]);
            edges[now[1]].add(now[0]);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        
        queue.add(1);
        visited[1] = true;
        
        int answer = 0;
        
        while(!queue.isEmpty()){
            int L = queue.size();
            answer = L;
            
            while(L-- > 0){
                int now = queue.poll();
                
                for(int next : edges[now]){
                    if(visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        
        return answer;
    }
}