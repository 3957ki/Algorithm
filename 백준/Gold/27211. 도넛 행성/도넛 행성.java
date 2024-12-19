import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] || map[i][j] == 1) continue;
                bfs(i, j);
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void bfs(int y, int x){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x));
        visited[y][x] = true;
        while(!queue.isEmpty()){
            Node now = queue.poll();

            for(int d = 0; d < 4; d++){
                int ny = (now.y + dy[d])%N;
                int nx = (now.x + dx[d])%M;

                ny = ny < 0 ? N-1 : ny;
                nx = nx < 0 ? M-1 : nx;

                if(visited[ny][nx] || map[ny][nx] == 1) continue;
                visited[ny][nx] = true;
                queue.add(new Node(ny, nx));
            }
        }
    }

    static class Node{
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}