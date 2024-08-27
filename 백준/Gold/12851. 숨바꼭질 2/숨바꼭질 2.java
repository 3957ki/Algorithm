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
		
		if(N >= M) {
			System.out.println(N-M);
			System.out.println(1);
			System.exit(0);
		}
		
		int[] visited = new int[100001];
		int[] answer = new int[100001];
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.addLast(new int[] {N, 1});
		visited[N] = 1;
		answer[N] = 1;
		
		while(!queue.isEmpty()) {
			int[] now = queue.removeFirst();
			int num = now[0];
			int cnt = now[1];
			
			if(num<<1 <= 100000) {
				if(visited[num<<1] == 0) {
					answer[num<<1] = 1;
					queue.addLast(new int[] {num<<1, cnt+1});
					visited[num<<1] = cnt+1;
				}
				else if(visited[num<<1] > cnt+1) {
					answer[num<<1] = 1;
					queue.addLast(new int[] {num<<1, cnt+1});
					visited[num<<1] = cnt+1;
				}
				else if(visited[num<<1] == cnt+1) {
					answer[num<<1]++;
					queue.addLast(new int[] {num<<1, cnt+1});
				}
			}
			if(num+1 <= 100000) {
				if(visited[num+1] == 0) {
					answer[num+1] = 1;
					queue.addLast(new int[] {num+1, cnt+1});
					visited[num+1] = cnt+1;
				}
				else if(visited[num+1] > cnt+1) {
					answer[num+1] = 1;
					queue.addLast(new int[] {num+1, cnt+1});
					visited[num+1] = cnt+1;
				}
				else if(visited[num+1] == cnt+1) {
					answer[num+1]++;
					queue.addLast(new int[] {num+1, cnt+1});
				}
			}
			if(num-1 >= 0) {
				if(visited[num-1] == 0) {
					answer[num-1] = 1;
					queue.addLast(new int[] {num-1, cnt+1});
					visited[num-1] = cnt+1;
				}
				else if(visited[num-1] > cnt+1) {
					answer[num-1] = 1;
					queue.addLast(new int[] {num-1, cnt+1});
					visited[num-1] = cnt+1;
				}
				else if(visited[num-1] == cnt+1) {
					answer[num-1]++;
					queue.addLast(new int[] {num-1, cnt+1});
				}
			}
		}
		System.out.println(visited[M]-1);
		System.out.println(answer[M]);
	}
	
}
