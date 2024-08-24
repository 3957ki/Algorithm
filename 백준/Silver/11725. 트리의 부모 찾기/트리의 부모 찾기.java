import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] parent = new int[N+1];
		List<Integer>[] arr = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[start].add(end);
			arr[end].add(start);
		}
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.addLast(1);
		while(!queue.isEmpty()) {
			Integer prt = queue.removeFirst();
			while(!arr[prt].isEmpty()) {
				Integer num = arr[prt].remove(0);
				arr[num].remove(prt);
				parent[num] = prt;
				queue.addLast(num);
			}
		}
		
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append('\n');
		}
		System.out.println(sb);
	}
	
}