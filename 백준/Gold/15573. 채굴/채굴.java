import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+2][M+2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		바닥
		for(int i = 0; i <= M+1; i++) {
			map[N+1][i] = -1;
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited;
		
		int low = 1;
		int high = 1000000;
		int mid;
		int answer = Integer.MAX_VALUE;
		
//		이분탐색으로 D찾기
		A: while(low <= high) {
			mid = (low+high)/2;
			
//			BFS
			Queue<Node> queue = new ArrayDeque<>();
			visited = new boolean[N+2][M+2];
			visited[0][0] = true;
			queue.add(new Node(0, 0));
			int cnt = 0;
			
			while(!queue.isEmpty()) {
				Node now = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int y = now.y + dy[d];
					int x = now.x + dx[d];
					
					if(y < 0 || y > N+1 || x < 0 || x > M+1 || visited[y][x] || map[y][x] == -1 || map[y][x] > mid) continue;
					visited[y][x] = true;
					queue.add(new Node(y, x));
//					빈자리면 패스
					if(map[y][x] == 0) continue;
					
//					광물 수가 K이상이면 갱신
					if(++cnt >= K) {
						answer = Math.min(answer, mid);
						high = mid - 1;
						continue A;
					}
				}
			}
			low = mid + 1;
		}
		System.out.println(answer);
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