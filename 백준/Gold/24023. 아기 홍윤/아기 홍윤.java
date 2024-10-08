import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr, seg;
	static int K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		seg = new int[N*4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, N, 1);
		
//		이분탐색
		for(int i = 1; i <= N; i++) {
			int low = i;
			int high = N;
			int mid;
			while(low <= high) {
				mid = (low+high)/2;
				int value = find(1, N, 1, i, mid);
				if(value > K) high = mid-1;
				else if(value == K) {
					System.out.println(i+" "+mid);
					return;
				}
				else low = mid+1;
			}
		}
		System.out.println(-1);
	}

	static int init(int start, int end, int index) {
		if(start==end) return seg[index] = arr[start];
		int mid = (start+end)/2;
		return seg[index] = init(start, mid, index<<1) | init(mid+1, end, index<<1|1);
	}
	
	static int find(int start, int end, int index, int left, int right) {
		if(start > right || end < left) return 0;
		if(start >= left && end <= right) return seg[index];
		int mid = (start+end)/2;
		return find(start, mid, index<<1, left, right) | find(mid+1, end, index<<1|1, left, right);
	}
}