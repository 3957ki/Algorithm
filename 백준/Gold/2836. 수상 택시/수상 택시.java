import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> list = new ArrayList<>();
		long last = 0;
		long temp = M;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start > end && start <= M) {
				list.add(new Node(start, end));
			}
			if(start > end && end < M && start > M) {
				temp = Math.min(temp, end);
			}
			last = Math.max(Math.max(start, end), last);
		}
		
		Collections.sort(list, (o1, o2) -> o1.end - o2.end);
		int L = list.size();
		
		long min = -1;
		long max = -1;
		long answer = M;
		for(int i = 0; i < L; i++) {
			Node now = list.get(i);
			if(min == -1 && max == -1) {
				min = now.end;
				max = now.start;
			}
			else if(now.end >= min && now.end <= max && now.start > max) {
				max = now.start;
			}
			else if(now.end >= max) {
				answer+=(max-min)*2;
				min = now.end;
				max = now.start;
			}
		}
		answer+=(max-min)*2;
		if(last > M) {
			answer+=(last-M)*2;
		}
		answer+=(M-temp)*2;
		System.out.println(answer);
	}

	static class Node{
		int start, end;

		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}
}