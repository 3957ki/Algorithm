import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int N;
	static int score = 0;
	static int answer = 0;
	static int startY, startX;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t <= T; t++) {
			answer = 0;
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] != 0) continue;
					startY = i;
					startX = j;
					for(int k = 0; k < 4; k++) {
						score = 0;
						play(i, j, k);
					}
				}
			}
//			System.out.println("#"+t+" "+ answer);
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void play(int y, int x, int d) {
		int X = x + dx[d];
		int Y = y + dy[d];
		int dir = d;
		while(true) {
			if(X < 0) {
				score++;
				dir = 2;
				X += dx[dir];
				Y += dy[dir];
				continue;
			}
			if(X >= N) {
				score++;
				dir = 3;
				X += dx[dir];
				Y += dy[dir];
				continue;
			}
			if(Y < 0) {
				score++;
				dir = 0;
				X += dx[dir];
				Y += dy[dir];
				continue;
			}
			if(Y >= N) {
				score++;
				dir = 1;
				X += dx[dir];
				Y += dy[dir];
				continue;
			}
			
			if((X == startX && Y == startY) || arr[Y][X] == -1) {
				answer = Math.max(answer, score);
				break;
			}
			
			if(arr[Y][X] == 0) {
				X += dx[dir];
				Y += dy[dir];
				continue;
			}
			
			if(arr[Y][X] == 1) {
				score++;
				if(dir == 0) {
					dir = 2;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 1) {
					dir = 0;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 2) {
					dir = 3;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 3) {
					dir = 1;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
			}
			if(arr[Y][X] == 2) {
				score++;
				if(dir == 0) {
					dir = 1;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 1) {
					dir = 2;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 2) {
					dir = 3;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 3) {
					dir = 0;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
			}
			if(arr[Y][X] == 3) {
				score++;
				if(dir == 0) {
					dir = 1;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 1) {
					dir = 3;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 2) {
					dir = 0;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 3) {
					dir = 2;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
			}
			if(arr[Y][X] == 4) {
				score++;
				if(dir == 0) {
					dir = 3;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 1) {
					dir = 0;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 2) {
					dir = 1;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 3) {
					dir = 2;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
			}
			if(arr[Y][X] == 5) {
				score++;
				if(dir == 0) {
					dir = 1;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 1) {
					dir = 0;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 2) {
					dir = 3;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
				if(dir == 3) {
					dir = 2;
					X += dx[dir];
					Y += dy[dir];
					continue;
				}
			}
			
			if(arr[Y][X] > 5) {
				int num = arr[Y][X];
				A: for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(arr[i][j] == num && !(i == Y && j == X)) {
							X = j+dx[dir];
							Y = i+dy[dir];
							break A;
						}
					}
				}
			}
		}
		
	}
}