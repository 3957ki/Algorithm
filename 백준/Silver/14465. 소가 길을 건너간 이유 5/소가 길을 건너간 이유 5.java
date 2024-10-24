import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		boolean[] broken = new boolean[N+1];
		
//		고장난 번호
		for(int i = 0; i < B; i++) {
			int num = Integer.parseInt(br.readLine());
			broken[num] = true;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		int answer = K;
		int cnt = 0;
		for(int i = 1; i <= K; i++) {
			if(broken[i]) cnt++;
			queue.add(i);
		}
		
		answer = Math.min(cnt, answer);
		
		for(int i = K+1; i <= N; i++) {
			if(broken[queue.poll()]) cnt--;
			if(broken[i]) cnt++;
			queue.add(i);
			answer = Math.min(answer, cnt);
		}
		System.out.println(answer);
	}

}