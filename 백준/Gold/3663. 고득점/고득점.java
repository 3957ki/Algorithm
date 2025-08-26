import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			String str = br.readLine();
			int N = str.length();

			int cnt = 0;	// 상하 이동 횟수
			int min = N-1;	// 좌우 이동 횟수
			int prev = 0;
			int last = 0;
			for(int i = 0; i < N; i++) {
				int now = str.charAt(i) - 'A';
				if(now == 0) continue;
				last = i;
				cnt += Math.min(now, 26 - now);
				min = Math.min(min, prev * 2 + N - i);
				min = Math.min(min, prev + (N - i) * 2);
				prev = i;
			}
			min = Math.min(min, last);
			sb.append(cnt == 0 ? cnt : cnt + min).append('\n');
		}
		System.out.println(sb);
    }
}