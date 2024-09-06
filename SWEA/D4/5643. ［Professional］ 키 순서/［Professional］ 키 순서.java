import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(bigBFS(i) + smallBFS(i) == N-1) {
					cnt++;
				}
			}
			
			
			sb.append('#').append(t).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}
	
	static int bigBFS(int start) {
		int cnt = 0;
		
		Queue<Integer> queue = new ArrayDeque<>();	
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && edges[cur][i] != 0) {
					queue.add(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	static int smallBFS(int start) {
		int cnt = 0;
		
		Queue<Integer> queue = new ArrayDeque<>();	
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && edges[i][cur] != 0) {
					queue.add(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}