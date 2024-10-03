import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		HashSet<Double> set = new HashSet<>();
		int[] arr = new int[1001];
		arr[1] = 3;
		
		for(int x = 2; x <= 1000; x++) {
			arr[x] = arr[x-1];
			for(double y = 1; y < x; y++) {
				if(set.contains(y/x)) continue;
				set.add(y/x);
				arr[x]+=2;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(arr[N]).append('\n');
		}
		System.out.println(sb);
	}

}