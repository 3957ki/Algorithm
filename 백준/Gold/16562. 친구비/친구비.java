import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] cost = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			cost[i] = Integer.parseInt(st.nextToken());
		}

		Set<Integer>[] edges = new Set[N];
		for(int i = 0; i < N; i++){
			edges[i] = new HashSet<>();
		}

		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			edges[start].add(end);
			edges[end].add(start);
		}

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		int money = 0;

		for(int i = 0; i < N; i++){
			if(visited[i]) continue;
			queue.add(i);
			visited[i] = true;

			int min = cost[i];

			while(!queue.isEmpty()){
				int now = queue.poll();
				for(int next : edges[now]){
					if(visited[next]) continue;
					visited[next] = true;
					queue.add(next);
					min = Math.min(min, cost[next]);
				}
			}

			money += min;
			if(money > K){
				System.out.println("Oh no");
				return;
			}
		}

		System.out.println(money);
    }
}