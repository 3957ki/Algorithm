import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>(N);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				char order = st.nextToken().charAt(0);
				switch (order) {
				case 'I':
					int index = Integer.parseInt(st.nextToken());
					int cnt = Integer.parseInt(st.nextToken());
					for(int c = 0; c < cnt; c++) {
						list.add(index, Integer.parseInt(st.nextToken()));
						index++;
					}
					break;
				case 'D':
					int index_d = Integer.parseInt(st.nextToken());
					int cnt_d = Integer.parseInt(st.nextToken());
					for(int c = 0; c < cnt_d; c++) {
						list.remove(index_d);
					}
					break;
				case 'A':
					int cnt_a = Integer.parseInt(st.nextToken());
					for(int c = 0; c < cnt_a; c++) {
						list.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}
			}
			sb.append('#').append(t).append(' ');
			for(int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}