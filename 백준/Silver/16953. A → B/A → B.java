import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int answer = recur(A, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer+1);
	}

	static int recur(long A, int cnt) {
		if(A == B) {
			return cnt;
		}
		if(A > B) {
			return Integer.MAX_VALUE;
		}
		return Math.min(recur(A<<1, cnt+1), recur(A*10+1, cnt+1));
	}
}