import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M+1];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		int start_y = Integer.parseInt(st.nextToken());
		int start_x = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int end_y = Integer.parseInt(st.nextToken());
		int end_x = Integer.parseInt(st.nextToken());
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N+1][M+1];
		queue.add(new Node(start_y, start_x));
		visited[start_y][start_x] = true;
		
		
		int answer = 0;
		while(!queue.isEmpty()) {
			int L = queue.size();
			answer++;
			
			for(int k = 0; k < L; k++) {
				Node node = queue.poll();
				A: for(int d = 0; d < 4; d++) {
					int y = node.y + dy[d];
					int x = node.x + dx[d];
					if(x <= 0 || x > M || y <= 0 || y > N || visited[y][x]) continue;
					if(d == 0) {
						if(y+A > N+1) continue;
						for(int i = x; i < x + B; i++) {
							if(map[y+A-1][i] == 1) continue A;
						}
						visited[y][x] = true;
						queue.add(new Node(y, x));
						if(y == end_y && x == end_x) {
							System.out.println(answer);
							return;
						}
					}
					else if(d == 1) {
						if(y <= 0) continue;
						for(int i = x; i < x + B; i++) {
							if(map[y][i] == 1) continue A;
						}
						visited[y][x] = true;
						queue.add(new Node(y, x));
						if(y == end_y && x == end_x) {
							System.out.println(answer);
							return;
						}
					}
					else if(d == 2) {
						if(x+B > M+1) continue;
						for(int i = y; i < y + A; i++) {
							if(map[i][x+B-1] == 1) continue A;
						}
						visited[y][x] = true;
						queue.add(new Node(y, x));
						if(y == end_y && x == end_x) {
							System.out.println(answer);
							return;
						}
					}
					else if(d == 3) {
						if(x <= 0) continue;
						for(int i = y; i < y + A; i++) {
							if(map[i][x] == 1) continue A;
						}
						visited[y][x] = true;
						queue.add(new Node(y, x));
						if(y == end_y && x == end_x) {
							System.out.println(answer);
							return;
						}
					}
				}
			}
			
		}
		System.out.println(-1);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}