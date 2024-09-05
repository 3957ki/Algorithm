import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N+1];
		for(int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
//		인덱스 값을 만드는 경우의 수
		
		int[] dp = new int[K+1];
		dp[0] = 1;
		for(int i = 1; i <= N; i++) {
//			해당 동전보다 같거나 큰 값들에 대해
//			원래 dp[j]값 + dp[남는 금액]
			for(int j = coins[i]; j <= K; j++) {
				dp[j] += dp[j-coins[i]];
			}
		}
		
		System.out.println(dp[K]);
	}

}