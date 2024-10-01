import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] dp = new int[1001][2];
//		홀수 파티션
		dp[1][0] = 1;
//		짝수파티션
		dp[1][1] = 0;
		
		for(int i = 2; i <= 1000; i++) {
//			짝수일 때
			if((i&1) == 0) {
				dp[i][0] = 1;
				for(int j = 0; j < i; j+=2) {
					dp[i][0] += dp[j][1];
				}
				dp[i][1] = dp[i/2][0]+dp[i/2][1];
			}
//			홀수일 때
			else {
				dp[i][0] = dp[i-1][0]+dp[i-1][1];
			}
		}
//		
//		for(int j = 0; j <= 1; j++) {
//			for(int i = 1; i <=10; i++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N][0]+dp[N][1]).append('\n');
		}
		System.out.println(sb);
	}

}