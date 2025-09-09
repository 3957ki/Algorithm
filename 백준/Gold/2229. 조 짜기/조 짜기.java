import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++){
			arr[i] = Integer.parseInt(st.nextToken());

			int min = arr[i];
			int max = arr[i];
			for(int j = i - 1; j >= 0; j--){
				min = Math.min(min, arr[j+1]);
				max = Math.max(max, arr[j+1]);
				dp[i] = Math.max(dp[i], dp[j] + max - min);
			}
		}
		
		System.out.println(dp[N]);
    }
}