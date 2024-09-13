import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
//			C와 같은게 있다면 종료
			if(arr[i] == C) {
				System.out.println(1);
				return;
			}
		}
		
		Arrays.sort(arr);
		
		int low;
		int high;
		int mid;
		
		for(int i = 0; i < N-1; i++) {
			low = i+1;
			high = N-1;
			while(low <= high) {
				mid = (low+high)/2;
//				같으면 종료
				if(arr[i]+arr[mid] == C) {
					System.out.println(1);
					return;
				}
//				합이 C보다 작으면
				else if(arr[i]+arr[mid] < C) {
					int num = C - (arr[i]+arr[mid]);
					if(num != arr[i] && num != arr[mid] && set.contains(num)) {
						System.out.println(1);
						return;
					}
					if(num > arr[mid]) {
						low = mid+1;
					}
					else {
						high = mid-1;
					}
				}
//				합이 C보다 크면
				else {
					high = mid-1;
				};
			}
			
		}
		System.out.println(0);
	}

}