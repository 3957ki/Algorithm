import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr, seg;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N+1];
		seg = new int[4*N];
		arr[0] = Integer.MAX_VALUE;
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, N, 1);
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
//			update
			if(cmd == 1) {
				arr[a] = b;
				update(1, N, 1, a);
			}
//			find
			else {
				sb.append(find(1, N, 1, a, b)).append('\n');
			}
			
		}
		System.out.println(sb);
	}
	
	static int find(int start, int end, int node, int left, int right) {
		if(left > end || right < start) return 0;
		if(left <= start && right >= end) return seg[node];
		int mid = (start+end)/2;
		int v1 = find(start, mid, node<<1, left, right);
		int v2 = find(mid+1, end, node<<1|1, left, right);
//		v1값과 v2값이 같다면
		if(arr[v1] == arr[v2]) {
//			v1의 인덱스가 더 작다면
			if(v1 < v2) return v1;
//			아니라면
			return v2;
		}
//		v1의 값이 더 작다면
		if(arr[v1] < arr[v2]) return v1;
//		아니라면
		return v2;
	}

	static int update(int start, int end, int node, int index) {
		if(start > index || end < index) return seg[node];
		if(start == index && end == index) return seg[node];
		int mid = (start+end)/2;
		int v1 = update(start, mid, node<<1, index);
		int v2 = update(mid+1, end, node<<1|1, index);
//		v1값과 v2값이 같다면
		if(arr[v1] == arr[v2]) {
//			v1의 인덱스가 더 작다면
			if(v1 < v2) return seg[node] = v1;
//			아니라면
			return seg[node] = v2;
		}
//		v1의 값이 더 작다면
		if(arr[v1] < arr[v2]) return seg[node] = v1;
//		아니라면
		return seg[node] = v2;
	}

	static int init(int start, int end, int node) {
		if(start == end) return seg[node] = start;
		int mid = (start+end)/2;
		int v1 = init(start, mid, node<<1);
		int v2 = init(mid+1, end, node<<1|1);
//		v1값과 v2값이 같다면
		if(arr[v1] == arr[v2]) {
//			v1의 인덱스가 더 작다면
			if(v1 < v2) return seg[node] = v1;
//			아니라면
			return seg[node] = v2;
		}
//		v1의 값이 더 작다면
		if(arr[v1] < arr[v2]) return seg[node] = v1;
//		아니라면
		return seg[node] = v2;
	}
}