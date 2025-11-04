import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Node[] arr =  new Node[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken()) - 1;

			arr[i] = new Node(start, end);
		}

		Arrays.sort(arr, (o1, o2) -> o1.start - o2.start);

		int answer = 0;
		int prev = -1;
		for(Node now : arr){
			if(now.end > prev){
				if(now.start > prev){
					int cnt = (now.end - now.start) / L + 1;
					answer += cnt;
					prev = now.start + L * cnt - 1;
				}
				else{
					int cnt = (now.end - prev - 1) / L + 1;
					answer += cnt;
					prev = prev + L * cnt;
				}
			}
		}

		System.out.println(answer);
    }

	static class Node{
		int start, end;
		Node(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
}