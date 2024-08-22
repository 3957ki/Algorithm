import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] dx = {0, 0, 1, -1};
			int[] dy = {1, -1, 0, 0};
//			해당 위치에서 시작해서 갈 수 있는 최대 길이
			int[][] dp = new int[N][N];
			
			int start = Integer.MAX_VALUE;
			int max = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(dp[i][j] != 0) continue;
					int y = i;
					int x = j;
					int distance = 1;
//					탐색 시작
					A:while(true) {
						for(int d = 0; d < 4; d++) {
							int Y = y + dy[d];
							int X = x + dx[d];
							if(X < 0 || X >= N || Y < 0 || Y >= N) continue;
//							갈 수 있다면
							if(map[y][x]+1 == map[Y][X]) {
//								메모가 되어있으면
								if(dp[Y][X] != 0) {
									dp[i][j] = dp[Y][X] + distance;
									break A;
								}
//								아니라면 거리 늘리고 다음 탐색
								else {
									distance++;
									y = Y;
									x = X;
									continue A;
								}
							}
						}
//						더 이상 갈 수 없으면 distance 저장 
						dp[i][j] = distance;
						break;
					}
					if(dp[i][j] > max) {
						max = dp[i][j];
						start = map[i][j];
					}
					else if(dp[i][j] == max) {
						start = Math.min(start, map[i][j]);
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(start).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node{
		int x, y, distance;

		public Node(int y, int x, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
		
	}
}