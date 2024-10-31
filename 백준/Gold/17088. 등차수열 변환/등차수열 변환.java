import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int N, answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				func(arr[0]+i, arr[1]+j, Math.abs(i)+Math.abs(j));
			}
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static void func(int first, int second, int cnt) {
		int diff = first - second;
//		check
		for(int i = 2; i < N; i++) {
			int temp = Math.abs(arr[i] - (first-diff*i));
//			불가능하면
			if(temp > 1) return;
//			1 차이 나면
			if(temp == 1) cnt++;
		}
		answer = Math.min(answer, cnt);
	}
}