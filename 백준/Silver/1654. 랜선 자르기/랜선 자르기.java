import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[K];
		for(int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long low = 1;
		long high = Integer.MAX_VALUE;
		long mid;
		
		A: while(low <= high) {
			mid = (low+high)/2;
			
			long sum = 0;
			for(long wire : arr) {
//				만들 수 있는 mid 길이의 랜선 개수
				sum+=wire/mid;
//				조건에 만족하면 더 길게 자르기
				if(sum >= N) {
					low = mid+1;
					continue A;
				}
			}
//			더 작게 자르기
			high = mid-1;
		}
		System.out.println(high);
	}

}