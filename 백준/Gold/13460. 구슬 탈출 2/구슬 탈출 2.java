import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		int ry = 0;
		int rx = 0;
		int by = 0;
		int bx = 0;
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					ry = i;
					rx = j;
				}
				if(map[i][j] == 'B') {
					by = i;
					bx = j;
				}
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		boolean[][] visited = new boolean[N*M][N*M];
		queue.add(new Node(ry, rx, by, bx));
		visited[ry*M+rx][by*M+bx] = true;
		
//		BFS
		int time = 0;
		while(!queue.isEmpty()) {
//			시간이 10보다 크면 -1 반환
			if(++time > 10) {
				System.out.println(-1);
				return;
			}
			int L = queue.size();
			while(L-- > 0) {
				Node node = queue.poll();
				ry = node.ry;
				rx = node.rx;
				by = node.by;
				bx = node.bx;
//				4방향으로 기울여보기
				for(int d = 0; d < 4; d++) {
					int exit = 0;
//					빨간색 먼저
					int t_ry = ry;
					int t_rx = rx;
					boolean flagR = false;
					while(map[t_ry][t_rx] != '#') {
//						구멍에 빠지면 성공
						if(map[t_ry][t_rx] == 'O') {
							exit = 1;
						}
//						이동중에 파란색이 있으면
						if(t_ry == by && t_rx == bx) flagR = true;
						t_ry += dy[d];
						t_rx += dx[d];
					}
					if(flagR) {
						t_ry -= dy[d];
						t_rx -= dx[d];
					}
					t_ry -= dy[d];
					t_rx -= dx[d];
					
//					파란색
					int t_by = by;
					int t_bx = bx;
					boolean flagB = false;
					while(map[t_by][t_bx] != '#') {
//						구멍에 빠지면 실패
						if(map[t_by][t_bx] == 'O') {
							exit = -1;
						}
//						이동중에 빨간색이 있으면
						if(t_by == ry && t_bx == rx) flagB = true;
						t_by += dy[d];
						t_bx += dx[d];
					}
					if(flagB) {
						t_by -= dy[d];
						t_bx -= dx[d];
					}
					t_by -= dy[d];
					t_bx -= dx[d];
//					빨간색만 구멍에 빠진거면 성공
					if(exit == 1) {
						System.out.println(time);
						return;
					}
//					파란색이 구멍에 빠진거면 패스
					if(exit == -1) continue;
//					방문처리
					if(visited[t_ry*M+t_rx][t_by*M+t_bx]) continue;
					visited[t_ry*M+t_rx][t_by*M+t_bx] = true;
//					구멍에 빠진게 없으면 다음 탐색
					queue.add(new Node(t_ry, t_rx, t_by, t_bx));
				}
			}
		}
		
		System.out.println(-1);
		
	}

	static class Node{
		int ry, rx, by, bx;

		public Node(int ry, int rx, int by, int bx) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
		}
		
	}
}