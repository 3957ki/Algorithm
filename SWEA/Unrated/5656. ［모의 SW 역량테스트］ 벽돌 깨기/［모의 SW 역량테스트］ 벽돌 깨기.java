import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int C, M, N, answer;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            
            map = new int[N][M];
            answer = Integer.MAX_VALUE;
            
            int total = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) total++;
                }
            }
            
            DFS(0, total, map);
            
            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void DFS(int cnt, int remains, int[][] currentMap) {
        if(remains == 0) {
            answer = 0;
            return;
        }
        
        if(cnt == C) {
            answer = Math.min(answer, remains);
            return;
        }
        
        for(int j = 0; j < M; j++) {
            int[][] nextMap = copyMap(currentMap);
            int broken = 0;
            
            for(int i = 0; i < N; i++) {
                if(nextMap[i][j] > 0) {
                    broken = breakBricks(i, j, nextMap);
                    applyGravity(nextMap);
                    DFS(cnt + 1, remains - broken, nextMap);
                    break;
                }
            }
        }
    }
    
    static int breakBricks(int y, int x, int[][] map) {
        Queue<Node> queue = new ArrayDeque<>();
        int broken = 0;
        
        if(map[y][x] > 0) {
            queue.offer(new Node(y, x, map[y][x]));
            map[y][x] = 0;
            broken++;
        }
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int ny = current.y;
                int nx = current.x;
                
                for(int k = 1; k < current.value; k++) {
                    ny += dy[d];
                    nx += dx[d];
                    
                    if(ny < 0 || ny >= N || nx < 0 || nx >= M) break;
                    if(map[ny][nx] > 0) {
                        queue.offer(new Node(ny, nx, map[ny][nx]));
                        map[ny][nx] = 0;
                        broken++;
                    }
                }
            }
        }
        
        return broken;
    }
    
    static void applyGravity(int[][] map) {
        for(int j = 0; j < M; j++) {
            int idx = N - 1;
            for(int i = N - 1; i >= 0; i--) {
                if(map[i][j] > 0) {
                    if(idx != i) {
                        map[idx][j] = map[i][j];
                        map[i][j] = 0;
                    }
                    idx--;
                }
            }
        }
    }
    
    static int[][] copyMap(int[][] original) {
        int[][] copy = new int[N][M];
        for(int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(original[i], M);
        }
        return copy;
    }
    
    static class Node {
        int y, x, value;
        
        Node(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
}