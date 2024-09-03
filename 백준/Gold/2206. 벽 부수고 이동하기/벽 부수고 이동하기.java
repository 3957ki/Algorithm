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
		
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		int[][] map = new int[N][M];
		int[][] temp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N][M];
		
//		시작지점에서 BFS
		queue.add(new Node(0, 0));
		visited[0][0] = true;
		
		int answer = Integer.MAX_VALUE;
		int dst = 1;
		while(!queue.isEmpty()){
			dst++;
			int L = queue.size();
			while(L-- > 0) {
				Node node = queue.poll();
				for(int d = 0; d < 4; d++) {
					int y = node.y + dy[d];
					int x = node.x + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x]) continue;
					visited[y][x] = true;
					if(x == M-1 && y == N-1) {
						answer = Math.min(answer, dst+1);
					}
					if(map[y][x] == 0) {
						queue.add(new Node(y, x));
					}
					else if(map[y][x] == 1) {
						temp[y][x] = dst;
					}
					
				}
			}
		}
		
		
//		메모 끝
		
		
//		도착점에서 BFS
		visited = new boolean[N][M];
		queue.add(new Node(N-1, M-1));
		visited[N-1][M-1] = true;
		
		dst = 1;
		while(!queue.isEmpty()){
			dst++;
			int L = queue.size();
			while(L-- > 0) {
				Node node = queue.poll();
				for(int d = 0; d < 4; d++) {
					int y = node.y + dy[d];
					int x = node.x + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x]) continue;
					visited[y][x] = true;
					if(map[y][x] == 0) {
						queue.add(new Node(y, x));
					}
					else if(map[y][x] == 1) {
						if(temp[y][x] > 0) {
							answer = Math.min(answer, dst+temp[y][x]);
						}
					}
					
				}
			}
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer-1);
		
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}