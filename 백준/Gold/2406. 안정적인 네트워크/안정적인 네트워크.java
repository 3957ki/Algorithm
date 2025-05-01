import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[N + 1];
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			set.add(start * 1001 + end);
			set.add(end * 1001 + start);
		}

		int[][] edges = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if (!set.contains(i * 1001 + j)) {
					edges[i][j] = weight;
				}
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		pq.add(new Node(1, 2, 0));
		int sum = 0;
		List<Node> list = new ArrayList<>();

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (visited[now.end])
				continue;
			visited[now.end] = true;
			sum += now.weight;
			if (now.weight > 0) {
				list.add(now);
			}

			for (int i = 2; i <= N; i++) {
				if (!visited[i]) {
					pq.add(new Node(now.end, i, edges[i][now.end]));
				}
			}

		}

		sb.append(sum).append(' ').append(list.size()).append('\n');
		for (Node now : list) {
			sb.append(now.start).append(' ').append(now.end).append('\n');
		}

		System.out.println(sb);

	}

	static class Node {
		int start, end, weight;

		public Node(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}