import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static long[] seg;
	static int[] arr;
	static final int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		seg = new long[4*N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, N, 1);
		
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				update(1, N, 1, a, b-arr[a]);
				arr[a] = b;
			}
			else {
				sb.append(find(1, N, 1, a, b)).append('\n');
			}
		}
		System.out.println(sb);
	}

	static long init(int start, int end, int node) {
		if(start == end) return seg[node] = arr[start];
		int mid = (start+end)/2;
		return seg[node] = init(start, mid, node<<1)*init(mid+1, end, node<<1|1)%MOD;
	}
	
	static long update(int start, int end, int node, int index, int diff) {
		if(start > index || end < index) return seg[node];
		if(start == index && end == index) return seg[node] += diff;
		int mid = (start+end)/2;
		return seg[node] = update(start, mid, node<<1, index, diff)*update(mid+1, end, node<<1|1, index, diff)%MOD;
	}
	
	static long find(int start, int end, int node, int left, int right) {
		if(start > right || end < left) return 1;
		if(start >= left && end <= right) return seg[node];
		int mid = (start+end)/2;
		return find(start, mid, node<<1, left, right)*find(mid+1, end, node<<1|1, left, right)%MOD;
	}
}