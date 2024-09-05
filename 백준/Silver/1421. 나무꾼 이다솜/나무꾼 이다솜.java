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
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long answer = 0;
		
		for(int i = 1; i <= max; i++) {
			long money = 0;
			
			for(int j = 0; j < N; j++) {
				if(arr[j] < i) continue;
				//나무 개수
				int cnt = arr[j]/i;
				//자른 횟수
				int cut;
				if(arr[j]%i == 0) {
					cut = arr[j]/i-1;
				}
				else {
					cut = arr[j]/i;
				}
				//손해면 패스
				if(cnt*i*W - C*cut < 0) continue;
				money += cnt*i*W - C*cut;
			}
			answer = Math.max(answer, money);
		}
		System.out.println(answer);
		
	}
	

}