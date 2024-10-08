import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		Arrays.sort(coins);
		
//		인덱스 값을 만드는 최소값
		int[] dp = new int[K+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for(int i = 1; i <= N; i++) {
//			dp[남는금액]이 불가능이면 갱신 불가
//			dp[j]가 불가능이면 1+dp[남는금액] 저장
//			가능하면 원래 dp[j]값과, 1+dp[남는금액] 중 최소값
			for(int j = coins[i]; j <= K; j++) {
				if(dp[j-coins[i]] == -1) continue;
				if(dp[j] == -1) {
					dp[j] = 1+dp[j-coins[i]];
				}
				else {
					dp[j] = Math.min(dp[j], 1+dp[j-coins[i]]);
				}
			}
		}
		
		System.out.println(dp[K]);
	}

}