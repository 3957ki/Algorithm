import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[K + 1];
		Arrays.fill(dp, 101);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= arr[i]; j--) {
				dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
			}
		}
		System.out.println(dp[K] == 101 ? -1 : dp[K]);
	}
}