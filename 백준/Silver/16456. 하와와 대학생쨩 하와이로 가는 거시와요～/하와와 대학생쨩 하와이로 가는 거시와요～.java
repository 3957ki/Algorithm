import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1);
			return;
		}
		if(N == 2) {
			System.out.println(1);
			return;
		}
		if(N == 3) {
			System.out.println(2);
			return;
		}
		if(N == 4) {
			System.out.println(3);
			return;
		}
		long[] dp = new long[N+1];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 2;
		dp[4] = 3;
		for(int i = 5; i <= N; i++) {
			dp[i] = (dp[i-1]+dp[i-3])%1000000009;
		}
		System.out.println(dp[N]);
	}

}