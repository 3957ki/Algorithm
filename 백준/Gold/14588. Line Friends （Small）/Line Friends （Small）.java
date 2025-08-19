import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] edges = new int[N+1][N+1];

		for(int i = 1; i <= N; i++){
			Arrays.fill(edges[i], INF);
		}

		// left, right
		int[][] arr = new int[N+1][2];

		for(int i = 1; i <= N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			edges[i][i] = 0;
		}

		for(int i = 1; i <= N; i++){
			for(int j = i+1; j <= N; j++){
				if(arr[i][0] > arr[j][1] || arr[i][1] < arr[j][0]) continue;
				edges[i][j] = 1;
				edges[j][i] = 1;
			}
		}

		for(int k = 1; k <= N; k++){
			for(int i = 1; i <= N; i++){
				for(int j = 1; j <= N; j++){
					if(i == j) continue;
					if(edges[i][k] == INF || edges[k][j] == INF) continue;
					edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
				}
			}
		}

		int Q = Integer.parseInt(br.readLine());
		for(int i = 1; i <= Q; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			sb.append(edges[num1][num2] == INF ? -1 : edges[num1][num2]).append('\n');
		}

		System.out.println(sb);
    }
}