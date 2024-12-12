import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int mod = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long a = 1;		// nCk
		long b = 1; 	// k! 
		for(int i = 0; i < K; i++) {
			a = (a*(N-i))%mod;
			b = (b*(K-i))%mod;
		}
		
		int exp = mod-2;
		long temp = 1;
		
		while(exp > 0) {
//			홀수
			if((exp&1) == 1) {
				temp = (temp*b)%mod;
			}
			
			b = (b*b)%mod;
			exp = exp >> 1;
		}
		
		long answer = a*temp%mod;
		
		exp = K-1;
		temp = 1;
		b = 2;
		while(exp > 0) {
//			홀수
			if((exp&1) == 1) {
				temp = (temp*b)%mod;
			}
			
			b = (b*b)%mod;
			exp = exp >> 1;			
		}
		
		answer = answer*temp%mod;
		System.out.println(answer);
		System.exit(0);
	}

}