import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		List<Node>[] dp = new List[M + 1];
		for (int i = 0; i <= M; i++) {
			dp[i] = new ArrayList<>();
		}

		dp[0].add(new Node(n1, n2, 0));

		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(br.readLine());
			for (Node now : dp[i]) {
				int diff1 = Math.abs(now.n1 - num);
				int diff2 = Math.abs(now.n2 - num);

				dp[i + 1].add(new Node(num, now.n2, now.sum + diff1));
				dp[i + 1].add(new Node(now.n1, num, now.sum + diff2));

			}
		}

		int answer = Integer.MAX_VALUE;

		for (Node now : dp[M]) {
			answer = Math.min(answer, now.sum);
		}

		System.out.println(answer);
	}

	static class Node {
		int n1, n2, sum;

		public Node(int n1, int n2, int sum) {
			this.n1 = n1;
			this.n2 = n2;
			this.sum = sum;
		}
	}
}