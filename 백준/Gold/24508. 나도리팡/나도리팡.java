import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int sum = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}

		// 불가능
		if(sum % K > 0){
			System.out.println("NO");
			return;
		}

		Arrays.sort(arr);

		int l = 0;
		int r = N - 1;

		while(l < r){
			arr[r] += arr[l];

			if(K > arr[r]){
				T -= arr[l];
				if(T < 0){
					System.out.println("NO");
					return;
				}
				l++;
			}

			else{
				T -= arr[l] - arr[r] + K;
				arr[l] = arr[r] - K;
				if(T < 0){
					System.out.println("NO");
					return;
				}
				r--;
			}
		}
		System.out.println("YES");
    }
}