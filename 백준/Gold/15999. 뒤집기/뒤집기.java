import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, M, cnt;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i] = str.toCharArray();
			}
		}
		
//		W가 기준
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'B' || visited[i][j]) continue;
//				floodfill
				BFS(i, j, 'W', visited);
			}
		}
		
//		B가 기준
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'W' || visited[i][j]) continue;
//				floodfill
				BFS(i, j, 'B', visited);
			}
		}
//		변하면 안되는 타일은 제외한 나머지 타일의 모든 경우의 수
		long a = 2;
		int b = N*M-cnt;
		long answer = 1;
		
//		거듭제곱
		while(b != 0) {
//			홀수라면
			if((b&1) == 1) {
				answer = (answer*a)%1000000007;
			}
			a = (a*a)%1000000007;
			b = b>>1;
		}
		
		System.out.println(answer);
	}

	static void BFS(int i, int j, char c, boolean[][] visited) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int y = now.y + dy[d];
				int x = now.x + dx[d];
				
				if(y < 0 || y >= N || x < 0 || x >= M || visited[y][x]) continue;
				visited[y][x] = true;
//				다른 타일을 만나면 표시
				if(map[y][x] != c) {
					cnt++;
					continue;
				}
				
				queue.add(new Node(y, x));
			}
		}
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}