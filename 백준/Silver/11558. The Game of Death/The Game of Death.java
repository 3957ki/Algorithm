import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			boolean[] visited = new boolean[N+1];
			
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			
			int now = 1;
			int cnt = 1;
			
			while(!visited[now]) {
				visited[now] = true;
				now = arr[now];
				if(now == N) {
					visited[now] = true;
					break;
				}
				cnt++;
			}
			
			sb.append(visited[N] ? cnt : 0).append('\n');
		}
		System.out.println(sb);
	}

}