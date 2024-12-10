import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
//		dp[현재 위치][건너뛴 횟수] = 거리
		int[][] dp = new int[N+1][K+1];
		
//		체크포인트 좌표
		Check[] arr = new Check[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[i] = new Check(y, x);
		}
		
		dp[2][0] = Math.abs(arr[2].y - arr[1].y) + Math.abs(arr[2].x - arr[1].x);
		
		int answer = 0;
		
//		현재 체크포인트
		for(int now = 3; now <= N; now++) {
//			건너뛴 횟수
			for(int i = 0; i <= K && i <= now-2; i++) {
				dp[now][i] = Integer.MAX_VALUE;
				if(i+2 == now) {
					dp[now][i] = Math.abs(arr[now].y - arr[now-i-1].y) + Math.abs(arr[now].x - arr[now-i-1].x);
					continue;
				}
				for(int j = 0; j <= i; j++) {
//					이전 체크포인트
					int prev = now-i-1+j;
					int dst = Math.abs(arr[now].y - arr[prev].y) + Math.abs(arr[now].x - arr[prev].x);
					dp[now][i] = Math.min(dp[prev][j] + dst, dp[now][i]);
				}
				answer = dp[now][i];
			}
		}
		
		System.out.println(answer);
		
	}
	
	static class Check {
		int y, x;

		public Check(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}