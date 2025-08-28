import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        
        List<Integer>[] edges = new List[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] p : path){
            edges[p[0]].add(p[1]);
            edges[p[1]].add(p[0]);
        }
        
        int[] ahead = new int[n];
        List<Integer>[] check = new List[n];
        for(int i = 0; i < n; i++){
            check[i] = new ArrayList<>();
        }
        
        for(int[] o : order){
            ahead[o[1]]++;
            check[o[0]].add(o[1]);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        if(ahead[0] > 0) return false;
        queue.add(0);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            int now = queue.poll();
            
            for(int next : edges[now]){
                if(visited[next]) continue;
                queue.add(next);
                visited[next] = true;
                ahead[next]++;
            }
        }
        
        visited = new boolean[n];
        visited[0] = true;
        queue.add(0);
        
        int cnt = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();
            cnt++;
            
            for(int num : check[now]) {
                if(--ahead[num] == 0){
                    visited[num] = true;
                    queue.add(num);
                }
            }

            for(int next : edges[now]){
                if(visited[next]) continue;
                if(--ahead[next] == 0){
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        
        System.out.println(cnt);
        
        return cnt == n;
    }
    
}