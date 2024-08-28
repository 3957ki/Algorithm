import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(M == 0) {
			System.out.println((int)Math.pow(10, N));
			return;
		}
		int visited = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			visited |= 1<<i;
		}
		perm(0, visited);
		System.out.println(answer);
	}

	static void perm(int cnt, int visited) {
		if(cnt == N) {
			if(visited != 0) return;
			answer++;
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			perm(cnt+1, visited & ~(1<<i));
		}
	}
}