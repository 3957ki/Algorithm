import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
//			동전 종류
			int[] coins = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
//			만들 금액
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[M+1];
			dp[0] = 1;
			
			for(int i = 1; i <= N; i++) {
				for(int j = coins[i]; j <= M; j++) {
					dp[j] += dp[j-coins[i]];
				}
			}
			sb.append(dp[M]).append('\n');
		}
		System.out.println(sb);
	}

}