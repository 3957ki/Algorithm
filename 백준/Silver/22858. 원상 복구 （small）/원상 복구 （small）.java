import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] P = new int[N+1];
		int[] S = new int[N+1];
		int[] D = new int[N+1];
		int[] temp = new int[N+1];
		int[] t;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			P[i] = i;
			S[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int k = 0; k < K; k++) {
			for(int i = 1; i <= N; i++) {
				temp[i] = P[D[i]];
			}
			t = temp;
			temp = P;
			P = t;
		}
		
		for(int i = 1; i <= N; i++) {
			temp[P[i]] = S[i];
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(temp[i]).append(' ');
		}
		System.out.println(sb);
	}

}