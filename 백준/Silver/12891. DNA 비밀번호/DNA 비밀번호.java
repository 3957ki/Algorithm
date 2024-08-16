import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<Character> queue = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[] arr = br.readLine().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		int[] limit = new int[4];
		for(int i = 0; i < 4; i++) {
			limit[i] = Integer.parseInt(st.nextToken());
		}
		
		int Acnt = 0;
		int Ccnt = 0;
		int Gcnt = 0;
		int Tcnt = 0;
		int answer = 0;
		
		for(int i = 0; i < M; i++) {
			queue.addLast(arr[i]);
			switch (arr[i]) {
			case 'A':
				Acnt++;
				break;
			case 'C':
				Ccnt++;
				break;
			case 'G':
				Gcnt++;
				break;
			case 'T':
				Tcnt++;
				break;
			default:
				break;
			}
		}
		if(Acnt >= limit[0] && Ccnt >= limit[1] && Gcnt >= limit[2] && Tcnt >= limit[3]) {
			answer++;
		}
		
		for(int i = M; i < N; i++) {
			char out = queue.removeFirst();
			queue.addLast(arr[i]);
			switch (arr[i]) {
			case 'A':
				Acnt++;
				break;
			case 'C':
				Ccnt++;
				break;
			case 'G':
				Gcnt++;
				break;
			case 'T':
				Tcnt++;
				break;
			default:
				break;
			}
			
			switch (out) {
			case 'A':
				Acnt--;
				break;
			case 'C':
				Ccnt--;
				break;
			case 'G':
				Gcnt--;
				break;
			case 'T':
				Tcnt--;
				break;
			default:
				break;
			}
			
			if(Acnt >= limit[0] && Ccnt >= limit[1] && Gcnt >= limit[2] && Tcnt >= limit[3]) {
				answer++;
			}
		}
		System.out.println(answer);
	}

}