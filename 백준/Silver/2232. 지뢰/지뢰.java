import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
//		1개라면
		if(N == 1) {
			System.out.println(br.readLine());
			return;
		}
		
		int prev = Integer.parseInt(br.readLine());
//		1 증가중, -1 감소중
		int flag = 0;
		for(int i = 1; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
//			마지막인데 증가중이었다면
			if(i == N-1 && flag == 1 && prev < now) {
				sb.append(i+1).append('\n');
			}
//			마지막인데 증가하다가 감소라면
			else if(i == N-1 && flag == 1 && prev > now) {
				sb.append(i).append('\n');
			}
//			마지막인데 증가하다가 같다면
			else if(i == N-1 && flag == 1 && prev == now) {
				sb.append(i).append('\n').append(i+1).append('\n');
			}
//			마지막인데 감소중이라면
			else if(i == N-1 && flag == -1 && prev > now);
//			마지막인데 감소하다가 증가라면
			else if(i == N-1 && flag == -1 && prev <= now) {
				sb.append(i+1).append('\n');
			}
//			마지막인데 같다면
			else if(i == N-1 && flag == 0 && prev == now) {
				sb.append(i).append('\n').append(i+1).append('\n');
			}
			
//			시작부터 증가시
			else if(flag == 0 && prev < now) {
				flag = 1;
			}
//			시작부터 감소시
			else if(flag == 0 && prev > now) {
				sb.append(i).append('\n');
				flag = -1;
			}
//			시작과 같다면
			else if(flag == 0 && prev == now) {
				sb.append(i).append('\n');
				flag = 0;
			}
//			계속 증가중
			else if(flag == 1 && prev < now);
//			증가하다가 감소한다면
			else if(flag == 1 && prev > now) {
				flag = -1;
				sb.append(i).append('\n');
			}
//			증가하다가 같다면
			else if(flag == 1 && prev == now) {
				flag = 0;
				sb.append(i).append('\n');
			}
//			계속 감소중
			else if(flag == -1 && prev > now);
//			감소하다가 증가한다면
			else if(flag == -1 && prev < now) {
				flag = 1;
			}
//			감소하다가 같다면
			else if(flag == -1 && prev == now) {
				flag = 0;
			}
			prev = now;
		}
		System.out.println(sb);
	}

}