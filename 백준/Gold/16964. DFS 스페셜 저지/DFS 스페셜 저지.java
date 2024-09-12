import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static HashSet<Integer>[] edges;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		edges = new HashSet[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new HashSet<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edges[start].add(end);
			edges[end].add(start);
		}
		queue = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			queue.add(Integer.parseInt(st.nextToken()));
		}
		if(queue.poll() == 1){
			DFS(1);
		}
		System.out.println(0);
	}
	
	static int DFS(int now) {
		if(queue.isEmpty()) {
			System.out.println(1);
			System.exit(0);
		}
		int next = queue.peek();
		int size = edges[now].size();
		while(size-- > 0) {
			if(edges[now].contains(next)) {
				queue.poll();
				next = DFS(next);
			}
			else {
				return next;
			}
		}
		return next;
	}

}