import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Queue<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
//			간선 리스트
			List<Integer>[] edges = new ArrayList[M+1];
			for(int i = 1; i <= M; i++) edges[i] = new ArrayList<>();
			
//			ahead
			int[] ahead = new int[M+1];
			int[] num = new int[M+1];
			boolean[] dupl = new boolean[M+1];
			
			for(int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				edges[start].add(end);
				ahead[end]++;
			}
			
			for(int i = 1; i <= M; i++) {
//				시작점 큐에 넣기
				if(ahead[i] == 0) {
					queue.add(i);
					num[i] = 1;
				}
			}
			
//			위상 정렬
			while(!queue.isEmpty()) {
				int now = queue.poll();
//				end라면
				if(now == M) {
					sb.append(K).append(' ').append(num[now]).append('\n');
				}
				
				for(Integer next : edges[now]) {
//					ahead 감소시키기
					--ahead[next];
					
//					들어오는 노드의 순서가 더 크다면 갱신
					if(num[next] < num[now]) {
						num[next] = num[now];
						dupl[next] = false;
					}
					
//					같으면
					else if(num[next] == num[now]) {
						dupl[next] = true;
					}
					
//					ahead가 0이면 큐에 넣기
					if(ahead[next] == 0) {
						if(dupl[next]) num[next]++;
						queue.add(next);
					}
				}
			}
		}
		System.out.println(sb);
	}

}