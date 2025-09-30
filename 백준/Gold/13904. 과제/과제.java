import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Node[] arr = new Node[N];

		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[i] = new Node(d, w);
		}

		Arrays.sort(arr, (o1, o2) -> o1.d - o2.d);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++){
			pq.add(arr[i].w);
			if(pq.size() > arr[i].d) pq.poll();
		}

		int answer = 0;
		while(!pq.isEmpty()){
			answer += pq.poll();
		}

		System.out.println(answer);
    }

	static class Node{
		int d, w;
		Node(int d, int w){
			this.d = d;
			this.w = w;
		}
	}
}