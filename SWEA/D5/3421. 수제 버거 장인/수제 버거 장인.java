import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int answer, N;
	static boolean[][] arr;
	static boolean[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			arr = new boolean[N+1][N+1];
			list = new boolean[N+1];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int b1 = Integer.parseInt(st.nextToken());
				int b2 = Integer.parseInt(st.nextToken());
				arr[b1][b2] = true;
				arr[b2][b1] = true;
			}
			
			subset(1);
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void subset(int cnt) {
		
		if(cnt > N) {
//			System.out.println(Arrays.toString(list));
			answer++;
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(list[i] && arr[cnt][i]) {
				subset(cnt+1);
				return;
			}
		}
		list[cnt] = true;
		subset(cnt+1);
		list[cnt] = false;
		subset(cnt+1);
	}

}