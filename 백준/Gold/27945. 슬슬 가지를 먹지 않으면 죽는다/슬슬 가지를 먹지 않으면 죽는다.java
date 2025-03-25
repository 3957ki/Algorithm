import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

		Node[] edges = new Node[N];

		int cnt = N * (N - 1) / 2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// N일 부터는 볼 필요 없음
			if (weight >= N)
				continue;

			edges[weight] = new Node(start, end);
			cnt -= weight;
			if (cnt == 0)
				break;
		}

		int answer = 1;
		for (int i = 1; i < N; i++) {
			if (edges[i] == null)
				break;
			if (union(edges[i].start, edges[i].end))
				answer++;
			else
				break;
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
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

	}
}