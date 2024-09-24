import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		long[] arr = new long[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long max = 0;
		for(int i = 0; i < N-1; i++) {
			max = Math.max(max, arr[0]*(i+1)+arr[i+1]*(N-1-i));
		}
		Long answer = K%max == 0 ? K/max : K/max+1;
		System.out.println(answer);
	}

}