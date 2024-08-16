import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int answer, N, L;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new int[N][2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
//				점수
				arr[i][0] = Integer.parseInt(st.nextToken());
//				칼로리
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			answer = 0;
			subset(0, 0, 0);
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void subset(int cnt, int score, int sum) {
		if(sum > L) return;
		if(cnt == N) {
			answer = Math.max(answer, score);
			return;
		}
		
		subset(cnt+1, score+arr[cnt][0], sum+arr[cnt][1]);
		subset(cnt+1, score, sum);
	}

}