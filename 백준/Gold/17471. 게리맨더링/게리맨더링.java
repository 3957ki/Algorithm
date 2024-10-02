import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] arr, edges;
	static int N, total;
	static int answer = Integer.MAX_VALUE;
	static Queue<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
//		구역별 인구수
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		
//		연결정보
		edges = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < M; j++) {
				int end = Integer.parseInt(st.nextToken());
				edges[i] |= 1<<end;
				edges[end] |= 1<<i;
			}
		}
		
//		부분집합
		subset(1, 0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	private static void subset(int cnt, int visited, int sum) {
		if((visited&1<<1) != 0) return;
		if(cnt > N) {
			if(visited == 0) return;
//			검증
			if(calc(visited)) {
				answer = Integer.min(answer, Math.abs(total-sum*2));
			}
			return;
		}
		
		subset(cnt+1, visited, sum);
		subset(cnt+1, visited|1<<cnt, sum+arr[cnt]);
		
	}

	private static boolean calc(int visited) {
		int temp = visited;
		queue.add(1);
		temp|=3;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i = 1; i <= N; i++) {
				if((temp&1<<i) == 0 && (edges[now]&1<<i) != 0) {
					temp|=1<<i;
					queue.add(i);
				}
			}
		}
//		다 연결됨
		if(temp == Math.pow(2, N+1)-1) {
			for(int i = 1; i <= N; i++) {
				if((visited&1<<i) != 0) {
					queue.add(i);
					visited ^= 1<<i;
					break;
				}
			}
			
			while(!queue.isEmpty()) {
				int now = queue.poll();
				
				for(int i = 1; i <= N; i++) {
					if((visited&1<<i) != 0 && (edges[now]&1<<i) != 0) {
						visited^=1<<i;
						queue.add(i);
					}
				}
			}
			if (visited == 0) {
				
				return true;
			}
		}
		return false;
		
	}

}