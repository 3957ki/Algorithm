import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int[][] arr;
    static int N, M, L;

    static int[][] dx = {{-1, 0 }, {-1, 0}, {1, 0}, {1, 0}};
    static int[][] dy = {{0, 1 }, {0, -1}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        L = N * M;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0);

        System.out.println(answer);
    }

    static void DFS(int now, int visited, int sum){
        if(now == L){
            answer = Math.max(answer, sum);
            return;
        }

        DFS(now + 1, visited, sum);

        if((visited & (1 << now)) > 0) return;

        visited |= 1 << now;
        int y = now / M;
        int x = now % M;

        int temp = arr[y][x] * 2;
        for(int i = 0; i < 4; i++){
            int ny1 = y + dy[i][0];
            int nx1 = x + dx[i][0];
            int ny2 = y + dy[i][1];
            int nx2 = x + dx[i][1];
            int n1 = 1 << (ny1*M+nx1);
            int n2 = 1 << (ny2*M+nx2);

            if(ny1 < 0 || ny2 < 0 || nx1 < 0 || nx2 < 0 ||
                ny1 >= N || nx1 >= M || ny2 >= N || nx2 >= M ||
                (visited & n1) > 0 ||
                (visited & n2) > 0
            ) continue;
            DFS(now + 1, visited | n1 | n2,
                sum + temp + arr[ny1][nx1] + arr[ny2][nx2]);
        }
    }
}