import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 인접 행렬
		int[][] edges = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (start > end)
				continue;

			edges[start][end] = Math.max(cost, edges[start][end]);
		}

		// dp 배열 (cnt, cost)
		Map<Integer, Integer>[] dp = new Map[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = new HashMap<>();
		}

		dp[1].put(1, 0);

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				if (edges[j][i] == 0)
					continue;
				for (Integer cnt : dp[j].keySet()) {
					dp[i].put(cnt + 1, Math.max(dp[i].getOrDefault(cnt + 1, 0), dp[j].get(cnt) + edges[j][i]));
				}
			}
		}

		int max = 0;
		for (Integer cnt : dp[N].keySet()) {
			if (cnt > M)
				continue;
			max = Math.max(max, dp[N].get(cnt));
		}
		System.out.println(max);
	}
}