import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 0;
		while(true) {
			t++;
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
			boolean[][] visited = new boolean[N][N];
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], INF);
			}
//			시작점 0, 0
			dist[0][0] = map[0][0];
			pq.add(new Node(0, 0, map[0][0]));
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				if(visited[now.y][now.x]) continue;
				visited[now.y][now.x] = true;
				
				for(int d = 0; d < 4; d++) {
					int y = now.y + dy[d];
					int x = now.x + dx[d];
					if(x < 0 || x >= N || y < 0 || y >= N) continue;
					if(dist[y][x] > dist[now.y][now.x] + map[y][x]) {
						dist[y][x] = dist[now.y][now.x] + map[y][x];
						pq.add(new Node(y, x, dist[y][x]));
					}
				}
			}
			
			sb.append("Problem ").append(t).append(':').append(' ').append(dist[N-1][N-1]).append('\n');
		}
		
		System.out.println(sb);
	}

	static class Node{
		int y, x, w;

		public Node(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
		
	}
}