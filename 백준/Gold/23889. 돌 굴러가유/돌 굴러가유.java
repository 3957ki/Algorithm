import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];
		dp[N] = arr[N];

		for (int i = N - 1; i >= 1; i--) {
			dp[i] = arr[i] + dp[i + 1];
		}

		int[] stones = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}

		List<Node> list = new ArrayList<>();

		int prev = 0;
		for (int i = K - 1; i >= 0; i--) {
			list.add(new Node(stones[i], dp[stones[i]] - prev));
			prev = dp[stones[i]];
		}

		list.sort((o1, o2) -> {
			if (o1.value == o2.value)
				return o1.index - o2.index;

			return o2.value - o1.value;
		});

		int[] answer = new int[M];
		for (int i = 0; i < M; i++) {
			answer[i] = list.get(i).index;
		}

		Arrays.sort(answer);

		for (int now : answer) {
			sb.append(now).append('\n');
		}
		System.out.println(sb);
	}

	static class Node {
		int index;
		int value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
}