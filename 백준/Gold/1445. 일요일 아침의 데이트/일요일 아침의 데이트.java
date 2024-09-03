import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static final int INF = 10000;
	static final long INFF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int start_y = 0;
		int start_x = 0;
		int end_y = 0;
		int end_x = 0;
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				char c = str.charAt(j);
				if(c == 'g') {
					map[i][j] = INF;
				}
				if(c == 'S') {
					start_y = i;
					start_x = j;
				}
				if(c == 'F') {
					end_y = i;
					end_x = j;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != INF) continue;
				for(int d = 0; d < 4; d++) {
					int y = i + dy[d];
					int x = j + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || map[y][x] == INF) continue;
					map[y][x] = 1;
				}
			}
		}
		map[start_y][start_x] = 0;
		map[end_y][end_x] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (int)(o1.w - o2.w));
		boolean[][] visited = new boolean[N][M];
		long[][] dist = new long[N][M];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INFF);
		}
		dist[start_y][start_x] = 0;
		pq.add(new Node(start_y, start_x, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.y][now.x]) continue;
			visited[now.y][now.x] = true;
			
			for(int d = 0; d < 4; d++) {
				int y = now.y + dy[d];
				int x = now.x + dx[d];
				if(x < 0 || x >= M || y < 0 || y >= N) continue;
				if(dist[y][x] > dist[now.y][now.x] + map[y][x]) {
					dist[y][x] = dist[now.y][now.x] + map[y][x];
					pq.add(new Node(y, x, dist[y][x]));
				}
			}
		}
		long garbage = dist[end_y][end_x]/INF;
		long litter = dist[end_y][end_x]%INF;
		System.out.println(garbage + " " +litter);
	}

	static class Node{
		int y, x;
		long w;

		public Node(int y, int x, long w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
		
	}
}