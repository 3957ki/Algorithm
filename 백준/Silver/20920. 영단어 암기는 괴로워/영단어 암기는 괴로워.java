import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map = new HashMap<>();
		
		TreeSet<String> set = new TreeSet<>((o1, o2) -> {
			if(map.get(o1) == map.get(o2)) {
				if(o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o2.length() - o1.length();
			}
			return map.get(o2) - map.get(o1);
		});
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			if(str.length() < M) continue;
			if(map.containsKey(str)) set.remove(str);	
			map.put(str, map.getOrDefault(str, 0)+1);
			set.add(str);
		}
		
		for(String str : set) {
			sb.append(str).append('\n');
		}
		
		System.out.println(sb);
	}

}