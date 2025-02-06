import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		A:
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int O = Integer.parseInt(st.nextToken());
			int F = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			int start_x = Integer.parseInt(st.nextToken());
			int end_y = Integer.parseInt(st.nextToken());
			int end_x = Integer.parseInt(st.nextToken());

			// 도착지가 같으면 가능
			if (start_y == end_y && start_x == end_x) {
				sb.append("잘했어!!").append('\n');
				continue A;
			}

			int[][] map = new int[N + 1][M + 1];

			for (int i = 0; i < O; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				map[y][x] = l;
			}

			Queue<Node> queue = new ArrayDeque<>();
			int[] dx = {0, 1, 0, -1};
			int[] dy = {1, 0, -1, 0};
			int[][] visited = new int[N + 1][M + 1];

			queue.add(new Node(start_y, start_x, F));
			visited[start_y][start_x] = F;

			while (!queue.isEmpty()) {
				Node now = queue.poll();

				for (int d = 0; d < 4; d++) {
					int y = now.y + dy[d];
					int x = now.x + dx[d];

					// 못가면 패스
					if (y <= 0 || y > N || x <= 0 || x > M)
						continue;

					// 높이가 힘보다 더 크면 패스
					if (map[y][x] - map[now.y][now.x] > now.power || now.power == 0)
						continue;

					// 도착지에 갈 수 있으면
					if (y == end_y && x == end_x) {
						sb.append("잘했어!!").append('\n');
						continue A;
					}

					// 다음으로 가는게 이득이라면
					if (visited[y][x] >= now.power - 1) {
						continue;
					}
					visited[y][x] = now.power - 1;
					queue.add(new Node(y, x, now.power - 1));
				}
			}

			sb.append("인성 문제있어??").append('\n');
		}
		System.out.println(sb);
	}

	static class Node {
		int y, x, power;

		public Node(int y, int x, int power) {
			this.y = y;
			this.x = x;
			this.power = power;
		}
	}
}