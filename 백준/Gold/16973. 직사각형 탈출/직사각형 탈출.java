import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = {-1, 1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<int[]> wall = new ArrayList<>();
		int[][] map = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					wall.add(new int[]{i, j});
					map[i][j] = 0;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int SR = Integer.parseInt(st.nextToken());
		int SC = Integer.parseInt(st.nextToken());
		int FR = Integer.parseInt(st.nextToken());
		int FC = Integer.parseInt(st.nextToken());

		for(int[] w : wall) {
			int y = Math.max(w[0] - H + 1, 1);
			int x = Math.max(w[1] - W + 1, 1);
			map[y][x] += 1;

			if(w[0] + 1 <= N) map[w[0] + 1][x] -= 1;
			if(w[1] + 1 <= M) map[y][w[1] + 1] -= 1;
			if(w[0] + 1 <= N && w[1] + 1 <= M) map[w[0] + 1][w[1] + 1] += 1;
		}

		N += 1 - H;
		M += 1 - W;

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				map[i][j] += map[i][j-1];
			}
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				map[i][j] += map[i-1][j];
			}
		}

		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+1][M+1];
		queue.add(new int[] {SR, SC});
		visited[SR][SC] = true;

		int answer = 0;
		while(!queue.isEmpty()) {
			int L = queue.size();
			while(L-- > 0) {
				int[] now = queue.poll();

				if(now[0] == FR && now[1] == FC) {
					System.out.println(answer);
					return;
				}

				for(int d = 0; d < 4; d++) {
					int y = now[0] + dy[d];
					int x = now[1] + dx[d];
					if(y <= 0 || y > N || x <= 0 || x > M || visited[y][x] || map[y][x] > 0) continue;
					visited[y][x] = true;
					queue.add(new int[] {y, x});
				}
			}
			answer++;
		}

		System.out.println(-1);
    }
}