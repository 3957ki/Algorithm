import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 2][M + 2];

		st = new StringTokenizer(br.readLine());
//      퀸 개수
		int Q = Integer.parseInt(st.nextToken());
		for (int i = 0; i < Q; i++) {
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			arr[n][m] = 1;
		}

		st = new StringTokenizer(br.readLine());
//      나이트 개수
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			arr[n][m] = 2;
		}

		st = new StringTokenizer(br.readLine());
//      폰 개수
		int P = Integer.parseInt(st.nextToken());
		for (int i = 0; i < P; i++) {
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			arr[n][m] = 3;
		}

		boolean[][] visited = new boolean[N + 2][M + 2];
		int[] Qdx = { 1, -1, 0, 0, 1, -1, -1, 1 };
		int[] Qdy = { 0, 0, 1, -1, 1, -1, 1, -1 };
		int[] Kdx = { 1, 1, -1, -1, 2, 2, -2, -2 };
		int[] Kdy = { 2, -2, 2, -2, 1, -1, 1, -1 };
//		탐색
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
//				퀸일때
				if (arr[i][j] == 1) {
					for (int d = 0; d < 8; d++) {
						int num = 1;
						while (true) {
							int y = i + Qdy[d] * num;
							int x = j + Qdx[d] * num;
							if (x < 1 || x > M || y < 1 || y > N) break;
							if (arr[y][x] != 0) break;
							visited[y][x] = true;
							num++;
						}
					}
				}
//				나이트일때
				else if (arr[i][j] == 2) {
					for (int d = 0; d < 8; d++) {
						int y = i + Kdy[d];
						int x = j + Kdx[d];
						if (x < 1 || x > M || y < 1 || y > N) continue;
						if (arr[y][x] != 0) continue;
						visited[y][x] = true;
					}
				}
			}
		}
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!visited[i][j] && arr[i][j] == 0) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	
}