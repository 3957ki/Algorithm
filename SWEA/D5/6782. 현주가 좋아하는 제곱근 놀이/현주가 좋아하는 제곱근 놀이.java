import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			long N = Long.parseLong(br.readLine());
			
			int answer = 0;
			while(N != 2) {
				double num = Math.sqrt(N);
				if(num == (long)num) {
					N = (long)num;
					answer++;
				}
				else {
					long temp =  (long) (Math.pow(Math.ceil(num), 2) - N);
					answer += temp;
					N += temp;
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

}