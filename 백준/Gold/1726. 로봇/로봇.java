import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) map[i][j] = true;
			}
		}
		
		Map<String, Integer> convert = new HashMap<>();
		convert.put("1", 0);
		convert.put("2", 2);
		convert.put("3", 1);
		convert.put("4", 3);
		
		st = new StringTokenizer(br.readLine());
		Node start = new Node(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				convert.get(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Node end = new Node(Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				convert.get(st.nextToken()));
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N+1][M+1][4];
//		동, 남, 서, 북
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		queue.add(start);
		visited[start.y][start.x][start.dir] = true;
		
		int answer = 0;
		while(!queue.isEmpty()) {
			int L = queue.size();
			
			while(L-- > 0) {
				Node now = queue.poll();
				
//				종료
				if(now.y == end.y && now.x == end.x && now.dir == end.dir) {
					System.out.println(answer);
					return;
				}
				
//				직진
				A: for(int k = 1; k <= 3; k++) {
					int y = now.y + dy[now.dir]*k;
					int x = now.x + dx[now.dir]*k;
					
					if(y <= 0 || y > N || x <= 0 || x > M || visited[y][x][now.dir]) continue;
					
					for(int t = 1; t <= k; t++) {
						if(!map[now.y+dy[now.dir]*t][now.x+dx[now.dir]*t]) continue A;
					}
					
					visited[y][x][now.dir] = true;
					queue.add(new Node(y, x, now.dir));
				}
				
				int y = now.y;
				int x = now.x;
//				회전
				for(int d = 1; d <= 3; d+=2) {
					int dir = (now.dir+d)%4;
					
					if(y <= 0 || y > N || x <= 0 || x > M || visited[y][x][dir]) continue;
					visited[y][x][dir] = true;
					queue.add(new Node(y, x, dir));
				}
			}
			answer++;
		}
	}

	static class Node{
		int y, x, dir;

		public Node(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
		
	}
}