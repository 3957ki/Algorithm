import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Set<Integer>[] edges;
	static StringBuilder sb = new StringBuilder();
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];

		edges = new Set[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new HashSet<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edges[start].add(end);
			edges[end].add(start);
		}

		int q = Integer.parseInt(br.readLine());

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			switch (cmd) {
				case 1:
					edges[start].add(end);
					edges[end].add(start);
					break;
				case 2:
					edges[start].remove(end);
					edges[end].remove(start);
					break;
			}

			BFS();
		}

		System.out.println(sb);
	}

	static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.add(1);
		visited[1] = true;

		int dst = 0;
		while (!queue.isEmpty()) {
			int L = queue.size();
			dst++;
			while (L-- > 0) {
				int cur = queue.poll();
				for (int next : edges[cur]) {
					if (visited[next])
						continue;
					visited[next] = true;
					dist[next] = dst;
					queue.add(next);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				sb.append(-1).append(' ');
			else
				sb.append(dist[i]).append(' ');
		}
		sb.append('\n');
	}
}