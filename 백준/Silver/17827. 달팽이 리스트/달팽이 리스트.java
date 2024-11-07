import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		for(int i = 0; i < M; i++) {
			int K = Integer.parseInt(br.readLine());
//			회전 필요 없을 때
			if(K < N) sb.append(arr[K+1]).append('\n');
			
//			회전
			else sb.append(arr[V+(K-V+1)%(N-V+1)]).append('\n');
		}
		System.out.println(sb);
	}

}