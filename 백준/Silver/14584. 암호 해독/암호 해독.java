import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		int L = str.length;
		
		int N = Integer.parseInt(br.readLine());
		
		HashSet<String> set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		for(int i = 0; i < 26; i++) {
			String temp = "";
			for(int j = 0; j < L; j++) temp+= (char)((str[j]+i)%26+'a');
			for(String pattern : set) {
				if(temp.contains(pattern)) {
					System.out.println(temp);
					return;
				}
			}
		}
	}

}