import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//동서북남
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start_y = Integer.parseInt(st.nextToken());
		int start_x = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		위, 앞, 아래, 뒤, 왼, 오
		int[] dice = {0, 0, 0, 0, 0, 0};
		int temp;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken())-1;
			int y = start_y+dy[cmd];
			int x = start_x+dx[cmd];
			
//			이동 못하면 패스
			if(x < 0 || x >= M || y < 0 || y >= N) continue;
			
//			굴리기
			switch (cmd) {
//			동
			case 0 :
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[2];
				dice[2] = dice[5];
				dice[5] = temp;
				break;
//			서
			case 1 :
				temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[2];
				dice[2] = dice[4];
				dice[4] = temp;
				break;
//			북
			case 2 :
				temp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[3];
				dice[3] = temp;
				break;
//			남
			case 3 :
				temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[2];
				dice[2] = dice[1];
				dice[1] = temp;
				break;
				
			}
			
//			해당 칸이 0일 때
			if(map[y][x] == 0) {
				map[y][x] = dice[2];
			}
			
//			0이 아닐 때
			else {
				dice[2] = map[y][x];
				map[y][x] = 0;
			}
			sb.append(dice[0]).append('\n');
			
			start_y = y;
			start_x = x;
		}
		System.out.println(sb);
	}

}