import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parents, candies;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()) - 1;

		parents = new int[N + 1];    // 자식 사이즈 배열
		candies = new int[N + 1];    // 사탕 개수 배열
		Arrays.fill(parents, -1);

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			candies[i] = Integer.parseInt(st.nextToken());
		}

		// 연결
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		// 루트 객체 (인원수, 사탕수) 리스트
		List<Node> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (parents[i] < 0) {
				list.add(new Node(-parents[i], candies[i]));
			}
		}

		int[] dp = new int[K + 1];

		// 냅색
		for (Node now : list) {
			for (int i = K; i >= now.member; i--) {
				dp[i] = Math.max(dp[i], dp[i - now.member] + now.candy);
			}
		}
		System.out.println(dp[K]);
	}

	static class Node {
		int member, candy;

		public Node(int member, int candy) {
			this.member = member;
			this.candy = candy;
		}
	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;
		parents[rootA] += parents[rootB];
		candies[rootA] += candies[rootB];
		parents[rootB] = rootA;
	}
}