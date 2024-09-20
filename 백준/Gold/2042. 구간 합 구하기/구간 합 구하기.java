import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long[] arr, seg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		seg = new long[4*N];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
//		구간 합 초기화
		init(1, N, 1);
		
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			long n2 = Long.parseLong(st.nextToken());
//			값 변경
			if(cmd == 1) {
				long diff = n2 - arr[n1];
				arr[n1] = n2;
				update(1, N, 1, n1, diff);
			}
//			구간 합 구하기
			else {
				sb.append(sum(1, N, 1, n1, (int)n2)).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	static long init(int start, int end, int node) {
//		구간이 1이면 해당 값으로 저장
		if(start == end) return seg[node] = arr[start];
//		반으로 나누어서 구간 합을 저장
		int mid = (start + end)/2;
		return seg[node] = init(start, mid, node<<1) + init(mid+1, end, node<<1|1);
	}
	
	static long sum(int start, int end, int node, int left, int right) {
//		아예 구간 밖인 경우
		if(start > right || end < left) return 0;
//		구간 안에 포함된 경우
		if(start >= left && end <= right) return seg[node];
//		일부분만 포함이라면 나누어 계산
		int mid = (start + end)/2;
		return sum(start, mid, node<<1, left, right) + sum(mid+1, end, node<<1|1, left, right);
	}

	static void update(int start, int end, int node, int index, long diff) {
//		아예 구간 밖인 경우
		if(start > index || end < index) return;
//		구간 안인 경우 갱신
		seg[node] += diff;
//		끝까지 내려오면 return
		if(start == end) return;
		int mid = (start + end)/2;
		update(start, mid, node<<1, index, diff);
		update(mid+1, end, node<<1|1, index, diff);
	}
}