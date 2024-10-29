import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parent;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = R*C;
		Node[] arr = new Node[N];
		int[][] map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i*C+j] = new Node(i, j, val);
				map[i][j] = val;
			}
		}
		
//		오름차순
		Arrays.sort(arr, (o1, o2) -> o1.val-o2.val);
		
		parent = new int[300001];
		Arrays.fill(parent, -1);
		
		boolean[] visited = new boolean[300001];
		
		for(Node now : arr) {
			int y = now.y;
			int x = now.x;
			visited[now.val] = true;
			for(int d = 0; d < 8; d++) {
				int Y = y + dy[d];
				int X = x + dx[d];
				if(Y < 0 || Y >= R || X < 0 || X >= C || visited[map[Y][X]]) continue;
				visited[map[Y][X]] = true;
				
				union(Math.min(map[y][x], map[Y][X]), Math.max(map[y][x], map[Y][X]));
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
//				자신이 루트가 아니면 0
				if(parent[map[i][j]] >= 0) {
					map[i][j] = 0;
				}
				else{
					map[i][j] = -parent[map[i][j]];
				}
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static int find(int a) {
		if(parent[a] < 0) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parent[aRoot] += parent[bRoot];
		parent[bRoot] = aRoot;
		return true;
	}
	
	static class Node{
		int y, x, val;

		public Node(int y, int x, int val) {
			super();
			this.y = y;
			this.x = x;
			this.val = val;
		}
		
	}

}