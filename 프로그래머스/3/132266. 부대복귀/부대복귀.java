import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        List<Integer>[] edges = new List[n+1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            edges[road[0]].add(road[1]);
            edges[road[1]].add(road[0]);
        }
        
        int M = sources.length;
        int[] answer = new int[M];
        int[] idx = new int[n+1];
        Arrays.fill(answer, -1);
        Arrays.fill(idx, -1);
        
        for(int i = 0; i < M; i++){
            idx[sources[i]] = i;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        queue.add(destination);
        visited[destination] = true;

        int dst = 0;
        while(!queue.isEmpty()){
            int L = queue.size();
            while(L-- > 0){
                int now = queue.poll();
                
                if(idx[now] >= 0){
                    answer[idx[now]] = dst;
                }

                for(int next : edges[now]){
                    if(visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                }
            }
            dst++;
        }
        
        return answer;
    }
}