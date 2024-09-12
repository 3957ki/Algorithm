import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			char c = str.charAt(i);
			if(c == 'A') pq.add(i);
		}
		
		if(pq.size() < 2) {
			System.out.println("NO");
			return;
		}
		
		char end = '0';
		
		for(int i = pq.poll()+1; i < N; i++) {
			char now = str.charAt(i);
			if(now == 'E' || now == 'I' || now == 'O' || now == 'U') continue;
			end = now;
			break;
		}
		
		if(end == '0') {
			System.out.println("NO");
			return;
		}
		
		int pos = pq.poll();
		if(pos+3 < M) {
			System.out.println("NO");
			return;
		}
		sb.append("YES").append('\n');
		for(int i = 0; i < M-3; i++) {
			sb.append(str.charAt(i));
		}
		sb.append("AA").append(end);
		System.out.println(sb);
	}

}