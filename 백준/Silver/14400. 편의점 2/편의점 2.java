import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] x = new int[N];
		int[] y = new int[N];
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
//			x
			arr[i][0] = x[i] = Integer.parseInt(st.nextToken());
//			y
			arr[i][1] = y[i] = Integer.parseInt(st.nextToken());
			
		}
		
		Arrays.sort(x);
		Arrays.sort(y);
		
		int i = y[N/2];
		int j = x[N/2];
		long sum = 0;
		for (int k = 0; k < N; k++) {
			sum += Math.abs(i - arr[k][1]) + Math.abs(j - arr[k][0]);
		}

		System.out.println(sum);
	}

}