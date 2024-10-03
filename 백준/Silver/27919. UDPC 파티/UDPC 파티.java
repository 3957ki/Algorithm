import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		double UC = 0;
		double DP = 0;
		
		char[] vote = br.readLine().toCharArray();
		int L = vote.length;
		for(int i = 0; i < L; i++) {
			if(vote[i] == 'U' || vote[i] == 'C') {
				UC++;
			}
			else {
				DP++;
			}
		}
		
		if(UC > Math.ceil(DP/2)) {
			sb.append('U');
		}
		if(DP > 0) {
			sb.append("DP");
		}
		
		if(sb.length() == 0) {
			sb.append('C');
		}
		System.out.println(sb);
	}

}