import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			arr[start][end] = time;
		}
		
		int K = Integer.parseInt(br.readLine());
		int[] friends = new int[K+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= K; i++) {
			friends[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(arr[i][k] == 0 || arr[k][j] == 0) continue;
					if(arr[i][j] == 0 ) arr[i][j] = arr[i][k]+arr[k][j];
					else arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}
		}
		int max = Integer.MAX_VALUE;
		List<Integer> list = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			int now = 0;
			for(int j = 1; j <= K; j++) {
				if(friends[j] == i) continue;
				now = Math.max(arr[friends[j]][i]+arr[i][friends[j]], now);
			}
			if(max > now) {
				max = now;
				list.clear();
				list.add(i);
			}
			else if(max == now) {
				list.add(i);
			}
		}
		Collections.sort(list);
		for(int num : list) {
			sb.append(num).append(' ');
		}
		System.out.println(sb);
	}
	
}