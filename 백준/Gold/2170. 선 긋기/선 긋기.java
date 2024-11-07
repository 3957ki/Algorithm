import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		List<Node> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Node(start, end));
		}
		
//		시작지점 오름차순
		Collections.sort(list, (o1, o2) -> {
			if(o1.start == o2.start) {
				return o1.end-o2.end;
			}
			return o1.start-o2.start;
		});
		
		int min = Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		int answer = 0;
		for(Node now : list) {
			if(now.start > max) {
				answer += max-min;
				min = now.start;
				max = now.end;
			}
			else max = Math.max(max, now.end);
		}
		answer += max-min;
		
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