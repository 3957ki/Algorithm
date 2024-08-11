import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, String> map = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			String name = br.readLine();
			String num = i+"";
			map.put(name, num);
			map.put(num, name);
		}
		for(int i = 0; i < M; i++) {
			String str = br.readLine();
			sb.append(map.get(str)).append('\n');
		}
		System.out.println(sb);
	}
}
