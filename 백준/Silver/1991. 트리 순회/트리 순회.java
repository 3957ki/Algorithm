import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static char[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char['Z'+1][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			arr[root][0] = left;
			arr[root][1] = right;
		}
		func1('A');
		sb.append('\n');
		func2('A');
		sb.append('\n');
		func3('A');
		System.out.println(sb);
	}
	
	static void func1(char root) {
		sb.append(root);
		if(arr[root][0] !='.') {
			func1(arr[root][0]);
		}
		if(arr[root][1] !='.') {
			func1(arr[root][1]);
		}
	}
	static void func2(char root) {
		if(arr[root][0] !='.') {
			func2(arr[root][0]);
		}
		sb.append(root);
		if(arr[root][1] !='.') {
			func2(arr[root][1]);
		}
	}
	static void func3(char root) {
		if(arr[root][0] !='.') {
			func3(arr[root][0]);
		}
		if(arr[root][1] !='.') {
			func3(arr[root][1]);
		}
		sb.append(root);
	}
}