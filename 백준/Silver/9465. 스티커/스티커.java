import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N+1][2];
			int[][] dp = new int[N+1][2];
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					arr[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			dp[1][0] = arr[1][0];
			dp[1][1] = arr[1][1];
			int max = Math.max(arr[1][0], arr[1][1]);
			for(int i = 2; i <= N; i++) {
				dp[i][0] = Math.max(dp[i-1][1]+arr[i][0], Math.max(dp[i-2][0], dp[i-2][1])+arr[i][0]);
				dp[i][1] = Math.max(dp[i-1][0]+arr[i][1], Math.max(dp[i-2][0], dp[i-2][1])+arr[i][1]);
				max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
			}
			sb.append(max).append('\n');
		}
		System.out.println(sb);
	}
}
