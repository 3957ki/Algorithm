import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int max = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int low = 1;
		int high = max;
		int mid;
		
		A: while(low <= high) {
			mid = (low+high)/2;
			
			int sum = 0;
			for(int num : arr) {
				sum += num <= mid ? num : mid;
				if(sum > M) {
					high = mid - 1;
					continue A;
				}
			}
			
			low = mid + 1;
		}
		
		System.out.println(high);
		
	}

}