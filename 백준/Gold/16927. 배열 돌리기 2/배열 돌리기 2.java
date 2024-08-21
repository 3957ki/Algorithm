import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//성능 요약
//메모리: 126208 KB, 시간: 788 ms

//바깥부터 한칸씩 배열을 돌린다.
//delta를 사용해서 방향을 꺾어준다.
//바깥을 다 돌리면 안쪽을 돌리며 반복한다.
//방향을 꺾었는데도 방문한 곳이라면 다 돌렸으므로 break한다.
//이 과정을 R번 반복하고 출력한다.

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int k = Math.min(N / 2, M / 2);

		for (int i = 0; i < k; i++) {
			int RR = R % ((N + M) * 2 - 4);

			for (int r = 0; r < RR; r++) {
				int now = arr[i][i];
				int y = i;
				int x = i;
				int d = 0;
				
				for (int j = 1; j < M; j++) {
					int Y = y + dy[d];
					int X = x + dx[d];
					arr[y][x] = arr[Y][X];
					y = Y;
					x = X;
				}
				d = (d + 1) % 4;
				for (int j = 1; j < N; j++) {
					int Y = y + dy[d];
					int X = x + dx[d];
					arr[y][x] = arr[Y][X];
					y = Y;
					x = X;
				}
				d = (d + 1) % 4;
				for (int j = 1; j < M; j++) {
					int Y = y + dy[d];
					int X = x + dx[d];
					arr[y][x] = arr[Y][X];
					y = Y;
					x = X;
				}
				d = (d + 1) % 4;
				for (int j = 1; j < N; j++) {
					int Y = y + dy[d];
					int X = x + dx[d];
					if (j == N - 1) {
						arr[y][x] = now;
						continue;
					}
					arr[y][x] = arr[Y][X];
					y = Y;
					x = X;
				}
				d = (d + 1) % 4;
				
			}

			N -= 2;
			M -= 2;
		}

		for (int[] a : arr) {
			for (int num : a) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}