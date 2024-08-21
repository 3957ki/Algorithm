import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		st = new StringTokenizer(br.readLine());
		for(int r = 0; r < R; r++) {
			int cmd = Integer.parseInt(st.nextToken());

			switch(cmd) {
			case 1:
				for(int j = 0; j < M; j++) {
					int start = 0;
					int end = N-1;
					while(start < end) {
						int temp = map[start][j];
						map[start][j] = map[end][j];
						map[end][j] = temp;
						start++;
						end--;
					}
				}
				break;
			case 2:
				for(int i = 0; i < N; i++) {
					int start = 0;
					int end = M-1;
					while(start < end) {
						int temp = map[i][start];
						map[i][start] = map[i][end];
						map[i][end] = temp;
						start++;
						end--;
					}
				}
				
				break;
			case 3:
				int[][] temp3 = new int[M][N];
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < M; j++) {
						temp3[j][N-1-i] = map[i][j];
					}
				}
				map = temp3;
				int t3 = N;
				N = M;
				M = t3;
				break;
			case 4:
				int[][] temp4 = new int[M][N];
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < M; j++) {
						temp4[M-1-j][i] = map[i][j];
					}
				}
				map = temp4;
				int t4 = N;
				N = M;
				M = t4;
				
				break;
			case 5:
				int[][] temp5 = new int[N][M];
				for(int i = 0; i < N/2; i++) {
					for(int j = 0; j < M/2; j++) {
						temp5[i][j+M/2] = map[i][j];
					}
				}
				for(int i = 0; i < N/2; i++) {
					for(int j = M/2; j < M; j++) {
						temp5[i+N/2][j] = map[i][j];
					}
				}
				for(int i = N/2; i < N; i++) {
					for(int j = M/2; j < M; j++) {
						temp5[i][j-M/2] = map[i][j];
					}
				}
				for(int i = N/2; i < N; i++) {
					for(int j = 0; j < M/2; j++) {
						temp5[i-N/2][j] = map[i][j];
					}
				}
				map = temp5;
				
				break;
			case 6:
				int[][] temp6 = new int[N][M];
				for(int i = 0; i < N/2; i++) {
					for(int j = 0; j < M/2; j++) {
						temp6[i+N/2][j] = map[i][j];
					}
				}
				for(int i = 0; i < N/2; i++) {
					for(int j = M/2; j < M; j++) {
						temp6[i][j-M/2] = map[i][j];
					}
				}
				for(int i = N/2; i < N; i++) {
					for(int j = M/2; j < M; j++) {
						temp6[i-N/2][j] = map[i][j];
					}
				}
				for(int i = N/2; i < N; i++) {
					for(int j = 0; j < M/2; j++) {
						temp6[i][j+M/2] = map[i][j];
					}
				}
				map = temp6;
				
				break;
			}
		}

		for(int[] a : map) {
			for(int num : a) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}