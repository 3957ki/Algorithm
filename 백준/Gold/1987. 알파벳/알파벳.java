import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int R, C;
	static char[][] map;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean['Z'+1];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited[map[0][0]] = true;
		DFS(0, 0, 1);
		System.out.println(answer);
	}

	static void DFS(int r, int c, int cnt) {
		
		for(int d = 0; d < 4; d++) {
			int y = r+dy[d];
			int x = c+dx[d];
			if(x < 0 || x >= C || y < 0 || y >= R || visited[map[y][x]]) {
				answer = Math.max(answer, cnt);
				continue;
			}
			visited[map[y][x]] = true;
			DFS(y, x, cnt+1);
			visited[map[y][x]] = false;
		}
	}
}