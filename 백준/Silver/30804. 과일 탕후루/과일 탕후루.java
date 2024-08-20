import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1);
			return;
		}
		if(N == 2) {
			System.out.println(2);
			return;
		}
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int first = 0;
		int first_pos = 0;
		int second = 0;
		int second_pos = 0;
		
		int answer = 0;
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(first == 0) {
				queue.addLast(now);
				first = now;
				first_pos = cnt++;
				answer = Math.max(answer, cnt);
				continue;
			}
			if(second == 0 && now == first) {
				queue.addLast(now);
				first_pos = cnt++;
				answer = Math.max(answer, cnt);
				continue;
			}
			if(second == 0) {
				queue.addLast(now);
				second = now;
				second_pos = cnt++;
				answer = Math.max(answer, cnt);
				continue;
			}
			if(now == first) {
				queue.addLast(now);
				first = second;
				first_pos = second_pos;
				second = now;
				second_pos = cnt++;
				answer = Math.max(answer, cnt);
				continue;
			}
			if(now == second) {
				queue.addLast(now);
				second_pos = cnt++;
				answer = Math.max(answer, cnt);
				continue;
			}
			else {
				for(int j = 0; j <= first_pos; j++) {
					queue.removeFirst();
					cnt--;
				}
				queue.addLast(now);
				first = second;
				first_pos = second_pos-first_pos-1;
				second = now;
				second_pos = cnt++ ;
				answer = Math.max(answer, cnt);
				continue;
			}
		}
		System.out.println(answer);
	}

}