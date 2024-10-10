import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int total = 0;
		Node up = null;
		Node down = null;
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		int[][] map = new int[N][M];
		int[][] temp = new int[N][M];
		
		for(int i = 0; i < N; i++) {			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				미세먼지 양 더하기
				if(map[i][j] > 0) total+=map[i][j];
//				공청기 위치
				if(map[i][j] == -1) {
					temp[i][j] = -1;
					if(up == null) up = new Node(i, j);
					else down = new Node(i, j);
				}
			}
		}
		
		while(T-- > 0) {
//			확산
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
//					먼지를 만나면 확산
					if(map[i][j] > 0) {
						int value = map[i][j]/5;
						temp[i][j] += map[i][j];
						for(int d = 0; d < 4; d++) {
							int y = i + dy[d];
							int x = j + dx[d];
							
							if(x < 0 || x >= M || y < 0 || y >= N || map[y][x] == -1) continue;
							temp[y][x]+=value;
							temp[i][j]-=value;
						}
						map[i][j] = 0;
					}
				}
			}
//			회전
			total-=temp[up.y-1][0];
			for(int i = up.y-1; i > 0; i--) {
				temp[i][0] = temp[i-1][0];
			}
			for(int i = 0; i < M-1; i++) {
				temp[0][i] = temp[0][i+1];
			}
			for(int i = 0; i < up.y; i++) {
				temp[i][M-1] = temp[i+1][M-1];
			}
			for(int i = M-1; i > 0; i--) {
				temp[up.y][i] = temp[up.y][i-1];
			}
			temp[up.y][1] = 0;
			
			total-=temp[down.y+1][0];
			for(int i = down.y+1; i < N-1; i++) {
				temp[i][0] = temp[i+1][0];
			}
			for(int i = 0; i < M-1; i++) {
				temp[N-1][i] = temp[N-1][i+1];
			}
			for(int i = N-1; i > down.y; i--) {
				temp[i][M-1] = temp[i-1][M-1];
			}
			for(int i = M-1; i > 0; i--) {
				temp[down.y][i] = temp[down.y][i-1];
			}
			temp[down.y][1] = 0;
			
			int[][] t = map;
			map = temp;
			temp = t;
		}
		System.out.println(total);
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