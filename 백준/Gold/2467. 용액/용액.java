import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int low = 0;
		int high = N-1;
		long sum = Math.abs(arr[high] + arr[low]);
		int left = low;
		int right = high;
		
		while(low < high) {
			long s = Math.abs(arr[low] + arr[high]);
			//합이 0이면 바로 return
			if(s == 0) {
				System.out.println(arr[low] +" "+arr[high]);
				return;
			}
			
			//최소값 보다 작다면 갱신
			if(sum > s) {
				left = low;
				right = high;
				sum = s;
				continue;
			}
			
			//합이 음수라면
			if(arr[low] + arr[high] < 0) {
				low++;
			}
			//합이 양수라면
			else if(arr[low] + arr[high] > 0) {
				high--;
			}
			
		}
		
		System.out.println(arr[left] + " "+ arr[right]);
		
	}

}