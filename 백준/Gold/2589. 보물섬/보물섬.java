import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		ArrayDeque<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		int answer = 0;
//		탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
//				BFS
				if(arr[i][j] != 'L') continue;
				boolean[][] visited = new boolean[N][M];
				queue.addLast(new Node(i, j, 0));
				visited[i][j] = true;
				int distance = 0;
				while(!queue.isEmpty()) {
					Node node = queue.removeFirst();
					distance = node.d;
					for(int d = 0; d < 4; d++) {
						int x = node.x + dx[d];
						int y = node.y + dy[d];
						if(x < 0 || x >= M || y < 0 || y >= N) continue;
						if(!visited[y][x] && arr[y][x] == 'L') {
							queue.addLast(new Node(y, x, distance+1));
							visited[y][x] = true;
						}
					}
				}
//				가장 긴 거리 저장
				answer = Math.max(answer, distance);
			}
		}
		System.out.println(answer);
	}
	
	static class Node{
		int x, y, d;

		public Node(int y, int x, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
}