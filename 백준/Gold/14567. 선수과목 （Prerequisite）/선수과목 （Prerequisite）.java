import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new List[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int[] ahead = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			list[first].add(next);
			ahead[next]++;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			if(ahead[i] == 0) queue.add(i);
		}
		
		int[] answer = new int[N+1];
		
		int dst = 0;
		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			while(L-- > 0) {
				int now = queue.poll();
				answer[now] = dst;
				for(Integer next : list[now]) {
					if(--ahead[next] == 0) {
						queue.add(next);
					}
				}
			}
			
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
		
	}

}
