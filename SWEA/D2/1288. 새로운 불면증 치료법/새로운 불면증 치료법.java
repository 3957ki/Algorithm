import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int flag = 0;
			int N = Integer.parseInt(br.readLine());
			int answer = 0;
			while((flag & (1<<10)-1 ) != (1<<10)-1) {
				int n = N*(answer+1);
				while(n != 0) {
					int num = n%10;
					n /= 10;
					flag |= 1<<num;
				}
				answer++;
			}
			
			sb.append('#').append(t).append(' ').append(N*answer).append('\n');
		}
		System.out.println(sb);
	}

}