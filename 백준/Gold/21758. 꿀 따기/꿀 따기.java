import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		int[] sum = new int[N];
		int[] left = new int[N];
		int[] right = new int[N];
		
		arr[0] = Integer.parseInt(st.nextToken());
		sum[0] = arr[0];
		left[0] = Integer.MAX_VALUE;
		right[N-1] = Integer.MAX_VALUE;
		
		for(int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1]+arr[i];
		}
		
		int answer = 0;
		
//		왼쪽
		for(int i = 1; i < N-1; i++) {
			left[i] = Math.min(left[i-1], arr[i] + sum[i]-arr[0]);
		}
		
//		오른쪽
		for(int i = N-2; i > 0; i--) {			
			right[i] = Math.min(right[i+1], arr[i] + sum[N-1] - sum[i-1] - arr[N-1]);
		}
		
//		벌통 위치
		for(int i = 0; i < N; i++) {
			if(i > 0 && i < N-1) {				
				answer = Math.max(answer, sum[N-2] - arr[0] + arr[i]);
			}
			
			if(i >= 2) {
				answer = Math.max(answer, (sum[i]-arr[0])*2 - left[i-1]);				
			}
			
			if(i < N-2) {				
				answer = Math.max(answer, (sum[N-1]-(i == 0 ? 0 : sum[i-1])-arr[N-1])*2 - right[i+1]);
			}
		}
		
		System.out.println(answer);
	}

}