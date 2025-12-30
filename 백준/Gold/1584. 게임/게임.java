import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[501][501];

        // 위험
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int minX = Math.min(x1, x2);
            int minY = Math.min(y1, y2);
            int maxX = Math.max(x1, x2);
            int maxY = Math.max(y1, y2);

            for(int j = minY; j <= maxY; j++){
                for(int k = minX; k <= maxX; k++){
                    map[j][k] = 1;
                }
            }
        }

        // 죽음
        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int minX = Math.min(x1, x2);
            int minY = Math.min(y1, y2);
            int maxX = Math.max(x1, x2);
            int maxY = Math.max(y1, y2);

            for(int j = minY; j <= maxY; j++){
                for(int k = minX; k <= maxX; k++){
                    map[j][k] = -1;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[][] visited = new boolean[501][501];
        int[][] dst = new int[501][501];

        for(int i = 0; i <= 500; i++){
            for(int j = 0; j <= 500; j++){
                dst[i][j] = Integer.MAX_VALUE;
            }
        }

        dst[0][0] = 0;
        pq.add(new Node(0, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.y][now.x]) continue;
            visited[now.y][now.x] = true;

            if(now.y == 500 && now.x == 500) break;

            for(int d = 0; d < 4; d++){
                int Y = now.y + dy[d];
                int X = now.x + dx[d];

                if(Y < 0 || Y > 500 || X < 0 || X > 500 || visited[Y][X] || map[Y][X] < 0) continue;
                if(dst[Y][X] > now.cost + map[Y][X]){
                    dst[Y][X] = now.cost + map[Y][X];
                    pq.add(new Node(Y, X, dst[Y][X]));
                }
            }
        }

        System.out.println(dst[500][500] == Integer.MAX_VALUE ? -1 : dst[500][500]);
    }

    static class Node{
        int y, x, cost;

        Node(int y, int x, int cost){
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}