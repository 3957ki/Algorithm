import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] arr, seg, lazy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int dst = Integer.parseInt(st.nextToken());
		long damage = Integer.parseInt(st.nextToken());
		
		int C = Integer.parseInt(br.readLine());
		
		arr = new long[N+1];
		seg = new long[N*4];
		lazy = new long[N*4];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 1; i <= N; i++) {
			if(arr[i] == 0) continue;
			int cnt = i <= dst ? i : dst;
			cnt -= find(1, N, 1, i);
			
//			지뢰
			if(cnt <= 0 || arr[i] > damage*cnt) {
				if(C-- == 0) {
					System.out.println("NO");
					return;					
				}
				update(1, N, 1, i, Math.min(i+dst, N));
			}
		}
		
		System.out.println("YES");
		
	}

	static void propagate(int start, int end, int index) {
		if(lazy[index] != 0) {
			if(start == end) {
				seg[index] += lazy[index];
				lazy[index] = 0;
				return;
			}
			lazy[index<<1] += lazy[index];
			lazy[index<<1|1] += lazy[index];
			lazy[index] = 0;
		}
	}
	static void update(int start, int end, int index, int left, int right) {
		propagate(start, end, index);
		if(start > right || end < left) return;
		if(start >= left && end <= right) {
			lazy[index]++;
			propagate(start, end, index);
			return;
		}
		int mid = (start+end)/2;
		update(start, mid, index<<1, left, right);
		update(mid+1, end, index<<1|1, left, right);
	}
	
	static long find(int start, int end, int index, int target) {
		propagate(start, end, index);
		if(end < target || start > target) return 0;
		if(start == end && start == target) return seg[index];
		int mid = (start+end)/2;
		return find(start, mid, index<<1, target)+find(mid+1, end, index<<1|1, target);
	}
}