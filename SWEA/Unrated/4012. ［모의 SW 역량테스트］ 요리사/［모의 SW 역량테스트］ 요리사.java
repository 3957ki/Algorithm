import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int answer, N;
	static int[] A, B;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			A = new int[N];
			B = new int[N];
			arr = new int[N][N];
			boolean[] visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			comb(0, 0, visited);
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void comb(int cnt, int start, boolean[] visited) {
		if(cnt == N/2) {
			long sumA = 0;
			long sumB = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i] && visited[j]) {
						sumA+=arr[i][j];
					}
					else if(!visited[j] && !visited[i]) {
						sumB+=arr[i][j];
					}
				}
			}
			answer = Math.min((int)Math.abs(sumA-sumB), answer);
			return;
		}
		
		for(int i = start; i < N; i++) {
			visited[i] = true;
			comb(cnt+1, i+1, visited);
			visited[i] = false;
		}
		
	}
}
