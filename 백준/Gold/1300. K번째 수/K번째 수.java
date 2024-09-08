import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long low = 1;
		long high = K;
		long mid;
		
		A: while(low <= high) {
			mid = (low+high)/2;
			
			long cnt = 0;
//			mid를 1부터 N까지 나눈 몫을 구해보기, 몫은 N을 넘으면 안됨
			for(int i = 1; i <= N; i++) {
				cnt+=Math.min(mid/i, N);
//				개수가 K이상이면 값 줄이기, lowerbound 하기 위함
				if(cnt >= K) {
					high = mid-1;
					continue A;
				}
			}
			low = mid + 1;
		}
		
		System.out.println(low);
	}

}