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
		
//		증감 값 배열
		int[] arr = new int[N+1];
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		min = Integer.min(prev, min);
		max = Integer.max(max, prev);
		for(int i = 2; i <= M; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(prev < now) {
				arr[prev]++;
				arr[now]--;
			}
			else {
				arr[now]++;
				arr[prev]--;
			}
			prev = now;
			min = Integer.min(prev, min);
			max = Integer.max(max, prev);
		}
		
//		비용 배열
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int prevCnt = 0;
		for(int i = min; i < max; i++) {
			int cnt = prevCnt+arr[i];
			prevCnt = cnt;
			answer += Math.min(A[i]*cnt, B[i]*cnt+C[i]);
		}
		System.out.println(answer);
	}

}