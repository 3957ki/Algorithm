import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		int[] idx = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int num = arr[i];
			if(list.isEmpty() || list.get(list.size()-1) < num) {
				list.add(num);
				idx[i] = list.size()-1;
			}
			
			else {
				int low = 0;
				int high = list.size()-1;
				int mid;
				int pos = 0;
				while(low <= high) {
					mid = (low+high)/2;
					if(list.get(mid) >= num) {
						pos = mid;
						high = mid - 1;
					}
					else {
						low = mid + 1;
					}
				}
				list.set(pos, num);
				idx[i] = pos;
			}
		}
		sb.append(list.size()).append('\n');

		int index = list.size()-1;
		Stack<Integer> stack = new Stack<>();
		for(int i = N-1; i >= 0; i--) {
			if(idx[i] == index) {
				stack.push(arr[i]);
				index--;
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(' ');
		}
		System.out.println(sb);
	}

}