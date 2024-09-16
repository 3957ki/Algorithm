import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] c1 = br.readLine().toCharArray();
		char[] c2 = br.readLine().toCharArray();
		
		int L1 = c1.length;
		int L2 = c2.length;
		
		int[][] dp = new int[L1+1][L2+1];
		
		for(int i = 1; i <= L1; i++) {
			for(int j = 1; j <= L2; j++) {
				if(c1[i-1] == c2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
//		길이가 0이면 종료
		if(dp[L1][L2] == 0) {
			System.out.println(0);
			return;
		}
		
		sb.append(dp[L1][L2]).append('\n');
		
		Stack<Character> stack = new Stack<>();
		
		int i =  L1;
		int j =  L2;
		
		while(i > 0 && j > 0) {
			if(dp[i][j] == dp[i-1][j]) {
				i--;
			}
			else if(dp[i][j] == dp[i][j-1]) {
				j--;
			}
//			lcs
			else {
				stack.push(c1[i-1]);
				i--;
				j--;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}

}