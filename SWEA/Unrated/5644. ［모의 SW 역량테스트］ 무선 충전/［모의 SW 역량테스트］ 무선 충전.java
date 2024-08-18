import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
//			이동거리
			int M = Integer.parseInt(st.nextToken());
//			충전기 개수
			int A = Integer.parseInt(st.nextToken());
			
			int[][][] map = new int[12][12][A+1];
			int[] dx = {0, 0, 1, 0, -1};	//상우하좌
			int[] dy = {0, -1, 0, 1, 0};
			
			int[][] arr_a = new int[M+1][2];
			int[][] arr_b = new int[M+1][2];
//			0은 y 1은 x
			arr_a[0][0] = 1;
			arr_a[0][1] = 1;
			arr_b[0][0] = 10;
			arr_b[0][1] = 10;
//			a 경로
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				int d = Integer.parseInt(st.nextToken());
				arr_a[i][0] = arr_a[i-1][0]+dy[d];
				arr_a[i][1] = arr_a[i-1][1]+dx[d];
			}
//			b 경로
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				int d = Integer.parseInt(st.nextToken());
				arr_b[i][0] = arr_b[i-1][0]+dy[d];
				arr_b[i][1] = arr_b[i-1][1]+dx[d];
			}
//			배터리 파워 저장
			for(int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				for(int Y = y-c; Y <= y+c; Y++) {
					for(int X = x-c; X <= x+c; X++) {
						if(X < 1 || X > 10 || Y < 1 || Y > 10) continue;
						if(Math.abs(X-x) + Math.abs(Y-y) <= c) {
							map[Y][X][i] = p;
						}
					}
				}
			}
			int sum = 0;
			for(int i = 0; i <= M; i++) {
				int a_y = arr_a[i][0];
				int a_x = arr_a[i][1];
				int b_y = arr_b[i][0];
				int b_x = arr_b[i][1];
				
				int maxA = 0;
				int maxA_pos = 0;
				int prevA = 0;
				int maxB = 0;
				int maxB_pos = 0;
				int prevB = 0;
				for(int j = 1; j <= A; j++) {
					if(maxA < map[a_y][a_x][j]) {
						prevA = maxA;
						maxA = map[a_y][a_x][j];
						maxA_pos = j;
					}
					else if(prevA < map[a_y][a_x][j]) {
						prevA = map[a_y][a_x][j];
					}
					if(maxB < map[b_y][b_x][j]) {
						prevB = maxB;
						maxB = map[b_y][b_x][j];
						maxB_pos = j;
					}
					else if(prevB < map[b_y][b_x][j]) {
						prevB = map[b_y][b_x][j];
					}
				}
				if(maxA_pos == maxB_pos && maxA_pos !=0) {
					if(prevA > prevB) {
						sum += prevA + maxB;
					}
					else if(prevA < prevB) {
						sum += maxA + prevB;
					}
					else if(prevA == prevB) {
						if((maxA/2 + maxB/2) > maxA+prevB) {
							sum += maxA/2 + maxB/2;
						}
						else {
							sum += maxA+prevB;
						}
					}
					continue;
				}
				sum += maxA + maxB;
//				System.out.println(sum);
			}
			sb.append('#').append(t).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}
}