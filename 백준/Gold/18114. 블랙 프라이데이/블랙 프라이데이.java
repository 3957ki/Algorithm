import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
//			C와 같은게 있다면 종료
			if(arr[i] == C) {
				System.out.println(1);
				return;
			}
		}
		
		Arrays.sort(arr);
		
		A: for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
//				같으면 종료
				if(arr[i]+arr[j] == C) {
					System.out.println(1);
					return;
				}
//				합이 C보다 작으면
				else if(arr[i]+arr[j] < C) {
					int num = C - (arr[i]+arr[j]);
					if(num != arr[i] && num != arr[j] && set.contains(num)) {
						System.out.println(1);
						return;
					}
				}
//				합이 C보다 크면
				else continue A;
			}
		}
		System.out.println(0);
	}

}