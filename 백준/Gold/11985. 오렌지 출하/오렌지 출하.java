import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N+1];
		for(int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		long[] dp = new long[N+1];
		dp[1] = K;
		
		for(int i = 2; i <= N; i++) {
//			i번 오렌지를 1개만 포장했을 때로 초기화
			dp[i] = dp[i-1] + K;
//			최소값 최대값을 i번 오렌지크기로 초기화
			long min = A[i];
			long max = A[i];
//			1보다 작아지지 않는 선에서 거꾸로 최대 M개 까지 담기
			int L = Math.max(i-M+1, 1);
			for(int j = i-1; j >= L; j--) {
//				최대값 최소값 갱신
				min = Math.min(min, A[j]);
				max = Math.max(max, A[j]);
//				j-1번 dp값에 j부터 i까지 포장한 비용을 더한 값과 비교하여 최소값 갱신
				dp[i] = Math.min(dp[i], dp[j-1]+K+(i-j+1)*(max - min));
			}
		}
		System.out.println(dp[N]);
	}

}