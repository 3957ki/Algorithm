import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[] arr = br.readLine().toCharArray();
		
		for(int i = N-1; i >= 0; i--) {
			if(sb.length() == 0 && arr[i] != 'A' && arr[i] != 'E' && arr[i] != 'I' && arr[i] != 'O' && arr[i] != 'U'){
				sb.append(arr[i]);
			}
			else if(sb.length() < 3 && sb.length() > 0 && arr[i] == 'A'){
				sb.append(arr[i]);
			}
			else if(sb.length() >= 3 && sb.length() < M){
				sb.append(arr[i]);
			}
		}
		
        if(sb.length() == M){
            System.out.println("YES");
            System.out.println(sb.reverse());
        }else{
            System.out.println("NO");
        }
	}

}