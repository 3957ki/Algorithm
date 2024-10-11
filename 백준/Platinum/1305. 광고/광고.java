import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String pattern = br.readLine();
		
		int[] pi = new int[N];
		int j = 0;
		for(int i = 1; i < N; i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				pi[i]+=j;
			}
		}
		System.out.println(N-pi[N-1]);
	}

}