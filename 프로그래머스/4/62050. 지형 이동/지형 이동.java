import java.util.*;

class Solution {
    public int solution(int[][] land, int height) {
        
        int N = land.length;
        int M = land[0].length;
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N*M];
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        
        queue.add(0);
        visited[0] = true;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        
        int answer = 0;
        
        do{
            while(!pq.isEmpty()){
                Node now = pq.poll();
                if(visited[now.idx]) continue;
                visited[now.idx] = true;
                queue.add(now.idx);
                answer += now.cost;
                break;
            }
            while(!queue.isEmpty()){
                int now = queue.poll();
                int y = now / M;
                int x = now % M;

                for(int d = 0; d < 4; d++){
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    int idx = ny*M+nx;
                    if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[idx]) continue;
                    int cost = Math.abs(land[y][x] - land[ny][nx]);

                    if(cost > height){
                        pq.add(new Node(idx, cost));
                        continue;
                    }
                    visited[idx] = true;
                    queue.add(idx);
                }
            }
        } while(!pq.isEmpty());
        
        return answer;
    }
    
    static class Node{
        int idx, cost;
        
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
}