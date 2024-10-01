import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(func(1, N, 1, N));
	}

	static int func(int startY, int endY, int startX, int endX) {
//		1개의 자리만 남았다면
		if(startY == endY) {
			return map[startY][startX];
		}
		
//		분할
		int[] arr = new int[4];
		arr[0] = func(startY, (startY+endY)/2, startX, (startX+endX)/2);
		arr[1] = func(startY, (startY+endY)/2, (startX+endX)/2+1, endX);
		arr[2] = func((startY+endY)/2+1, endY, startX, (startX+endX)/2);
		arr[3] = func((startY+endY)/2+1, endY, (startX+endX)/2+1, endX);
		Arrays.sort(arr);
		return arr[1];
	}
}