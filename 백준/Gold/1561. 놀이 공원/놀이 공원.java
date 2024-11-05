import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		long N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[M+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long low = 0;
		long high = N*(31);
		long mid;
		long time = high;
		long cnt = 0;
		
		while(low <= high) {
			mid = (low+high)/2;
			long sum = 0;
			
			for(int i = 1; i <= M; i++) {
				sum += mid/arr[i]+1;
			}
			
			if(sum >= N) {
				cnt = sum;
				high = mid-1;
				time = Math.min(time, mid);
			}
			
			else if(sum < N) {
				low = mid + 1;
			}
		}
		
		List<int[]> list = new ArrayList<>();
		for(int i = 1; i <= M; i++) {
			list.add(new int[] {(int)(time%arr[i]), i});
		}
		Collections.sort(list, (o1, o2) -> {
			if(o1[0] == o2[0]) {
				return o2[1]-o1[1];
			}
			return o1[0]-o2[0];
		});
		
		System.out.println(list.get((int)(cnt-N))[1]);
	}

}