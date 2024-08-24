import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];
			for(int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			int tank_y = 0;
			int tank_x = 0;
			
			A: for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						tank_y = i;
						tank_x = j;
						break A;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			for(int i = 0; i < str.length(); i++) {
				char cmd = str.charAt(i);
				
				switch (cmd) {
				case 'U':
//					위로 방향 돌리기
					map[tank_y][tank_x] = '^';
//					위로 갈 수 있으면
					if(tank_y-1 >= 0 && map[tank_y-1][tank_x] == '.') {
						map[tank_y][tank_x] = '.';
						map[--tank_y][tank_x] = '^';
					}
					break;
				
				case 'D':
					map[tank_y][tank_x] = 'v';
					if(tank_y+1 < H && map[tank_y+1][tank_x] == '.') {
						map[tank_y][tank_x] = '.';
						map[++tank_y][tank_x] = 'v';
					}
					break;
					
				case 'L':
					map[tank_y][tank_x] = '<';
					if(tank_x-1 >= 0 && map[tank_y][tank_x-1] == '.') {
						map[tank_y][tank_x] = '.';
						map[tank_y][--tank_x] = '<';
					}
					break;
					
				case 'R':
					map[tank_y][tank_x] = '>';
					if(tank_x+1 < W && map[tank_y][tank_x+1] == '.') {
						map[tank_y][tank_x] = '.';
						map[tank_y][++tank_x] = '>';
					}
					break;
					
				case 'S':
					if(map[tank_y][tank_x] == '^') {
						int k = 1;
						while(tank_y - k >= 0) {
//							강철을 만나면 멈추기
							if(map[tank_y - k][tank_x] == '#') break;
//							벽돌을 만나면 평지로 만들기
							if(map[tank_y - k][tank_x] == '*') {
								map[tank_y - k][tank_x] = '.';
								break;
							}
							k++;
						}
					}
					else if(map[tank_y][tank_x] == 'v') {
						int k = 1;
						while(tank_y + k < H) {
							if(map[tank_y + k][tank_x] == '#') break;
							if(map[tank_y + k][tank_x] == '*') {
								map[tank_y + k][tank_x] = '.';
								break;
							}
							k++;
						}
					}
					else if(map[tank_y][tank_x] == '<') {
						int k = 1;
						while(tank_x - k >= 0) {
							if(map[tank_y][tank_x - k] == '#') break;
							if(map[tank_y][tank_x - k] == '*') {
								map[tank_y][tank_x - k] = '.';
								break;
							}
							k++;
						}
					}
					else if(map[tank_y][tank_x] == '>') {
						int k = 1;
						while(tank_x + k < W) {
							if(map[tank_y][tank_x + k] == '#') break;
							if(map[tank_y][tank_x + k] == '*') {
								map[tank_y][tank_x + k] = '.';
								break;
							}
							k++;
						}
					}
					break;
				}
			}
			
			sb.append('#').append(t).append(' ');
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

}