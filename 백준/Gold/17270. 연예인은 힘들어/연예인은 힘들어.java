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

		long[][] edges = new long[N+1][N+1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			if(edges[start][end] == 0) edges[start][end] = weight;
			else edges[start][end] = Math.min(edges[start][end], weight);
			edges[end][start] = edges[start][end];
		}

		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(edges[i][k] == 0 || edges[k][j] == 0) continue;
					if(edges[i][j] == 0) edges[i][j] = edges[i][k]+edges[k][j];
					else if(edges[i][j] > edges[i][k]+edges[k][j]) edges[i][j] = edges[i][k]+edges[k][j];
				}
			}
		}
		
		long min = Long.MAX_VALUE;
		
		for(int k = 1; k <= N; k++) {
			if(k == J || k == S) continue;
			if(edges[J][k] == 0 || edges[S][k] == 0) continue;
			min = Math.min(edges[S][k]+edges[k][J], min);
		}
		
		int answer = -1;
		long minJ = Long.MAX_VALUE;
		for(int k = 1; k <= N; k++) {
			if(k == J || k == S) continue;
			if(edges[J][k] == 0 || edges[S][k] == 0) continue;
			if(edges[S][k]+edges[k][J] != min) continue;
			if(edges[J][k] > edges[S][k]) continue;
			if(edges[J][k] >= minJ) continue;
			minJ = edges[J][k];
			answer = k;
		}

		System.out.println(answer);
	}
}