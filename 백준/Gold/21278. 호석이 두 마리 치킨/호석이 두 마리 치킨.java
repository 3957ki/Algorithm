import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접행렬
        int[][] edges = new int[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edges[start][end] = 1;
            edges[end][start] = 1;
        }

        // 플로이드
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(i == j) continue;
                    if(edges[i][k] == 0 || edges[k][j] == 0) continue;
                    if(edges[i][j] == 0) edges[i][j] = edges[i][k] + edges[k][j];
                    else edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }

        int pos1 = 0;
        int pos2 = 0;
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                int sum = 0;
                for (int k = 1; k <= N; k++) {
                    sum += Math.min(edges[i][k]*2, edges[j][k]*2);
                }
                if (sum < answer) {
                    answer = sum;
                    pos1 = i;
                    pos2 = j;
                }
            }
        }

        System.out.println(pos1+" "+pos2+" "+answer);

    }
}