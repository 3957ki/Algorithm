import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[1001][1001];
		queue.add(new Node(1, 0));
		visited[1][0] = true;
		int cnt = 0;
		while(!queue.isEmpty()) {
			cnt++;
			int L = queue.size();
			while(L-- > 0) {
				Node node = queue.poll();
				if(node.num == N) {
					System.out.println(cnt-1);
					return;
				}
				if(!visited[node.num][node.num]) {
					visited[node.num][node.num] = true;
					queue.add(new Node(node.num, node.num));
				}
				if(node.num+node.clip < 1001 && !visited[node.num+node.clip][node.clip]) {
					visited[node.num+node.clip][node.clip] = true;
					queue.add(new Node(node.num+node.clip, node.clip));
				}
				if(node.num > 0 && !visited[node.num-1][node.clip]) {
					visited[node.num-1][node.clip] = true;
					queue.add(new Node(node.num-1, node.clip));
				}
			}
		}
	}
	
	static class Node{
		int num, clip;

		public Node(int num, int clip) {
			this.num = num;
			this.clip = clip;
		}
		
	}
}