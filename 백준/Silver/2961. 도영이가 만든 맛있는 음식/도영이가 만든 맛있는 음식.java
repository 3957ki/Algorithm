import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int N;
	static long answer = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
//			S
			arr[i][0] = Integer.parseInt(st.nextToken());
//			B
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 1, 0, 0);
		System.out.println(answer);
	}

	static void subset(int cnt, long totalS, long totalB, int ecnt) {
		if(ecnt == 0 && cnt == N) {
			return;
		}
		if(cnt == N) {
			answer = Math.min(answer, Math.abs(totalS-totalB));
			return;
		}
		
		subset(cnt+1, totalS*arr[cnt][0], totalB+arr[cnt][1], ecnt+1);
		subset(cnt+1, totalS, totalB, ecnt);
	}
}