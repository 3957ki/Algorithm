import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] ahead = new int[N+1];
		Queue<Integer>[] edges = new ArrayDeque[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayDeque<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int now = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j < cnt; j++) {
				int next = Integer.parseInt(st.nextToken());
//				간선 저장
				edges[now].add(next);
//				ahead 증가
				ahead[next]++;
				now = next;
			}
		}
//		정답 큐
		Queue<Integer> result = new ArrayDeque<>();
//		방문배열
		boolean[] visited = new boolean[N+1];
		
//		위상정렬
		Queue<Integer> queue = new ArrayDeque<>();
//		ahead 0인거 큐에 넣기
		for(int i = 1; i <= N; i++) {
			if(ahead[i] == 0) {
				visited[i] = true;
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
//			정답 배열에 저장
			result.add(now);
//			간선 탐색
			while(!edges[now].isEmpty()) {
				int next = edges[now].poll();
//				다음 번호의 ahead 줄이고, 0이 되면 큐에 넣기 
				if(--ahead[next] == 0) {
//					사이클이 생긴다면 return
					if(visited[next]) {
						System.out.println(0);
						return;
					}
					visited[next] = true;
					queue.add(next);
				}
			}

		}
		if(result.size() != N) {
			System.out.println(0);
			return;
		}
		
		while(!result.isEmpty()) {
			int num = result.poll();
			sb.append(num).append('\n');
		}
		System.out.println(sb);
	}

}