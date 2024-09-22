import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr, seg;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		seg = new int[4*N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
//		초기화
		init(1, N, 1);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(min(1, N, 1, start, end)).append('\n');
		}
		System.out.println(sb);
	}
	
	static int init(int start, int end, int node) {
		if(start == end) return seg[node] = arr[start];
		int mid = (start + end)/2;
		return seg[node] = Math.min(init(start, mid, node<<1), init(mid+1, end, node<<1|1));
	}

	static int min(int left, int right, int node, int start, int end) {
		if(start > right || end < left) return Integer.MAX_VALUE;
		if(start <= left && end >= right) return seg[node];
		int mid = (left+right)/2;
		return Math.min(min(left, mid, node<<1, start, end), min(mid+1, right, node<<1|1, start, end));
	}
}