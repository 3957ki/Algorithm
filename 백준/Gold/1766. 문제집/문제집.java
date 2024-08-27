import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
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
		Queue<Integer>[] edge = new ArrayDeque[N+1];
		for(int i = 1; i <= N; i++) {
			edge[i] = new ArrayDeque<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			edge[first].add(second);
			ahead[second]++;
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(ahead[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append(' ');
			while(!edge[now].isEmpty()) {
				int num = edge[now].poll();
				ahead[num]--;
				if(ahead[num] == 0) {
					queue.add(num);
				}
			}
		}
		System.out.println(sb);
	}
	
}