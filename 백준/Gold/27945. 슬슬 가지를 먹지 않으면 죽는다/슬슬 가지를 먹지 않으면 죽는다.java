import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		Arrays.fill(parents, -1);

		List<Node> edges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Node(start, end, weight));
		}

		Collections.sort(edges, (o1, o2) -> o1.w - o2.w);

		int answer = 0;
		int cnt = 0;
		for (Node node : edges) {
			if (union(node.start, node.end)) {
				answer++;
				if (node.w != answer)
					break;
				if (++cnt == N - 1) {
					answer++;
					break;
				}
			}
		}

		System.out.println(answer);
	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}

	static class Node {
		int start, end, w;

		public Node(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}

	}
}