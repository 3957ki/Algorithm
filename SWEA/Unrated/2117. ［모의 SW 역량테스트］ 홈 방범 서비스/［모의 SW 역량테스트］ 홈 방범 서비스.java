import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N*3+10][N*3+10];
			visited = new boolean[N*3+10][N*3+10];
			
			for(int i = N; i < N*2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = N; j < N*2; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			DFS(1);
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static void DFS(int K) {
		if(K == N+2) return;
		int cost;
		int cnt;
		
		for(int i = N; i < N*2; i++) {
			for(int j = N; j < N*2; j++) {
				cnt = 0;
				cost = K*K + (K-1)*(K-1);
				for(int k = i-K; k <= i+K; k++) {
					for(int l = j-K; l <= j+K; l++) {
						if(Math.abs(k-i) + Math.abs(l-j) >= K) continue;
						visited[k][l] = true;
//						for(int u = 0; u < N*3; u++) {
//							for(int o = 0; o < N*3; o++) {
//								System.out.print(visited[u][o] ?  '■':'□');
//							}
//							System.out.println();
//						}
//						System.out.println();
						if(map[k][l] == 1) {
							cost -= M;
							cnt++;
						}
					}
				}
				if(cost <= 0) {
					answer = Math.max(answer, cnt);
				}
				
			}
		}
		DFS(K+1);
	}
}