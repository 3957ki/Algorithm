import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] edges = new int[N+1][N+1];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[start][end] = weight;
			edges[end][start] = weight;
		}
		
		int low = 0;
		int high = 1000000;
		int mid;
		int answer = Integer.MAX_VALUE;
//		무시할 간선의 최소 가격
		A: while(low <= high) {
			mid = (low+high)/2;
			
			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1.ignore == o2.ignore) return o1.w - o2.w;
	            return o1.ignore - o2.ignore;
			});
			int[] dst = new int[N+1];
			Arrays.fill(dst, Integer.MAX_VALUE);
			boolean[] visited = new boolean[N+1];
			dst[1] = 0;
			pq.add(new Edge(1, 0, 0));
			
			while(!pq.isEmpty()) {
				Edge now = pq.poll();
				
				if (now.ignore > K || visited[now.v]) continue;
	            visited[now.v] = true;
	            if (now.v == N) {
	            	answer = Math.min(answer, mid);
	            	high = mid-1;
	            	continue A;
	            }
				
				for(int i = 1; i <= N; i++) {
					if(edges[now.v][i] == 0 || visited[i]) continue;
					
					int newIgnore = now.ignore + (edges[now.v][i] > mid ? 1 : 0);
	                if (newIgnore <= K && dst[i] > newIgnore) {
	                    dst[i] = newIgnore;
	                    pq.add(new Edge(i, dst[i], newIgnore));
	                }
				}
			}
			
			low = mid + 1;
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static class Edge{
		int v, w, ignore;

		public Edge(int v, int w, int ignore) {
			super();
			this.v = v;
			this.w = w;
			this.ignore = ignore;
		}
		
	}
}