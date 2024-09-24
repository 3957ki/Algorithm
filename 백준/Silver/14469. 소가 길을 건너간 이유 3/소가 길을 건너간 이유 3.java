import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.start == o2.start) {
				return o1.end - o2.end;
			}
			return o1.start - o2.start;
		});
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end));
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			answer+= Math.max(now.start-answer, 0)+now.end;
		}
		System.out.println(answer);
	}

	static class Node{
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
	}
}