import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(N > M) {
			System.out.println(N-M);
			System.exit(0);
		}
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		int[] visited = new int[100001];
		queue.addLast(new int[] {N, 1});
		visited[N] = 1;
		while(!queue.isEmpty()) {
			int[] now = queue.removeFirst();
			int num = now[0];
			int cnt = now[1];
			
			if(num<<1 <= 100000 && (visited[num<<1] == 0 || visited[num<<1] > cnt)) {
				queue.addLast(new int[] {num<<1, cnt});
				visited[num<<1] = cnt;
			}
			if(num+1 <= 100000 && (visited[num+1] == 0 || visited[num+1] > cnt+1)) {
				queue.addLast(new int[] {num+1, cnt+1});
				visited[num+1] = cnt+1;
			}
			if(num-1 >= 0 && (visited[num-1] == 0 || visited[num-1] > cnt+1)) {
				queue.addLast(new int[] {num-1, cnt+1});
				visited[num-1] = cnt+1;
			}
		}
		System.out.println(visited[M]-1);
	}
	
}
