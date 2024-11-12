import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		long L = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 

		long[] arr = new long[N+2];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] =Long.parseLong(st.nextToken());
		}
		arr[0] = -1;
		arr[N+1] = L+1;

		//		오름차순
		Arrays.sort(arr);


		if(K <= N) {
			//		K개
			for(int i = 0; i < K; i++) {
				sb.append(0).append('\n');
			}
			System.out.println(sb);
			return;
		}
//		N개
		for(int i = 0; i < N; i++) {
			sb.append(0).append('\n');
		}

		K -= N;

		int dst = 0;
		A: while(true) {
			dst++;
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if((arr[i]-arr[i-1]) >= dst*2) {
					cnt++;
				}
				if((arr[i+1]-arr[i]) > dst*2) {
					cnt++;
				}
				if(i == N && (arr[i+1]-arr[i]) == dst*2) {
					cnt++;
				}
				while(cnt-- > 0) {
					sb.append(dst).append('\n');
					if(--K == 0) {
						break A;
					}
				}		
				cnt = 0;
			}
		}
		System.out.println(sb);
	}

}