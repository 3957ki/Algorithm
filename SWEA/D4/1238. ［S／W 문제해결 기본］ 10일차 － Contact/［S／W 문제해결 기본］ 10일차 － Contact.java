import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken())/2;
			int M = Integer.parseInt(st.nextToken());
			
			Node[] edge = new Node[101];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				edge[start] = new Node(end, edge[start]);
			}
			
			Queue<Integer> queue = new ArrayDeque<>();
			boolean[] visited = new boolean[101];
			queue.add(M);
			visited[M] = true;
			
			int answer = 0;
			while(!queue.isEmpty()) {
				answer = 0;
				int L = queue.size();
				for(int i = 0; i < L; i++) {
					int num = queue.poll();
					answer = Math.max(answer, num);
					for(Node temp = edge[num]; temp != null; temp = temp.next) {
						if(visited[temp.to]) continue;
						visited[temp.to] = true;
						queue.add(temp.to);
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int to;
		Node next;
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
		
	}
}
