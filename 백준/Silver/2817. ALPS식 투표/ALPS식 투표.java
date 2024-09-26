import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int X = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		PriorityQueue<Score> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.val > o2.val) return -1;
			return 1;
		});
		
//		전체중 5%
		double cutLine = X*0.05;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char staff = st.nextToken().charAt(0);
			double vote = Integer.parseInt(st.nextToken());
//			미달이면 패스
			if(vote < cutLine) continue;
			map.put(staff, 0);
//			나누어가며 pq에 넣기
			for(int j = 1; j <= 14; j++) {
				pq.add(new Score(staff, vote/j));
			}
		}
		
		for(int i = 0; i < 14; i++) {
			Score now = pq.poll();
			map.put(now.who, map.get(now.who)+1);
		}
		
		for(Character staff : map.keySet()) {
			sb.append(staff).append(' ').append(map.get(staff)).append('\n');
		}
		
		System.out.println(sb);
	}

	static class Score{
		char who;
		double val;
		public Score(char who, double val) {
			this.who = who;
			this.val = val;
		}
		
	}
}