import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		for(int i = 1; i <= K; i++) {
			sum += i;
		}
		
//		최소 가능 숫자보다 N이 작으면 불가능
		if(N < sum) {
			System.out.println(-1);
		}
//		N에서 최소 가능 숫자를 뺀 값이 K와 나누어떨어지면 모두 1씩 차이나므로 K-1
		else if((N-sum) % K == 0) {
			System.out.println(K-1);
		}
//		K로 안나눠떨어지면 최소값은 1만큼 작으니까 K
		else {
			System.out.println(K);
		}
	}

}