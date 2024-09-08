import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Queue<Node> queue = new ArrayDeque<>();
//	행이 작은 순, 같다면 열이 작은 순
	static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
		if(o1.y == o2.y) {
			return o1.x - o2.x;
		}
		return o1.y - o2.y;
	});
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N, answer;
	static int[][] map;
	static Shark shark;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		
//		상어 위치, 크기, 먹은 먹이 개수
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			if(!BFS()) break;
		}
		
		System.out.println(answer);
		
	}
	
	static boolean BFS() {
		boolean[][] visited = new boolean[N][N];
		queue.add(new Node(shark.y, shark.x));
		visited[shark.y][shark.x] = true;
		
		int time = 0;
		while(!queue.isEmpty()) {
			time++;
			int L = queue.size();
			while(L-- > 0) {
				Node now = queue.poll();
				for(int d = 0; d < 4; d++) {
					int y = now.y + dy[d];
					int x = now.x + dx[d];
					if(x < 0 || x >= N || y < 0 || y >= N || visited[y][x] || shark.z < map[y][x]) continue;
					visited[y][x] = true;
					queue.add(new Node(y, x));
//					빈공간이면 이동만
					if(map[y][x] == 0) continue;
//					상어가 더 크다면 pq에 넣기
					if(map[y][x] < shark.z) {
						pq.add(new Node(y, x));
					}
				}
			}
//			먹을 물고기가 있다면 먹기
			if(!pq.isEmpty()) {
				Node fish = pq.poll();
				map[fish.y][fish.x] = 0;
//				이동
				shark.y = fish.y;
				shark.x = fish.x;
//				자기 크기 만큼 먹었다면
				if(++shark.cnt == shark.z) {
					shark.z++;
					shark.cnt = 0;
				}
//				시간 저장
				answer+=time;
//				초기화
				queue.clear();
				pq.clear();
				return true;
			}
		}
//		먹을 물고기가 없다면 false
		return false;
	}

	static class Shark{
		int y, x, z, cnt;

		public Shark(int y, int x, int z, int cnt) {
			this.y = y;
			this.x = x;
			this.z = z;
			this.cnt = cnt;
		}
		
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}