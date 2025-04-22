import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int[] dp = new int[D + 1];
		dp[0] = Integer.MAX_VALUE;

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			for (int j = D; j >= length; j--) {
				if (dp[j - length] > 0)
					dp[j] = Math.max(dp[j], Math.min(dp[j - length], cost));
			}
		}

		System.out.println(dp[D]);
	}

}