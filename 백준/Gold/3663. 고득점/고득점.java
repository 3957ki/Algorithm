import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			String str = br.readLine();
			int N = str.length();
			int[] arr = new int[N];

			int cnt = 0;
			int min = N-1;
			int prev = 0;
			int last = 0;
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				arr[i] = str.charAt(i) - 'A';
				if(arr[i] == 0) continue;
				last = i;
				flag = true;
				arr[i] = Math.min(arr[i], 26 - arr[i]);
				cnt += arr[i];

				min = Math.min(min, prev * 2 + N - i);
				min = Math.min(min, prev + (N - i)*2);
				prev = i;
			}
			min = Math.min(min, last);
			sb.append(flag ? cnt + min : cnt).append('\n');
		}
		System.out.println(sb);
    }
}