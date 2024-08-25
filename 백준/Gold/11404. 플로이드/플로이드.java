import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] edge = new int[N+1][N+1];
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(edge[start][end] == 0) {
				edge[start][end] = weight;
			}
			else {
				edge[start][end] = Math.min(edge[start][end], weight);
			}
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(edge[i][k] == 0 || edge[k][j] == 0 || i == j) continue;
					if(edge[i][j] == 0) {
						edge[i][j] = edge[i][k]+edge[k][j];
					}
					else {
						edge[i][j] = Math.min(edge[i][j], edge[i][k]+edge[k][j]);
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				sb.append(edge[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}