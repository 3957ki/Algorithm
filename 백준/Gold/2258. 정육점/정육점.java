import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int total = 0;
		Node[] arr = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr[i] = new Node(weight, cost);
			total += weight;
		}

		if(total < M) {
			System.out.println(-1);
			return;
		}

		Arrays.sort(arr, (o1, o2) -> o1.cost == o2.cost ? o2.weight - o1.weight : o1.cost - o2.cost);

		int sum = arr[0].weight;
		if(sum >= M) {
			System.out.println(arr[0].cost);
			return;
		}

		int answer = Integer.MAX_VALUE;
		int cost = arr[0].cost;
		for(int i = 1; i < N; i++) {
			sum += arr[i].weight;
			if(arr[i].cost == arr[i-1].cost) cost += arr[i].cost;
			else cost = arr[i].cost;

			if(sum >= M) answer = Math.min(answer, cost);
		}

		System.out.println(answer);
    }

	static class Node {
		int weight;
		int cost;
		public Node(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}
	}
}