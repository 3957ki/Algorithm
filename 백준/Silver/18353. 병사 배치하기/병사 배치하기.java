import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> list = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			if(list.isEmpty() || list.get(list.size()-1) > arr[i]) {
				list.add(arr[i]);
			}
			
			else {
//				이분탐색
				int low = 0;
				int high = list.size()-1;
				int mid = 0;
				
				int min = Integer.MAX_VALUE;
				while(low <= high) {
					mid = (low+high)/2;
					
					if(list.get(mid) <= arr[i]) {
						high = mid-1;
						min = Math.min(min, mid);
					}
					else {
						low = mid+1;
					}
				}
				if(min == Integer.MAX_VALUE) {
					list.add(0, arr[i]);
					continue;
				}
				list.set(min, arr[i]);
			}
		}
		System.out.println(N-list.size());
	}

}