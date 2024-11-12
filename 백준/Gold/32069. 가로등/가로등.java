import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		long L = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken()); 
		int K = Integer.parseInt(st.nextToken()); 

		long[] arr = new long[N+2];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		arr[0] = -1;
		arr[N+1] = L+1;

		if(K <= N) {
			//		K개
			for(int i = 0; i < K; i++) {
				sb.append(0).append('\n');
			}
			System.out.println(sb);
			return;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		Set<Long> set = new HashSet<>();
//		N개
		for(int i = 1; i <= N; i++) {
			sb.append(0).append('\n');
			queue.add(i);
			set.add(arr[i]);
		}

		K -= N;

		int dst = 0;
		A: while(!queue.isEmpty()) {
			dst++;
			int S = queue.size();
			while(S-- > 0) {
				int i = queue.poll();
				if(arr[i]-dst >= 0 && !set.contains(arr[i]-dst)) {
					set.add(arr[i]-dst);
					queue.add(i);
					sb.append(dst).append('\n');
					if(--K == 0) break A;
				}
				if(arr[i]+dst <= L && !set.contains(arr[i]+dst)) {
					set.add(arr[i]+dst);
					queue.add(i);
					sb.append(dst).append('\n');
					if(--K == 0) break A;
				}
			}
		}
		System.out.println(sb);
	}

}