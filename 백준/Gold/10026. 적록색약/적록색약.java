import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N][N];
		
		int Cnt1 = 0;
		int Cnt2 = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				Cnt1++;
				
				char now = map[i][j];
				queue.add(new Node(i, j));
				while(!queue.isEmpty()) {
					Node node = queue.poll();
					for(int d = 0; d < 4; d++) {
						int y = node.y + dy[d];
						int x = node.x + dx[d];
						if(x < 0 || x >= N || y < 0 || y >= N || visited[y][x] || map[y][x] != now) continue;
						visited[y][x] = true;
						queue.add(new Node(y, x));
					}
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				Cnt2++;
				
				char now = map[i][j];
				if(now == 'B') {
					queue.add(new Node(i, j));
					while(!queue.isEmpty()) {
						Node node = queue.poll();
						for(int d = 0; d < 4; d++) {
							int y = node.y + dy[d];
							int x = node.x + dx[d];
							if(x < 0 || x >= N || y < 0 || y >= N || visited[y][x] || map[y][x] != 'B') continue;
							visited[y][x] = true;
							queue.add(new Node(y, x));
						}
					}
				}
				else {
					queue.add(new Node(i, j));
					while(!queue.isEmpty()) {
						Node node = queue.poll();
						for(int d = 0; d < 4; d++) {
							int y = node.y + dy[d];
							int x = node.x + dx[d];
							if(x < 0 || x >= N || y < 0 || y >= N || visited[y][x] || map[y][x] == 'B') continue;
							visited[y][x] = true;
							queue.add(new Node(y, x));
						}
					}
				}
			}
		}
		System.out.println(Cnt1 + " " + Cnt2);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		
	}
}