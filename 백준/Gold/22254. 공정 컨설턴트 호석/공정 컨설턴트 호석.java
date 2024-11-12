import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int low = 1;
		int high = N;
		int mid;
		int answer = N;
		A: while(low <= high) {
			mid = (low+high)/2;
			
			PriorityQueue<Long> pq = new PriorityQueue<>();
//			라인 개수만큼 배치
			for(int i = 0; i < mid; i++) {
				pq.add(arr[i]);
			}
			for(int i = mid; i < N; i++) {
				Long now = pq.poll();
				now+=arr[i];
				pq.add(now);
				if(now > X) {
					low = mid + 1;
					continue A;
				}
			}
			
			answer = Math.min(mid, answer);
			high = mid - 1;
		}
		
		System.out.println(answer);
	}

}