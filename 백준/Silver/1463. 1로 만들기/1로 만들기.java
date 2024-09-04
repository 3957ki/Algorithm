import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(0);
			return;
		}
		if(N == 2) {
			System.out.println(1);
			return;
		}
		
		int[] dp = new int[N+1];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i = 4; i <= N; i++) {
			dp[i] = Math.min(dp[i/2]+i%2+1, dp[i/3]+i%3+1);
		}
		
		System.out.println(dp[N]);
	}

}