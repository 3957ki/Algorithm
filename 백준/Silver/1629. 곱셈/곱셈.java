import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = 1;
		while(B != 0) {
			if((B & 1) == 1) {
				answer = (answer*A)%C;
			}
			A = ((A%C)*(A%C))%C;
			B = B >> 1;
		}
		System.out.println(answer);
	}

}