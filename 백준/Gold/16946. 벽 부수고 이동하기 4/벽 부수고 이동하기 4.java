import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		//		원본 맵
		int[][] map = new int[N][M];
		//		벽에 어떤 구역이 닿는지 보는 맵
		Set<Integer>[][] temp = new HashSet[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = new HashSet<>();
			}
		}
		//		빈 구역 번호별 영역 크기
		int[] place = new int[1000001];
		//		벽 리스트
		Queue<Node> wallList = new ArrayDeque<>();
		//		빈 공간 리스트
		Queue<Node> blankList = new ArrayDeque<>();

		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				if(map[i][j] == 1) {
					wallList.add(new Node(i, j));
				}
				if(map[i][j] == 0) {
					blankList.add(new Node(i, j));
				}
			}
		}

		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N][M];
		
		//flood fill
		int num = 0;

		while(!blankList.isEmpty()) {
			Node blank = blankList.poll();
			//해당 빈 공간이 이미 처리됐다면 패스
			if(visited[blank.y][blank.x]) continue;
			visited[blank.y][blank.x] = true;
			num++;
			queue.add(blank);
			int cnt = 1;
			while(!queue.isEmpty()) {
				Node node = queue.poll();

				for(int d = 0; d < 4; d++) {
					int y = node.y + dy[d];
					int x = node.x + dx[d];
					if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x]) continue;
					//벽에 기록하기
					if(map[y][x] == 1) {
						temp[y][x].add(num);
						continue;
					}
					visited[y][x] = true;
					queue.add(new Node(y, x));
					cnt++;
				}
			}
			place[num] = cnt;
		}
		
		//벽 확인
		while(!wallList.isEmpty()) {
			Node wall = wallList.poll();
			int cnt = 1;
			for(Integer n : temp[wall.y][wall.x]) {
				cnt+= place[n];
			}
			map[wall.y][wall.x] = cnt%10;
		}

		for(int[] a : map) {
			for(int n : a) {
				sb.append(n);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}