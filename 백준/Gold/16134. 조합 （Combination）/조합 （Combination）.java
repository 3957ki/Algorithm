import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int mod = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
//		nCr을 a/b의 형태로 변환
		long a = 1;
		long b = 1;
		for(int i = 0; i < R; i++) {
			a = (a*(N-i))%mod;
			b = (b*(R-i))%mod;
		}
		
//		페르마의 소정리 -> b의 -1승을 mod로 나눈 나머지 == b의 mod-2승을 mod로 나눈 나머지
//		b의 mod-2승 구하기
		int exp = mod-2;
		long temp_b = 1;
		
//		분할정복 거듭제곱
		while(exp > 0) {
//			홀수라면
			if((exp&1) == 1) {
				temp_b = (temp_b*b)%mod;
			}
			b = (b*b)%mod;
			exp = exp >> 1;
		}
		
		System.out.println((a*temp_b)%mod);
		
	}

}