import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for(int r = 0; r < R; r++) {
			int now = arr[0][0];
			boolean[][] visited = new boolean[N][M];
			int y = 0;
			int x = 0;
			int d = 0;
			visited[y][x] = true;
			while(true) {
				int Y = y+dy[d];
				int X = x+dx[d];
				if(X < 0 || X >= M || Y < 0 || Y >= N) {
					d = (d+1)%4;
					continue;
				}
				if(d != 3 && visited[Y][X]) {
					d = (d+1)%4;
					continue;
				}
				if(visited[Y][X]) {
					arr[Y][X] = now;
					d = (d+1)%4;
					Y = y+dy[d];
					X = x+dx[d];
					if(visited[Y][X]) break;
					now = arr[Y][X];
					y = Y;
					x = X;
					visited[Y][X] = true;
					continue;
				}
				int temp = arr[Y][X];
				arr[Y][X] = now;
				now = temp;
				y = Y;
				x = X;
				visited[Y][X] = true;
			}
		}
		
		for(int[] a : arr) {
			for(int num : a) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}