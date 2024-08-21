import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][2];
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
//			시간
			arr[i][0] = Integer.parseInt(st.nextToken());
//			벌금
			arr[i][1] = Integer.parseInt(st.nextToken());
			sum+=arr[i][1];
		}
		
		int[] dp = new int[T+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = T; j >= arr[i][0]; j--) {
				dp[j] = Math.max(dp[j], dp[j-arr[i][0]]+arr[i][1]);
			}
		}
		int answer = sum-dp[T];
		System.out.println(answer);
	}

}