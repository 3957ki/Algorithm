import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] edges = new int[N+1][N+1];

		for(int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++){
				edges[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int k = 1; k <= N; k++){
			for(int i = 1; i <= N; i++){
				for(int j = 1; j <= N; j++){
					if(i == j) continue;
					if(edges[i][k] == 0 || edges[k][j] == 0) continue;
					edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
				}
			}
		}

		for(int i = 1; i <= M; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			if(edges[start][end] <= time) sb.append("Enjoy other party").append('\n');
			else sb.append("Stay here").append('\n');
		}

		System.out.println(sb);
    }
}