import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];

		Node start = null;

		// 불 큐
		Queue<Node> queue = new ArrayDeque<>();

		// -1: 벽
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				switch (str.charAt(j)) {
					case '#':
						map[i][j] = -1;
						break;
					case 'J':
						if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
							System.out.println(1);
							return;
						}
						start = new Node(i, j);
						break;
					case 'F':
						map[i][j] = 1;
						queue.add(new Node(i, j));
						break;
					default:
						break;
				}
			}
		}

		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];

				if (y < 0 || y >= R || x < 0 || x >= C || map[y][x] != 0)
					continue;
				map[y][x] = map[now.y][now.x] + 1;
				queue.add(new Node(y, x));
			}
		}

		boolean[][] visited = new boolean[R][C];
		queue.add(start);
		visited[start.y][start.x] = true;

		int dst = 1;
		while (!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			while (L-- > 0) {
				Node now = queue.poll();
				for (int i = 0; i < 4; i++) {
					int y = now.y + dy[i];
					int x = now.x + dx[i];

					// 방문한 곳이거나, 벽이거나, 불타는 곳
					if (y < 0 || y >= R || x < 0 || x >= C || visited[y][x] || map[y][x] == -1 || (map[y][x] <= dst
						&& map[y][x] > 0))
						continue;
					visited[y][x] = true;

					if (y == 0 || x == 0 || y == R - 1 || x == C - 1) {
						System.out.println(dst);
						return;
					}

					queue.add(new Node(y, x));
				}
			}
		}

		System.out.println("IMPOSSIBLE");

	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}