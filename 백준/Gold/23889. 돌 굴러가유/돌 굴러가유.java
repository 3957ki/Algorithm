import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

		int[] dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
		}

		int[] stones = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}

		List<Node> list = new ArrayList<>();

		int prev = N;
		for (int i = K - 1; i >= 0; i--) {
			list.add(new Node(stones[i], dp[prev] - dp[stones[i] - 1]));
			prev = stones[i] - 1;
		}

		Collections.sort(list, (o1, o2) -> o2.value - o1.value);

		int[] arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = list.get(i).index;
		}

		Arrays.sort(arr);

		for (int now : arr) {
			sb.append(now).append('\n');
		}
		System.out.println(sb);
	}

	static class Node {
		int index, value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
}