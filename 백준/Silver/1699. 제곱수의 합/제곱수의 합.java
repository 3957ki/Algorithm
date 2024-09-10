import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N+1];
		for(int i = 1; i <= N; i++) {
			dp[i] = i;
		}
		
		int n = (int)Math.sqrt(N);
		for(int i = 2; i <= n; i++) {
			for(int j = N; j >= i*i; j--) {
				dp[j] = Math.min(dp[j], j/(i*i) + dp[j%(i*i)]);
			}
		}
		
		System.out.println(dp[N]);
		
		
	}

}