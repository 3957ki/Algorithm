import java.util.*;

class Solution {
    public int solution(int[][] land) {
        
        int N = land.length;
        int M = land[0].length;
        
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        int[] sum = new int[M];
                
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    // BFS
                    queue.add(new Node(i, j));
                    visited[i][j] = true;
                    int cnt = 0;
                    
                    Set<Integer> set = new HashSet<>();
                    
                    while(!queue.isEmpty()){
                        cnt++;
                        Node now = queue.poll();
                        set.add(now.x);
                        
                        for(int d = 0; d < 4; d++){
                            int y = now.y + dy[d];
                            int x = now.x + dx[d];
                            
                            if(y < 0 || y >= N || x < 0 || x >= M || visited[y][x] || land[y][x] == 0) continue;
                            visited[y][x] = true;
                            queue.add(new Node(y, x));
                        }
                    }
                    
                    for(int idx : set){
                        sum[idx] += cnt;
                    }
                }
            }
        }
        
        int answer = 0;
        for(int val : sum){
            answer = Math.max(answer, val);
        }
        
        return answer;
    }
    
    static class Node{
        int y, x;
        
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}