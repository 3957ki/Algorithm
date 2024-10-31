import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long[] seg, lazy, arr;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		arr = new long[N+1];
		seg = new long[N*4];
		lazy = new long[N*4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, N, 1);
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
//			update
			if(cmd == 1) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				update(1, N, 1, start, end, diff);
			}
//			find
			else {
				int num = Integer.parseInt(st.nextToken());
				find(1, N, 1, num);
				sb.append(arr[num]).append('\n');
			}
		}
		System.out.println(sb);
	}

	static void propagate(int start, int end, int index) {
//		자식이 있다면
		if(start != end) {
			lazy[index<<1] += lazy[index];
			lazy[index<<1|1] += lazy[index];
		}
		seg[index] += lazy[index];
		lazy[index] = 0;
	}
	
	static void init(int start, int end, int index) {
		if(start == end) {
			seg[index] = arr[start];
			return;
		}
		int mid = (start+end)/2;
		init(start, mid, index<<1);
		init(mid+1, end, index<<1|1);
	}
	
	static void update(int start, int end, int index, int left, int right, int diff) {
		propagate(start, end, index);
		if(start > right || end < left) return;
		if(start >= left && end <= right) {
			lazy[index] += diff;
			propagate(start, end, index);
			return;
		}
		int mid = (start+end)/2;
		update(start, mid, index<<1, left, right, diff);
		update(mid+1, end, index<<1|1, left, right, diff);
	}
	
	static void find(int start, int end, int index, int num) {
		propagate(start, end, index);
		if(start > num || end < num) return;
		if(start == num && end == num) {
			arr[num] = seg[index];
			return;
		}
		int mid = (start+end)/2;
		find(start, mid, index<<1, num);
		find(mid+1, end, index<<1|1, num);
	}
}