import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int mod = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		long[] fact = new long[4000001];
		fact[0] = 1;
		for(int i = 1; i <= 4000000; i++) {
			fact[i] = (fact[i-1]*i)%mod;
		}
		
		int N, R, exp;
		long a, b, temp_b;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
//			nCr을 a/b의 형태로 변환
			a = fact[N];
			b = (fact[R]*fact[N-R])%mod;
			
//			페르마의 소정리 -> b의 -1승을 mod로 나눈 나머지 == b의 mod-2승을 mod로 나눈 나머지
//			b의 mod-2승 구하기
			exp = mod-2;
			temp_b = 1;
			
//			분할정복 거듭제곱
			while(exp > 0) {
//				홀수라면
				if((exp&1) == 1) {
					temp_b = (temp_b*b)%mod;
				}
				b = (b*b)%mod;
				exp = exp >> 1;
			}
			
			sb.append((a*temp_b)%mod).append('\n');
			
		}
		
		System.out.println(sb);
		
	}

}