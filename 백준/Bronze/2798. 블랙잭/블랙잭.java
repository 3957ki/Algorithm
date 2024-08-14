import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int sum;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		comb(0, 0);
		System.out.println(answer);
	}

	static void comb(int cnt, int start) {
		if(cnt == 3) {
			if(sum <= M) {
				answer = Math.max(answer, sum);
			}
			return;
		}
		
		for(int i = start; i < N; i++) {
			sum += arr[i];
			comb(cnt+1, i+1);
			sum -= arr[i];
		}
	}
}