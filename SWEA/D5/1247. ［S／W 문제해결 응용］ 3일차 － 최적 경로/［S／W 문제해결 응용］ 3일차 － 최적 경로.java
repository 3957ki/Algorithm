import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, answer;
	static int[][] edge;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[][] node = new int[N+2][2];		//node별 좌표
			edge = new int[N+2][N+2];	//node간 거리
			
//			시작노드 0
			node[0][0] = Integer.parseInt(st.nextToken());
			node[0][1] = Integer.parseInt(st.nextToken());
//			종료노드 N+1
			node[N+1][0] = Integer.parseInt(st.nextToken());
			node[N+1][1] = Integer.parseInt(st.nextToken());
			
			
			for(int i = 1; i <= N; i++) {
				node[i][0] = Integer.parseInt(st.nextToken());
				node[i][1] = Integer.parseInt(st.nextToken());
//				시작지점과의 거리
				edge[i][0] = edge[0][i] = Math.abs(node[i][0]-node[0][0])+Math.abs(node[i][1]-node[0][1]);
//				종료지점과의 거리
				edge[i][N+1] = edge[N+1][i] = Math.abs(node[i][0]-node[N+1][0])+Math.abs(node[i][1]-node[N+1][1]);
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = i+1; j <= N; j++) {
					edge[i][j] = edge[j][i] = Math.abs(node[i][0]-node[j][0])+Math.abs(node[i][1]-node[j][1]);
				}
			}
			
			boolean[] visited = new boolean[N+1];
			perm(0, visited, 0, 0);
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void perm(int cnt, boolean[] visited, int sum, int prev) {
		if(cnt == N) {
			answer = Math.min(sum+edge[prev][N+1], answer);
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm(cnt+1, visited, sum+edge[prev][i], i);
			visited[i] = false;
		}
	}
}