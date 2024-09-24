import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		queue.add(Integer.parseInt(st.nextToken()));
		for(int i = 2; i <= N; i++) {
			int now = Integer.parseInt(st.nextToken());
//			peek와 값이 다를 때 poll
			while(!queue.isEmpty() && queue.peek() != now) {
				queue.poll();
				sb.append(i).append(' ');
			}
			queue.add(now);
		}
		while(!queue.isEmpty()) {
			sb.append(-1).append(' ');
			queue.poll();
		}
		System.out.println(sb);
	}

}