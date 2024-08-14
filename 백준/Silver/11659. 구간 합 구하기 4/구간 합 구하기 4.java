import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	dp[i] = dp[i-1]+arr[i];
        }
        
        for(int i = 0; i < M; i++) {
        	int sum = 0;
        	st = new StringTokenizer(br.readLine());
        	int first = Integer.parseInt(st.nextToken());
        	int last = Integer.parseInt(st.nextToken());
        	sum = dp[last]-dp[first-1];
        	sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }

}