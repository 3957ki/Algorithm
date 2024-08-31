import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 1, 0, -1};	//상, 우, 하, 좌
	static int[] dy = {-1, 0, 1, 0};
	static int[][] delta = {{0, -1, 2, -1},
			{-1, 1, -1, 3},
			{0, 1, 2, 3},
			{1, -1, -1, 2},
			{-1, -1, 1, 0},
			{-1, 0, 3, -1},
			{3, 2, -1, -1},
			{-1, -1, -1, -1},
	};
	static char[] roadList = {'|', '-', '+', '1', '2', '3', '4'};
	static char[][] map;
	static HashMap<Character, Integer> convert = new HashMap<>();
	static Queue<Node> blank = new ArrayDeque<>();
	static int N, M, end_x, end_y, roadCnt;
	static int answer_y, answer_x;
	static char answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		int start_x = 0;
		int start_y = 0;
		end_x = 0;
		end_y = 0;
		roadCnt = 0;

		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'M') {
					start_y = i;
					start_x = j;
				}
				else if(map[i][j] == 'Z') {
					end_y = i;
					end_x = j;
				}
				else if(map[i][j] == '.') {
					blank.add(new Node(i, j));
				}
				else if(map[i][j] == '+') roadCnt+=2;
				else roadCnt++;
			}
		}

		convert.put('|', 0);
		convert.put('-', 1);
		convert.put('+', 2);
		convert.put('1', 3);
		convert.put('2', 4);
		convert.put('3', 5);
		convert.put('4', 6);
		convert.put('.', 7);

		while(!blank.isEmpty()) {
			Node node = blank.poll();
			answer_y = node.y+1;
			answer_x = node.x+1;
			for(int i = 0; i < 7; i++) {
				map[node.y][node.x] = roadList[i];
				answer = roadList[i];
				for(int d = 0; d < 4; d++) {
					func(start_y, start_x, d, 0);
				}
			}
			map[node.y][node.x] = '.';
		}
	}
	
	static void func(int y, int x, int dir, int cnt) {
		
		int Y = y+dy[dir];
		int X = x+dx[dir];
		if(X < 0 || X >= M || Y < 0 || Y >= N) return;
		if(map[Y][X] == 'Z') {
			if(cnt != roadCnt) return;
			System.out.println(answer_y + " " + answer_x + " " + answer);
			System.exit(0);
		}
		
		if(delta[convert.get(map[Y][X])][dir] == -1) return;
		if(Y == answer_y-1 && X == answer_x-1) {
			func(Y, X, delta[convert.get(map[Y][X])][dir], cnt);
			return;
		}
		func(Y, X, delta[convert.get(map[Y][X])][dir], cnt+1);
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}