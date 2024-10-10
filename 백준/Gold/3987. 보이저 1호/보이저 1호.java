import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		st = new StringTokenizer(br.readLine());
		int startY = Integer.parseInt(st.nextToken())-1;
		int startX = Integer.parseInt(st.nextToken())-1;
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		HashMap<Integer, Character> convert = new HashMap<>();
		convert.put(0, 'U');
		convert.put(1, 'R');
		convert.put(2, 'D');
		convert.put(3, 'L');
		
		int[][] convertDir = {	{1, 0, 3, 2},
								{3, 2, 1, 0},
								};
		
		int answer = 0;
		char answerDir = 0;
		
		for(int dir = 0; dir < 4; dir++) {
			int time = 0;
			int y = startY;
			int x = startX;
			int d = dir;
			while(true) {
				y += dy[d];
				x += dx[d];
				time++;
				if(y == startY && x == startX && d == dir) {
					System.out.println(convert.get(dir));
					System.out.println("Voyager");
					System.exit(0);
				}
				if(y < 0 || y >= N || x < 0 || x >= M || map[y][x] == 'C') break;
				if(map[y][x] == '/') {
					d = convertDir[0][d];
				}
				else if(map[y][x] == '\\') {
					d = convertDir[1][d];
				}
			}
			if(answer < time) {
				answer = time;
				answerDir = convert.get(dir);
			}
		}
		
		System.out.println(answerDir);
		System.out.println(answer);
	}

}