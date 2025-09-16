import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		if(A == B && B == C){
			System.out.println(1);
			return;
		}

		int sum = A + B + C;
		if(sum % 3 != 0 || (sum / 3) % 2 == 1){
			System.out.println(0);
			return;
		}

		Queue<int[]> queue = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();

		int[] arr = new int[]{A, B, C};
		Arrays.sort(arr);

		queue.add(arr);
		set.add(arr[0]*1000 + arr[1]);

		while(!queue.isEmpty()){
			int[] now = queue.poll();

			if(now[0] == now[1] && now[1] == now[2]){
				System.out.println(1);
				return;
			}

			if(now[0] != now[1]){
				int[] next = new int[]{now[0]*2, now[1]-now[0], now[2]};
				Arrays.sort(next);
				if(!set.contains(next[0]*1000 + next[1])){
					set.add(next[0]*1000 + next[1]);
					queue.add(next);
				}
			}
			if(now[1] != now[2]) {
				int[] next = new int[]{now[0], now[1]*2, now[2]-now[1]};
				Arrays.sort(next);
				if(!set.contains(next[0]*1000 + next[1])){
					set.add(next[0]*1000 + next[1]);
					queue.add(next);
				}

			}
			if(now[0] != now[2]){
				int[] next = new int[]{now[0]*2, now[1], now[2]-now[0]};
				Arrays.sort(next);
				if(!set.contains(next[0]*1000 + next[1])){
					set.add(next[0]*1000 + next[1]);
					queue.add(next);
				}
			}
		}

		System.out.println(0);
    }
}