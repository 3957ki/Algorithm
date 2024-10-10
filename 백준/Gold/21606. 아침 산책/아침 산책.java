import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashSet<Integer> inside = new HashSet<>();
		HashSet<Integer> outside = new HashSet<>();
		
		String str = br.readLine();
		for(int i = 1; i <= N; i++) {
//			실내
			if(str.charAt(i-1) == '1') inside.add(i);
//			실외
			else outside.add(i);
		}
		
		List<Integer>[] edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			edges[start].add(end);
			edges[end].add(start);
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		int answer = 0;
//		실내 2지점 만날 때
		for(int start : inside) {
			for(int next : edges[start]) {
				if(!inside.contains(next)) continue;
				answer++;
			}
		}
//		실외에서 BFS해서 만나는 실내 점들 개수로 계산
		boolean[] visited = new boolean[N+1];
		for(int start : outside) {
			if(visited[start]) continue;
			visited[start] = true;
			queue.add(start);
			int cnt = 0;
			
			while(!queue.isEmpty()) {
				int now = queue.poll();
				for(int next : edges[now]) {
					if(visited[next]) continue;
					if(inside.contains(next)) {
						cnt++;
						continue;
					}
					visited[next] = true;
					queue.add(next);
				}
			}
			answer+= cnt*(cnt-1);
		}
		System.out.println(answer);
	}

}