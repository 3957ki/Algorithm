import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static Pair[] seg;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		seg = new Pair[4*N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
//		초기화
		init(1, N, 1);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Pair now = find(1, N, 1, start, end);
			sb.append(now.min).append(' ').append(now.max).append('\n');
		}
		System.out.println(sb);
	}
	
	static Pair init(int start, int end, int node) {
		if(start == end) return seg[node] = new Pair(arr[start], arr[start]);
		int mid = (start + end)/2;
		Pair p1 = init(start, mid, node<<1);
		Pair p2 = init(mid+1, end, node<<1|1);
		return seg[node] = new Pair(Math.min(p1.min, p2.min), Math.max(p1.max, p2.max));
	}

	static Pair find(int left, int right, int node, int start, int end) {
		if(start > right || end < left) return new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE);
		if(start <= left && end >= right) return seg[node];
		int mid = (left+right)/2;
		Pair p1 = find(left, mid, node<<1, start, end);
		Pair p2 = find(mid+1, right, node<<1|1, start, end);
		return new Pair(Math.min(p1.min, p2.min), Math.max(p1.max, p2.max));
	}
	
	static class Pair{
		int min, max;

		public Pair(int min, int max) {
			this.min = min;
			this.max = max;
		}
		
	}
}