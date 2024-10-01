import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[1000002];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())]++;
			arr[Integer.parseInt(st.nextToken())+1]--;
		}
		
		int[] seat = new int[1000002];
		for(int i = 1; i <= 1000001; i++) {
			seat[i] = seat[i-1]+arr[i];
		}
		
		int Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			sb.append(seat[Integer.parseInt(st.nextToken())]).append('\n');
		}
		System.out.println(sb);
	}

}