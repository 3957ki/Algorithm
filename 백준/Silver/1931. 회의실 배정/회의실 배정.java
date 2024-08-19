import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[i] = new Node(start, end);
		}
		
		Arrays.sort(arr, (o1, o2) -> {
			if(o1.end == o2.end) {
				return o1.start -o2.start;
			}
			return o1.end - o2.end;
		});
		
		int answer = 0;
		int prev = 0;
		for(Node node : arr) {
			if(node.start >= prev) {
				answer++;
				prev = node.end;
			}
		}
		System.out.println(answer);
	}
	
	static class Node{
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}