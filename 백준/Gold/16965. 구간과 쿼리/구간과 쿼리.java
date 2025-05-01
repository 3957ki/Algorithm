import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		List<Node> list = new ArrayList<>();

		int idx = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			switch (cmd) {
				case 1:
					int L = list.size();
					List<Integer> edges = new ArrayList<>();
					for (int j = 0; j < L; j++) {
						Node now = list.get(j);
						if ((x < now.start && y > now.start) || (x < now.end && y > now.end)) {
							now.edges.add(idx);
						}
						if ((now.start < x && now.end > x) || (now.start < y && now.end > y)) {
							edges.add(j);
						}
					}
					list.add(new Node(x, y, edges));
					idx++;
					break;
				case 2:
					Queue<Node> queue = new ArrayDeque<>();
					queue.add(list.get(x - 1));

					boolean[] visited = new boolean[list.size()];
					visited[x - 1] = true;

					int answer = 0;
					A:
					while (!queue.isEmpty()) {
						Node now = queue.poll();

						for (Integer next : now.edges) {
							if (visited[next])
								continue;
							visited[next] = true;
							if (next == y - 1) {
								answer = 1;
								break A;
							}
							queue.add(list.get(next));
						}
					}

					sb.append(answer).append('\n');

					break;
			}
		}

		System.out.println(sb);
	}

	static class Node {
		int start, end;
		List<Integer> edges;

		public Node(int start, int end, List<Integer> edges) {
			this.start = start;
			this.end = end;
			this.edges = edges;
		}
	}
}