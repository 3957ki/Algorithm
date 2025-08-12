import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Node[] arr = new Node[N];
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[i] = new Node(time, end);
		}

		Arrays.sort(arr, (o1, o2) -> o1.end - o2.end);

		int start = arr[0].end - arr[0].time;
		if(start < 0){
			System.out.println(-1);
			return;
		}

		int now = arr[0].end;
		for(int i = 1; i < N; i++){
			int val = arr[i].end - arr[i].time;

			if(val < now){
				start -= now - val;
				now += arr[i].time - now + val;
				if(start < 0){
					System.out.println(-1);
					return;
				}
			}else{
				now += arr[i].time;
			}
		}

		System.out.println(start);
    }

	static class Node{
		int time, end;
		Node(int time, int end){
			this.time = time;
			this.end = end;
		}
	}
}