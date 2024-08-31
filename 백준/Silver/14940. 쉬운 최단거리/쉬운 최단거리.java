import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int start_y = 0;
		int start_x = 0;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;
				if(map[i][j] == 2) {
					start_y = i;
					start_x = j;
				}
			}
		}

		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N][M];

		int dst = 0;
		map[start_y][start_x] = 0;
		visited[start_y][start_x] = true;
		queue.add(new Node(start_y, start_x));

		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			
			while(L-- > 0) {
				Node node = queue.poll();
				for(int d = 0; d < 4; d++) {
					int y = node.y + dy[d];
					int x = node.x + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x] || map[y][x] == 0) continue;
					map[y][x] = dst;
					visited[y][x] = true;
					queue.add(new Node(y, x));
				}
			}
		}

		for(int[] a : map) {
			for(int num : a) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}