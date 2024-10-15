import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M+1];
		char[][] check = new char[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(check[i], 'S');
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		HashMap<String, Integer> convert = new HashMap<>();
		convert.put("S", 0);
		convert.put("N", 1);
		convert.put("E", 2);
		convert.put("W", 3);
		
		int score = 0;
		
		for(int i = 0; i < R; i++) {
//			공격
			st = new StringTokenizer(br.readLine());
			int ay = Integer.parseInt(st.nextToken());
			int ax = Integer.parseInt(st.nextToken());
			int d = convert.get(st.nextToken());
			
//			넘어진게 아니라면
			if(check[ay][ax] == 'S') {
				int Y = ay + dy[d];
				int X = ax + dx[d];
				int value = map[ay][ax]-1;
//				넘어짐 처리
				score++;
				check[ay][ax] = 'F';
				while(value-- > 0) {
					if(X > M || X <= 0 || Y > N || Y <= 0) break;
//					넘어진 애가 아니라면
					if(check[Y][X] == 'S') {
						score++;
						check[Y][X] = 'F';
						value = Math.max(value, map[Y][X]-1);
					}
					
					Y+=dy[d];
					X+=dx[d];
				}
			}
			
//			수비
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			check[y][x] = 'S';
		}
		sb.append(score).append('\n');
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				sb.append(check[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}