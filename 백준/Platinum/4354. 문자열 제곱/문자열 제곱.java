import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String pattern = br.readLine();
			if(pattern.equals(".")) break;
			
			int L = pattern.length();
			int[] pi = new int[L];
			
			int j = 0;
			for(int i = 1; i < L; i++) {
				while(pi[j] > 0 && pattern.charAt(i) != pattern.charAt(j)) {
					j = pi[j-1];
				}
				if(pattern.charAt(i) == pattern.charAt(j)) {
					j++;
					pi[i] = j;
				}
			}
			
			String str = pattern+pattern;
			L = str.length();
			j = 0;
			int cnt = 0;
			for(int i = 0; i < L-1; i++) {
				while(pi[j] > 0 && str.charAt(i) != pattern.charAt(j)) {
					j = pi[j-1];
				}
				if(str.charAt(i) == pattern.charAt(j)) {
					if(j == L/2-1) {
						cnt++;
						j = pi[j];
					}
					else j++;
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

}