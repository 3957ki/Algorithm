import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		
		Arrays.sort(arr);
		
		long low = 1;
		long high = arr[arr.length-1] - arr[0];
		long mid;
		
		A: while(low <= high) {
			mid = (low+high)/2;
			
			long now = arr[0];
			long n = 1;
			for(long stub : arr) {
//				mid길이보다 짧으면 패스
				if(stub - now < mid) continue;
				n++;
				now = stub;
//				조건 만족하면 늘리기
				if(n == N) {
					low = mid+1;
					continue A;
				}
			}
//			더 가깝게 설치
			high = mid-1;
		}
		
		System.out.println(high);
	}

}