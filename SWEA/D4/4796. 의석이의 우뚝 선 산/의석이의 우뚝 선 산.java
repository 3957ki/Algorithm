import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = scan.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = scan.nextInt();
			int[] arr = new int[N];
			arr[0] = scan.nextInt();
			boolean flag = false;
			
			int icnt = 0;
			int jcnt = 0;
			int answer = 0;
			
			for (int i = 1; i < N; i++) {
				arr[i] = scan.nextInt();
				if(!flag && arr[i-1] < arr[i]) {
					answer += icnt*jcnt;
					icnt = 1;
					flag = true;
				}
				else if(flag && arr[i-1] < arr[i]) {
					icnt++;
				}
				else if(flag && arr[i-1] > arr[i]) {
					jcnt = 1;
					flag = false;
					if(i == N-1) {
						answer += icnt*jcnt;
					}
				}
				else if(!flag && arr[i-1] > arr[i]) {
					jcnt++;
					if(i == N-1) {
						answer += icnt*jcnt;
					}
				}
			}
			
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

}