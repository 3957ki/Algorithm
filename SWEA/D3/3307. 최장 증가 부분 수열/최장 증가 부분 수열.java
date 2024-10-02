import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer> list = new ArrayList<>();
			list.add(arr[0]);
			
			for(int i = 1; i < N; i++) {
				int low = 0;
				int high = list.size()-1;
				int mid;
				int min = Integer.MAX_VALUE;
				while(low <= high) {
					mid = (low+high)/2;
					
					if(list.get(mid) >= arr[i]) {
						high = mid - 1;
						min = Math.min(min, mid);
					}
					else {
						low = mid + 1;
					}
				}
				if(min == Integer.MAX_VALUE) {
					list.add(arr[i]);
				}
				else {
					list.set(min, arr[i]);
				}
			}
			
			sb.append('#').append(t).append(' ').append(list.size()).append('\n');
		}
		System.out.println(sb);
	}

}