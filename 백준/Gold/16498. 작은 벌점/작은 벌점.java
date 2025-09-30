import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] arr = new int[3][];
		arr[0] = new int[A];
		arr[1] = new int[B];
		arr[2] = new int[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++) {
			arr[1][i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[2][i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr[0]);
		Arrays.sort(arr[1]);
		Arrays.sort(arr[2]);

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> arr[o1.player][o1.idx] - arr[o2.player][o2.idx]);
		pq.add(new Node(0, 0));
		pq.add(new Node(0, 1));
		pq.add(new Node(0, 2));

		int max = Math.max(arr[2][0], Math.max(arr[0][0], arr[1][0]));
		int answer = max - arr[pq.peek().player][pq.peek().idx];
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int next_idx = now.idx + 1;
			if(next_idx == arr[now.player].length) break;

			max = Math.max(max, arr[now.player][next_idx]);
			pq.add(new Node(next_idx, now.player));
			answer = Math.min(answer, max - arr[pq.peek().player][pq.peek().idx]);
		}

		System.out.println(answer);
    }

	static class Node{
		int idx, player;

		Node(int idx, int player){
			this.idx = idx;
			this.player = player;
		}
	}
}