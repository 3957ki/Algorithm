import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int[][] water = new int[N][M];
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		Node start = null;
		Node end = null;
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
//				시작점
				if(map[i][j] == 'S') {
					start = new Node(i, j);
				}
//				도착점
				if(map[i][j] == 'D') {
					end = new Node(i, j);
				}
//				물
				if(map[i][j] == '*') {
					water[i][j] = 1;
					queue.add(new Node(i, j));
					visited[i][j] = true;
				}
			}
		}
		
//		물 흐르기까지 걸린 시간 좌표별로 표시하기
		int dst = 0;
		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			while(L-- > 0) {
				Node now = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int y = now.y+dy[d];
					int x = now.x+dx[d];
					
					if(y < 0 || y >= N || x < 0 || x >= M || visited[y][x]) continue;
					if(map[y][x] == '.') {
						queue.add(new Node(y, x));
						visited[y][x] = true;
						water[y][x] = dst;
					}
				}
				
			}
		}
		
		visited = new boolean[N][M];
		queue.add(start);
		visited[start.y][start.x] = true;
		
		dst = 0;
		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			while(L-- > 0) {
				Node now = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int y = now.y+dy[d];
					int x = now.x+dx[d];
					
					if(y < 0 || y >= N || x < 0 || x >= M || visited[y][x]) continue;
					if(map[y][x] == 'D') {
						System.out.println(dst);
						return;
					}
					if(map[y][x] == '.' && (water[y][x] > dst || water[y][x] == 0)) {
						queue.add(new Node(y, x));
						visited[y][x] = true;
					}
				}
				
			}
		}
		System.out.println("KAKTUS");
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
