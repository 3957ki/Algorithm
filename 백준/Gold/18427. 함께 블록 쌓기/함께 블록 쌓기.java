import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][H + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= H; j++) {
				dp[i][j] = dp[i - 1][j];
			}
			while (st.hasMoreTokens()) {
				int now = Integer.parseInt(st.nextToken());
				dp[i][now] = (dp[i][now] + 1) % 10007;
				for (int j = now; j <= H; j++) {
					dp[i][j] = (dp[i][j] + dp[i - 1][j - now]) % 10007;
				}
			}
		}

		System.out.println(dp[N][H]);
	}
}