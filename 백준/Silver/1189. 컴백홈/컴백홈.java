import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, M, K, answer;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		DFS(N-1, 0, 1, 1<<(N-1)*M);
		
		System.out.println(answer);
	}

	static void DFS(int y, int x, int dst, int visited) {
		if(y == 0 && x == M-1) {
			if(dst == K) answer++;
			return;
		}
		if(dst == K) return;
		
		for(int d = 0; d < 4; d++) {
			int Y = y + dy[d];
			int X = x + dx[d];
			if(X < 0 || X >= M || Y < 0 || Y >= N || ((visited & 1<<(Y*M + X)) != 0) || map[Y][X] == 'T') continue;
			DFS(Y, X, dst+1, (visited | 1<<(Y*M + X)));
		}
	}
}