import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[65];
		
		for(int i = 1; i <= 64; i++) {
			dp[i] = dp[i-1]*2 + (long)Math.pow(2, i-1);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		String A = Long.toBinaryString(a);
		String B = Long.toBinaryString(b);
		
		int temp = 0;
		long sumA = 0;
		long sumB = 0;
		
		int cntA = 0;
		for(int i = 0; i < A.length(); i++) {
			if(A.charAt(i) == '1') {
				cntA++;
				sumA += dp[A.length()-i-1]+(long)Math.pow(2, A.length()-i-1)*temp++;
			}
		}
		sumA+=temp;
		
		temp = 0;
		for(int i = 0; i < B.length(); i++) {
			if(B.charAt(i) == '1') {
				sumB += dp[B.length()-i-1]+(long)Math.pow(2, B.length()-i-1)*temp++;
			}
		}
		sumB+=temp;
		
		long answer = sumB-sumA+cntA;
		
		System.out.println(answer);
	}

}