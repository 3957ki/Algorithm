import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
//		ahead 개수와 각 부품이 몇 개 필요한 지
		int[][] arr = new int[N+1][N+1];
//		자신이 어디에 몇 개를 줘야하는 지
		int[][] cnt = new int[N+1][N+1];
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			arr[X][0]++;
			cnt[Y][X] = K;
		}
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> standard = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(arr[i][0] == 0) {
				queue.add(i);
				standard.add(i);
				arr[i][i] = 1;
			}
		}
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for(int i = 1; i <= N; i++) {
				if(cnt[num][i] == 0) continue;
				arr[i][0]--;
				for(int j = 1; j <= N; j++) {
					arr[i][j] += arr[num][j]*cnt[num][i];
				}
				if(arr[i][0] == 0) {
					queue.add(i);
				}
			}
		}
		while(!standard.isEmpty()) {
			int num = standard.poll();
			sb.append(num).append(' ').append(arr[N][num]).append('\n');
		}
		System.out.println(sb);
	}
}
