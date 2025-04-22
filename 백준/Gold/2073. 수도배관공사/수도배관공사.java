import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[P];

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(length, cost);
		}

		int[] dp = new int[D + 1];

		Arrays.sort(nodes, (o1, o2) -> o1.cost - o2.cost);

		for (int i = 0; i < P; i++) {
			Node now = nodes[i];

			for (int j = D; j >= now.length; j--) {
				if (dp[j - now.length] == 0) {
					if (j == now.length)
						dp[j] = now.cost;
				} else {
					dp[j] = Math.max(dp[j], Math.min(dp[j - now.length], now.cost));
				}
			}
		}

		System.out.println(dp[D]);
	}

	static class Node {
		int length, cost;

		public Node(int length, int cost) {
			this.length = length;
			this.cost = cost;
		}
	}
}