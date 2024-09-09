import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		Node[] store = new Node[K];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			store[i] = new Node(dir, value);
		}
		st = new StringTokenizer(br.readLine());
		Node start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int answer = 0;
		for(Node s : store) {
//			둘다 같은 라인이라면
			if(s.dir == start.dir) {
				answer += Math.abs(s.val - start.val);
			}
//			위 아래로 다르면
			else if(s.dir < 3 && start.dir < 3) {
				answer+= N + Math.min(start.val + s.val, 2*M-(start.val + s.val));
			}
//			좌우로 다르면
			else if(s.dir >= 3 && start.dir >= 3) {
				answer+= M + Math.min(start.val + s.val, 2*N-(start.val + s.val));
			}
//			위 왼쪽
			else if((s.dir == 1 && start.dir == 3) || (s.dir == 3 && start.dir == 1)) {
				answer+= s.val+start.val;
			}
//			아래 오른쪽
			else if((s.dir == 2 && start.dir == 4) || (s.dir == 4 && start.dir == 2)) {
				answer += N+M-(s.val + start.val);
			}
//			위 오른쪽
			else if(s.dir == 1 && start.dir == 4) {
				answer+= M-s.val+start.val;
			}
			else if(s.dir == 4 && start.dir == 1) {
				answer+= M-start.val+s.val;
			}
//			아래 왼쪽
			else if(s.dir == 2 && start.dir == 3) {
				answer += N-start.val + s.val;
			}
			else if(s.dir == 3 && start.dir == 2) {
				answer += N-s.val + start.val;
			}
		}
		System.out.println(answer);
	}
	
	static class Node{
		int dir, val;

		public Node(int dir, int val) {
			this.dir = dir;
			this.val = val;
		}
		
	}

}