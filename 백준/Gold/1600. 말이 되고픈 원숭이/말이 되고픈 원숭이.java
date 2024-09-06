import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
//		1x1이면 바로 도착가능
		if(M == 1 && N == 1) {
			System.out.println(0);
			return;
		}
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
//		해당 좌표에 K번 말이동을 쓰고 온 방문 배열
		boolean[][] visited = new boolean[N*M][K+1];
//		시작지점 처리
		queue.add(new Node(0, 0, 0));
		visited[0][0] = true;
		
//		BFS
		int dst = 0;
		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			
			while(L-- > 0) {
				Node now = queue.poll();
				int k = now.k;
				
//				그냥 가기
				for(int d = 0; d < 4; d++) {
					int y = now.y + dy[d];
					int x = now.x + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y*M + x][k] || map[y][x] == 1) continue;
//					도착점에 도달 가능하면 종료
					if(y == N-1 && x == M-1) {
						System.out.println(dst);
						return;
					}
					visited[y*M+x][k] = true;
					queue.add(new Node(y, x, k));
				}
				
//				이동횟수 다썼으면 말처럼 못감
				if(k == K) continue;
				
//				말처럼 가기
				for(int d = 0; d < 8; d++) {
					int y = now.y + hy[d];
					int x = now.x + hx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y*M + x][k+1] || map[y][x] == 1) continue;
//					도착점에 도달 가능하면 종료
					if(y == N-1 && x == M-1) {
						System.out.println(dst);
						return;
					}
					visited[y*M+x][k+1] = true;
					queue.add(new Node(y, x, k+1));
				}
				
			}
		}
		
		System.out.println(-1);
		
	}
	
	static class Node{
		int y, x, k;

		public Node(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
		
	}
}