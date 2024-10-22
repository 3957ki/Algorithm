import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R, X, answer;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		System.out.println(answer);
	}

	static void subset(int cnt, int sum, int min, int max, int visited) {
		
		if(cnt == N) {
//			차이가 X보다 작거나 합이 L보다 작거나 공집합이면 패스
			if(max-min < X || sum < L || visited == 0) return;
			answer++;
			return;
		}
		
//		채택하지 않음
		subset(cnt+1, sum, min, max, visited);
		
//		최대 최소 갱신
		min = Math.min(min, arr[cnt]);
		max = Math.max(max, arr[cnt]);
		
//		합이 R이하이면 채택 가능
		if(sum+arr[cnt] <= R) {
			subset(cnt+1, sum+arr[cnt], min, max, visited|(1<<cnt));
		}
	}
}