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
		int M = Integer.parseInt(st.nextToken());
		double R = Integer.parseInt(st.nextToken());
		
//		말뚝 배열
		int[] pin = new int[N];
//		깃대 배열
		int[] stick = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pin[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		}
		
//		정렬
		Arrays.sort(pin);
		Arrays.sort(stick);
		
		double answer = -1;
		int low;
		int high;
		int mid;
		
//		말뚝 2개 뽑기
		for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
				double width = pin[j]-pin[i];
				
				low = 0;
				high = M-1;
				
				while(low <= high) {
					mid = (low+high)/2;
					
					if((stick[mid]*width)/2 > R) {
						high = mid-1;
					}
					else if((stick[mid]*width)/2 < R) {
						low = mid+1;
						answer = Math.max(answer, (stick[mid]*width)/2);
					}
					else {
						System.out.printf("%.1f", R);
						return;
					}
				}
				
			}
		}
		
		if(answer == -1) {
			System.out.print(-1);
		}
		else {
			System.out.printf("%.1f", answer);
		}
		
	}

}