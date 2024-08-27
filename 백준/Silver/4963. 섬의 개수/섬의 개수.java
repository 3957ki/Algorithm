import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			if(W == 0 && H == 0) break;
			
			int[][] map = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
			int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
			Queue<Node> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[H][W];
			
			int answer = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(visited[i][j] || map[i][j] == 0) continue;
					answer++;
					queue.add(new Node(i, j));
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Node node = queue.poll();
						for(int d = 0; d < 8; d++) {
							int y = node.y + dy[d];
							int x = node.x + dx[d];
							if(x < 0 || x >= W || y < 0 || y >= H || visited[y][x] || map[y][x] == 0) continue;
							visited[y][x] = true;
							queue.add(new Node(y, x));
							
						}
					}
				}
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}