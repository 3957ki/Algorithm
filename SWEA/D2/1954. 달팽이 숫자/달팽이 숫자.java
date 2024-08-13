import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append('\n');
			int N = Integer.parseInt(br.readLine());
			int L = N*N;
			int[][] arr = new int[N][N];
			int[] dx = {1, 0, -1, 0};
			int[] dy = {0, 1, 0, -1};
			int x = 0;
			int y = 0;
			int d = 0;
			arr[0][0] = 1;
			for(int i = 2; i <= L; i++) {
				int X = x+dx[d];
				int Y = y+dy[d];
				if(X < 0 || X >= N || Y < 0 || Y >= N || arr[Y][X] != 0) {
					d = (d+1)%4;
				}
				X = x+dx[d];
				Y = y+dy[d];
				arr[Y][X] = i;
				x = X;
				y = Y;
			}
			for(int[] a : arr) {
				for(int num : a) {
					sb.append(num).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

}