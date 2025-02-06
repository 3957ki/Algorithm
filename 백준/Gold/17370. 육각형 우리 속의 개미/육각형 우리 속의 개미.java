import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static int answer, N;
	static boolean[][] visited = new boolean[1000][1000];
	static Map<Integer, int[]> dir = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		dir = new HashMap<>();
		dir.put(21, new int[] {22, 20});
		dir.put(22, new int[] {2, 21});
		dir.put(2, new int[] {1, 22});
		dir.put(1, new int[] {0, 2});
		dir.put(0, new int[] {20, 1});
		dir.put(20, new int[] {0, 21});

		N = Integer.parseInt(br.readLine());

		visited[500][500] = true;
		visited[501][501] = true;
		DFS(500, 500, 0, 0);
		System.out.println(answer);

	}

	public static void DFS(int y, int x, int now, int cnt) {

		if (cnt == N)
			return;

		int[] next = dir.get(now);

		for (int d : next) {
			int ny = y + (d / 10) - 1;
			int nx = x + (d % 10) - 1;
			if (visited[ny][nx] && cnt == N - 1) {
				answer++;
				continue;
			}

			if (visited[ny][nx])
				continue;

			visited[ny][nx] = true;
			DFS(ny, nx, d, cnt + 1);
			visited[ny][nx] = false;
		}
	}
}