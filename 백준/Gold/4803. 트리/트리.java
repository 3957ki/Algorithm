import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int[] parents = new int[501];
	static Set<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 1;
		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 종료 조건
			if (N == 0 && M == 0) {
				break;
			}

			sb.append("Case ").append(T++).append(':');

			// 간선 리스트
			List<int[]> edges = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				edges.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}

			// 초기화
			for (int i = 1; i <= N; i++)
				parents[i] = -1;

			// 사이클
			set = new HashSet<>();

			for (int[] edge : edges) {
				union(edge[0], edge[1]);
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				// 루트이고 사이클이 아니면 ++
				if (parents[i] < 0 && !set.contains(i))
					cnt++;
			}

			switch (cnt) {
				case 0:
					sb.append(" No trees.");
					break;
				case 1:
					sb.append(" There is one tree.");
					break;
				default:
					sb.append(" A forest of ").append(cnt).append(" trees.");
					break;
			}

			sb.append('\n');
		}

		System.out.println(sb);
	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		// union 실패시
		if (rootA == rootB) {
			set.add(rootA);
			return;
		}

		// 둘 중 하나라도 사이클이라면
		if (set.contains(rootA) || set.contains(rootB)) {
			set.add(rootA);
			set.add(rootB);
			return;
		}

		parents[rootA] += parents[rootB];
		parents[rootB] = rootA;
	}
}