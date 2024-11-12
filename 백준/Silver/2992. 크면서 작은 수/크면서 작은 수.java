import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, X;
	static int[] arr;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		N = str.length();
		arr = new int[N];
		
		X = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = str.charAt(i) - '0';
			X = X*10+arr[i];
		}
		
		perm(0, 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
	}

	static void perm(int cnt, int visited, int sum) {
		if(cnt == N) {
			if(sum > X) {
				answer = Math.min(answer, sum);
			}
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((visited&(1<<i)) > 0) continue;
			perm(cnt+1, visited|1<<i, sum*10+arr[i]);
		}
		
	}
}