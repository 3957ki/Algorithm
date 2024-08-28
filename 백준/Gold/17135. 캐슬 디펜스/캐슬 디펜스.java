import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int N, M, D, answer, cnt, enemyCnt;
	static int[][] map, temp_map;
	static int[] temp = new int[3];
	static Queue<Node> queue = new ArrayDeque<>();
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];
		temp_map = new int[N+1][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp_map[i][j] = map[i][j];
				if(map[i][j] == 1) {
					enemyCnt++;
				}
			}
		}
		
		comb(0, 0);
		System.out.println(answer);
	}

	static void comb(int cnt, int start) {
		if(cnt == 3) {
			Main.cnt = 0;
			BFS();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = temp_map[i][j];
				}
			}
			return;
		}
		
		for(int i = start; i < M; i++) {
			temp[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	static void BFS() {
		Set<Integer> enemies = new HashSet<>();
		for(int k = 0; k < 3; k++) {
			boolean[][] visited = new boolean[N+1][M];
			queue.add(new Node(N, temp[k]));
			visited[N][temp[k]] = true;
			
			int dst = 0;
			int enemy_y = Integer.MAX_VALUE;
			int enemy_x = Integer.MAX_VALUE;
			boolean flag = false;
			while(!queue.isEmpty()) {
				dst++;
				if(dst > D) {
					queue.clear();
					break;
				}
				int L = queue.size();
				for(int i = 0; i < L; i++) {
					Node node = queue.poll();
					for(int d = 0; d < 4; d++) {
						int y = node.y + dy[d];
						int x = node.x + dx[d];
						if(x < 0 || x >= M || y < 0 || y > N || visited[y][x]) continue;
						if(map[y][x] == 1) {
							flag = true;
							if(enemy_x > x) {
								enemy_y = y;
								enemy_x = x;
							}
						}
						visited[y][x] = true;
						queue.add(new Node(y, x));
					}
				}
				if(flag) {
					enemies.add(enemy_y*M + enemy_x);
					queue.clear();
				}
			}
		}
		
		for(Integer num : enemies) {
			int y = num/M;
			int x = num%M;
			map[y][x] = 0;
			cnt++;
		}
		
		if(cnt == enemyCnt) {
			answer = cnt;
			System.out.println(answer);
			System.exit(0);
		}
		move();
	}
	
	static void move() {
		N--;
		if(N == 0) {
			answer = Math.max(answer, cnt);
			N++;
			return;
		}
		for(int i = 0; i < M; i++) {
			map[N][i] = 0;
		}
		BFS();
		N++;
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}