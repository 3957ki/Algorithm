import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] map;
	static int N, M, answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				DFS(0, i, j, map[i][j], visited);
				visited[i][j] = false;
				special(i, j);
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void special(int i, int j) {
		int sum = map[i][j];
		for(int d = 0; d < 4; d++) {
			int y = i + dy[d];
			int x = j + dx[d];
			if(x < 0 || x >= M || y < 0 || y >= N) continue;
			sum+=map[y][x];
		}
		
		for(int d = 0; d < 4; d++) {
			int y = i + dy[d];
			int x = j + dx[d];
			if(x < 0 || x >= M || y < 0 || y >= N ) {
				answer = Math.max(answer, sum);
				continue;
			}
			answer = Math.max(answer, sum-map[y][x]);
		}
		
	}

	static void DFS(int cnt, int i, int j, int sum, boolean[][] visited) {
		if(cnt == 3) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int y = i + dy[d];
			int x = j + dx[d];
			if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x]) continue;
			visited[i][j] = true;
			DFS(cnt+1, y, x, sum+map[y][x], visited);
			visited[i][j] = false;
		}
	}
	
}