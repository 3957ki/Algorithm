import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int totalSum = 0;
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
//			1개가 S 이상이면 return
			if(arr[i] >= S) {
				System.out.println(1);
				return;
			}
			totalSum += arr[i];
		}
//		총 합이 S보다 작다면 return
		if(totalSum < S) {
			System.out.println(0);
			return;
		}
		
		long[] dp = new long[N+1];
		
		int start = 1;
		dp[1] = arr[1];
		
		int answer = N;
		for(int i = 2; i <= N; i++) {
//			누적합
			dp[i] = dp[i-1]+arr[i];
//			S보다 작으면 패스
			if(dp[i] < S) continue;
//			시작지점 이동
			while(start < i) {
//				누적합에서 시작지점을 뺀 값이 S이상이라면
				if(dp[i] - arr[start] >= S) {
//					시작지점 빼고 시작지점 이동
					dp[i] -= arr[start];
					start++;
					continue;
				}
				break;
			}
//			길이 갱신
			answer = Math.min(answer, i - start+1);
		}
		System.out.println(answer);
	}

}