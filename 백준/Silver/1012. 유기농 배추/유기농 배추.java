import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		Queue<Node> queue = new LinkedList<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		for(int t = 1; t <= T; t++) {
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N+2][M+2];
			boolean[][] visited = new boolean[N+2][M+2];
			
			for(int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken())+1;
				int Y = Integer.parseInt(st.nextToken())+1;
				arr[Y][X] = 1;
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					if(visited[i][j] || arr[i][j] == 0) continue;
					queue.add(new Node(i, j));
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Node node = queue.poll();
						for(int d = 0; d < 4; d++) {
							int x = node.x+dx[d];
							int y = node.y+dy[d];
							if(!visited[y][x] && arr[y][x] == 1) {
								queue.add(new Node(y, x));
								visited[y][x] = true;
							}
						}
					}
					sum++;
				}
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node{
		int x, y;

		public Node(int y, int x) {
			this.x = x;
			this.y = y;
		}
		
	}
}
