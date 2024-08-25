import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[L1][L2]);
	}

}