import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int num = 0;
		HashMap<String, String> map = new HashMap<>();
		List<String> list = new ArrayList<>(N);
		for(int i = 0; i < N; i++) {
			map.put(br.readLine(), null);
		}
		for(int i = 0; i < M; i++) {
			String name = br.readLine();
			if(map.containsKey(name)) {
				list.add(name);
				num++;
			}
		}
		Collections.sort(list);
		sb.append(num).append('\n');
		for(String name : list) {
			sb.append(name).append('\n');
		}
		System.out.println(sb);
	}
}
