import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<int[]> blankList = new ArrayList<>();
	static int N, M, L;
	static int[][] map;
	static ArrayDeque<Node> queue = new ArrayDeque<>();
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					blankList.add(new int[] {i, j});
				}
			}
		}
		L = blankList.size();
		
		DFS(0, 0);
		System.out.println(answer);
	}
	
	static void DFS(int cnt, int start) {
		if(cnt == 3) {
			BFS();
			return;
		}
		
		for(int i = start; i < L; i++) {
			map[blankList.get(i)[0]][blankList.get(i)[1]] = 1;
			DFS(cnt+1, i+1);
			map[blankList.get(i)[0]][blankList.get(i)[1]] = 0;
		}
	}
	
	static void BFS() {
		boolean[][] visited = new boolean[N][M];
		int cnt = L-3;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] || map[i][j] != 2) continue;
				visited[i][j] = true;
				queue.addLast(new Node(i, j));
				while(!queue.isEmpty()) {
					Node node = queue.removeFirst();
					for(int d = 0; d < 4; d++) {
						int y = node.y + dy[d];
						int x = node.x + dx[d];
						if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x] || map[y][x] != 0) continue;
						visited[y][x] = true;
						cnt--;
						queue.addLast(new Node(y, x));
					}
				}
			}
		}
		answer = Math.max(answer, cnt);
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}