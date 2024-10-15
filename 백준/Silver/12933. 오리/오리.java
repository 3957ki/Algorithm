import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		int L = arr.length;
		
		int[] cnt = new int[5];
		HashMap<Character, Integer> convert = new HashMap<>();
		convert.put('q', 0);
		convert.put('u', 1);
		convert.put('a', 2);
		convert.put('c', 3);
		convert.put('k', 4);
		
		int max = 0;
		for(int i = 0; i < L; i++) {
			int index = convert.get(arr[i]);
			if(index == 4) {
				for(int j = 0; j < 4; j++) {
					if(cnt[j] == 0) {
						System.out.println(-1);
						return;
					}
					cnt[j]--;
				}
			}
			else {
				
				cnt[index]++;
				for(int j = 0; j < index; j++) {
					if(cnt[j] < cnt[index]) {
						System.out.println(-1);
						return;
					}
				}
			}
			max = Math.max(max, cnt[0]);
		}
		for(int i = 0; i < 4; i++) {
			if(cnt[i] != cnt[i+1]) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(max);
	}

}