import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int N = str.length();
		
		int[] arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = str.charAt(i-1)-'0';
		}
		
		int[] dp = new int[N+1];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <=N; i++) {
//			1자리 자르기
			if(arr[i] != 0) dp[i] += dp[i-1];
			
//			2자리 자르기
			if(arr[i-1]*10+arr[i] <= 34 && arr[i-1] != 0) dp[i] += dp[i-2];
		}
		
		System.out.println(dp[N]);
	}

}