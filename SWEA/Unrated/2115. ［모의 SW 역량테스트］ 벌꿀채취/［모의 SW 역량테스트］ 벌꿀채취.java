import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static int answer;
	static int ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int sum = 0;
			for(int ay = 0; ay < N; ay++) {
				for(int ax = 0; ax <= N-M; ax++) {
//					a위치
					answer = 0;
					subset(0, ay, ax, M, C, 0, 0);
					sum = answer;
					for(int by = ay; by < N; by++) {
						for(int bx = 0; bx <= N-M; bx++) {
							if(by == ay && bx < ax+M) continue;
							answer = 0;
							subset(0, by, bx, M, C, 0, 0);
							ans = Math.max(ans, sum+answer);
						}
					}
				}
			}
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static void subset(int cnt, int y, int x, int M, int C, int sum, int profit) {
		if(sum > C) {
			return;
		}
		if(cnt == M) {
			answer = Math.max(answer, profit);
			return;
		}
		subset(cnt+1, y, x+1, M, C, sum+map[y][x], profit+(int)Math.pow(map[y][x], 2));
		subset(cnt+1, y, x+1, M, C, sum, profit);
	}
}