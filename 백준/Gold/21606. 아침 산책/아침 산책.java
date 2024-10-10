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
		
		List<Integer> inside = new ArrayList<>();
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
		int L = inside.size();
		for(int i = 0; i < L-1; i++) {
			for(int j = i+1; j < L; j++) {
				int start = inside.get(i);
				int end = inside.get(j);
				boolean[] visited = new boolean[N+1];
				queue.add(start);
				visited[start] = true;
				
				A: while(!queue.isEmpty()) {
					int now = queue.poll();
					for(int next : edges[now]) {
						if(next == end) {
							answer++;
							queue.clear();
							break A;
						}
						if(!outside.contains(next) || visited[next]) continue;
						visited[next] = true;
						queue.add(next);
					}
				}
			}
		}
		System.out.println(answer*2);
	}

}