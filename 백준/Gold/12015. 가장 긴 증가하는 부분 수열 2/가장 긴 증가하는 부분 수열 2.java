import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		
		List<Integer> LIS = new ArrayList<>();
		LIS.add(arr[0]);
		for(int i = 1; i < N; i++) {
			int low = 0; 
			int high = LIS.size()-1;
			int mid;
			int min = Integer.MAX_VALUE;
			while(low <= high) {
				mid = (low+high)/2;
				
				if(LIS.get(mid) >= arr[i]) {
					high = mid - 1;
					min = Math.min(min, mid);
				}
				else {
					low = mid + 1;
				}
			}
			if(min == Integer.MAX_VALUE) {
				LIS.add(arr[i]);
			}
			else {
				LIS.set(min, arr[i]);
			}
		}
		System.out.println(LIS.size());
	}

}