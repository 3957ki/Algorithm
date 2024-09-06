import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, edges[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			edges = new int[N+1][N+1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				edges[a][b] = 1;
			}
			
			for(int i = 1; i <= N; i++) {
				edges[i][0] = -1;
			}
			
			for(int i = 1; i <= N; i++) {
				if(edges[i][0] != -1) continue;
				bigDFS(i);
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					edges[0][j] += edges[i][j];
				}
			}
			
			int answer = 0;
			
			for(int i = 1; i <= N; i++) {
				if(edges[i][0]+edges[0][i] == N-1) answer++;
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void bigDFS(int cur) {
		
		for(int i = 1; i <= N; i++) {
			if(edges[cur][i] == 0) continue;
			if(edges[i][0] == -1) {
				bigDFS(i);
			}
			
			if(edges[i][0] > 0) {
				for(int j = 1; j <= N; j++) {
					if(edges[i][j] != 0) {
						edges[cur][j] = 1;
					}
				}
			}
			
		}
		
		edges[cur][0] = 0;
		for(int i = 1; i <=N; i++) {
			edges[cur][0] += edges[cur][i];
		}
	}
}
