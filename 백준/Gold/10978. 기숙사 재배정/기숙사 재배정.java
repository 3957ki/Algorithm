import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static long answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] dp = new long[21];
		dp[1] = 0;

		for (int i = 2; i <= 20; i++) {
			dp[i] = dp[i - 1] * i + ((i & 1) == 0 ? 1 : -1);
		}

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
		}

		System.out.println(sb);

	}

}