import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[D];
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < D; i++){
			arr[i] = Math.min(arr[i-1], Integer.parseInt(st.nextToken()));
		}

		int L = D;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			int now = Integer.parseInt(st.nextToken());

			int high = L - 1;
			int low = 0;
			int max = -1;
			while(low <= high){
				int mid = (low + high) >> 1;

				if(arr[mid] >= now){
					low = mid + 1;
					max = Math.max(max, mid);
				}
				else{
					high = mid - 1;
				}
			}

			if(max == -1){
				System.out.println(0);
				System.exit(0);
			}

			L = max;
		}

		System.out.println(L + 1);
    }
}