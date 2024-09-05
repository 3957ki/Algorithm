import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		홀수면 불가능
		if((N&1) == 1) {
			System.out.println(0);
			return;
		}
		if(N == 2) {
			System.out.println(3);
			return;
		}
		if(N == 4) {
			System.out.println(11);
			return;
		}
		
		int[] dp = new int[N+1];
		dp[2] = 3;
		dp[4] = 11;
//		짝수만 갱신
		for(int i = 6; i <= N; i+=2) {
			int special = 0;
//			나올 수 있는 특이케이스 모두 추가하기
			for(int j = i-4; j >= 2; j--) {
				special += dp[j]*2;
			}
//			i에서만 나오는 특이케이스 +2
			dp[i] = dp[i-2]*3 + special + 2;
		}
		System.out.println(dp[N]);
	}

}