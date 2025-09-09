import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] edges;
	static Node[] arr;
	static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		edges = new List[N+1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		arr = new Node[N+1];
		arr[1] = new Node();

		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			long cnt = Long.parseLong(st.nextToken());
			int next = Integer.parseInt(st.nextToken());

			arr[i] = new Node(type, cnt);
			edges[i].add(next);
			edges[next].add(i);
		}

		System.out.println(DFS(1, 0));
    }

	static long DFS(int now, int prev){

		long sum = 0;
		for (int next : edges[now]) {
			if(next == prev) continue;
			Node node = arr[next];

			// 양
			if(node.type == 'S') sum += node.cnt;

			sum += DFS(next, now);
		}

		if(arr[now].type == 'W'){
			sum = Math.max(0, sum - arr[now].cnt);
		}

		return sum;
	}

	static class Node {
		char type;
		long cnt;

		Node(){};
		Node(char type, long cnt) {
			this.type = type;
			this.cnt = cnt;
		}
	}
}