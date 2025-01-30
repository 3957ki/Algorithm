import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		char[][] map = new char[H][W];
		List<Node> point = new ArrayList<>();

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'C') {
					point.add(new Node(i, j, 1));
				}
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
		int[][][] visited = new int[H][W][4];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		Node start = point.get(0);
		Node end = point.get(1);

		for (int d = 0; d < 4; d++) {
			int y = start.y + dy[d];
			int x = start.x + dx[d];

			if (y < 0 || y >= H || x < 0 || x >= W || map[y][x] == '*')
				continue;
			if (map[y][x] == 'C') {
				System.out.println(0);
				return;
			}
			pq.add(new Node(y, x, d, 0));
		}

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			for (int d = 0; d < 4; d++) {
				int y = now.y + dy[d];
				int x = now.x + dx[d];

				if (y < 0 || y >= H || x < 0 || x >= W || map[y][x] == '*')
					continue;

				// 방향이 다르면 개수 증가
				int nextCnt = now.cnt;
				if (now.dir != d)
					nextCnt++;

				// 거울 개수가 이상이면 패스
				if (visited[y][x][d] <= nextCnt)
					continue;
				visited[y][x][d] = nextCnt;
				pq.add(new Node(y, x, d, nextCnt));
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			answer = Math.min(visited[end.y][end.x][i], answer);
		}
		System.out.println(answer);
	}

	static class Node {
		int y, x, dir, cnt;

		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		public Node(int y, int x, int dir, int cnt) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}