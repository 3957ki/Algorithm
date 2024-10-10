import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		StringTokenizer st;
		HashSet<String> rabbits = new HashSet<>();
		rabbits.add("ChongChong");
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String start = st.nextToken();
			String end = st.nextToken();
			
			if(rabbits.contains(start)) {
				rabbits.add(end);
			}
			else if(rabbits.contains(end)) {
				rabbits.add(start);
			}
			
		}
		
		System.out.println(rabbits.size());
	}

}