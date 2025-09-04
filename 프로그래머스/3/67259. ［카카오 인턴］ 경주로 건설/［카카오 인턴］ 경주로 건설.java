import java.util.*;

class Solution {
    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};
    
    public int solution(int[][] board) {
        int N = board.length;
        boolean[][][] visited = new boolean[N][N][4];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(0, 0, 0, 0));
        pq.add(new Node(0, 0, 1, 0));
        
        int[][][] dst = new int[N][N][4];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(dst[i][j], Integer.MAX_VALUE);
            }
        }
        
        dst[0][0][0] = 0;
        dst[0][0][1] = 0;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            
            if(now.y == N-1 && now.x == N-1) return dst[N-1][N-1][now.dir];
            
            if(visited[now.y][now.x][now.dir]) continue;
            visited[now.y][now.x][now.dir] = true;
            
            for(int d = 0; d < 4; d++){
                int y = now.y + dy[d];
                int x = now.x + dx[d];
                
                if(y < 0 || y >= N || x < 0 || x >= N || visited[y][x][d] || board[y][x] == 1) continue;
                
                int c = now.dir == d ? 100 : 600;
                if(dst[y][x][d] > now.cost + c){
                    dst[y][x][d] = now.cost + c;
                    pq.add(new Node(y, x, d, dst[y][x][d]));
                }
            }
        }
        return 0;
    }
    
    private class Node{
        int y, x, dir, cost;
        
        Node(int y, int x, int dir, int cost){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
        
    }
}