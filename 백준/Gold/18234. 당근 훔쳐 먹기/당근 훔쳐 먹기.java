import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		List<Node> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long v = Long.parseLong(st.nextToken());
			long u = Long.parseLong(st.nextToken());
			list.add(new Node(v, u));
		}
		
		Collections.sort(list, (o1, o2) -> {
			if((int)o1.u ==(int)o2.u){
				return (int)o2.v - (int)o1.v;
			}
			return (int)o2.u-(int)o1.u;
		});
		
		long answer = 0;
		for(int i = 0; i < N; i++) {
			answer += list.get(i).v+list.get(i).u*(T-1-i);
		}
		System.out.println(answer);
	}

	static class Node{
		long v, u;

		public Node(long v, long u) {
			super();
			this.v = v;
			this.u = u;
		}
		
	}
}