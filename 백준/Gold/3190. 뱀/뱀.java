import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Map<Character, Integer> convert = new HashMap<>();
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		convert.put('D', 1);
		convert.put('L', 3);
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
//			사과
			map[y][x] = 1;
		}
		
//		뱀
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(1, 1));
		map[1][1] = 2;
		int dir = 0;
		int answer = 0;
		int y = 1;
		int x = 1;
		
		int L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int change = convert.get(st.nextToken().charAt(0));
			
			while(answer < time) {
				answer++;
				y += dy[dir];
				x += dx[dir];
//				이동중 박으면 죽음
				if(y <= 0 || y > N || x <= 0 || x > N || map[y][x] == 2) {
					System.out.println(answer);
					return;
				}
				
//				빈 공간이면 꼬리 자르기
				if(map[y][x] == 0) {
					Node tail = queue.poll();
					map[tail.y][tail.x] = 0;
				}
				
				map[y][x] = 2;
				queue.add(new Node(y, x));
			}
//			방향 전환
			dir = (dir+change)%4;
		}
		
		while(true) {
			answer++;
			y += dy[dir];
			x += dx[dir];
//			이동중 박으면 죽음
			if(y <= 0 || y > N || x <= 0 || x >= N || map[y][x] == 2) {
				System.out.println(answer);
				return;
			}
			map[y][x] = 2;
			queue.add(new Node(y, x));
			
//			빈 공간이면 꼬리 자르기
			if(map[y][x] == 0) {
				Node tail = queue.poll();
				map[tail.y][tail.x] = 0;
			}
		}
		
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