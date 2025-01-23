import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][] mark;
    static boolean[][] visited;
    // D,U,L,R
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        mark = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                if(c == 'D') map[i][j] = 0;
                else if(c == 'U') map[i][j] = 1;
                else if(c == 'L') map[i][j] = 2;
                else if(c == 'R') map[i][j] = 3;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DFS(i, j);
            }
        }

        System.out.println(answer);
    }

    static int DFS(int i, int j) {

        if(mark[i][j] == 1) {
            answer++;
            return 1;
        }

        if(mark[i][j] == 2) return 2;

        if(visited[i][j]) return 2;
        visited[i][j] = true;

        int y = i + dy[map[i][j]];
        int x = j + dx[map[i][j]];

        if(y < 0 || y >= N || x < 0 || x >= M) {
            answer++;
            mark[i][j] = 1;
            return 1;
        }

        return mark[i][j] = DFS(y, x);
    }
}