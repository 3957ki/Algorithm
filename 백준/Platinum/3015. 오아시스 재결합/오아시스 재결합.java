import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = N-1;
		
		Stack<Node> stack = new Stack<>();
		stack.push(new Node(Integer.parseInt(br.readLine()), 1));
		
		for(int i = 1; i < N; i++) {
			int cnt = 1;
			int now = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty() && stack.peek().val <= now) {
				Node prev = stack.pop();
//				top과 같을 때
				if(prev.val == now) {
					cnt+=prev.cnt;
				}
//				pop한 개수만큼 증가
				answer+= prev.cnt;
			}
//			비었으면 1개 빼기
			if(stack.isEmpty()) answer--;

			stack.push(new Node(now, cnt));
		}
		System.out.println(answer);
	}

	static class Node {
		int val, cnt;

		public Node(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}
		
	}
}