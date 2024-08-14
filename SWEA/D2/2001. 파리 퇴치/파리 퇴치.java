import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer = 0;
            int sum;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] arr = new int[n][n];
             
            for(int i = 0; i < n; i++) {
            	st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for(int i = 0; i < n-m+1; i++) {
                for(int j = 0; j < n-m+1; j++) {
                    sum = 0;
                    for(int k = 0; k < m; k++) {
                        for(int l = 0; l < m; l++) {
                            sum += arr[i+k][j+l];
                        }
                    }
                    answer = Math.max(answer, sum);
                }
            }
             
            sb.append('#').append(test_case).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
    }

}