import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		Arrays.fill(arr, 1);
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for(int j = start; j < end; j++) {
				arr[j] = 0;
			}
		}
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			if(arr[i] == 1) answer++;
		}
		
		System.out.println(answer);
	}

}