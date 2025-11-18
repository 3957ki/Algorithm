import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] arr;
	static final int SUCCESS = (1 << 26) - 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		for(int i = 0; i < N; i++){
			String name = br.readLine();
			int L = name.length();
			int bit = 0;
			for(int j = 0; j < L; j++){
				bit |= 1 << (name.charAt(j) - 'a');
			}
			arr[i] = bit;
		}

		System.out.println(subset(0, 0));
    }

	static int subset(int now, int status){
		if(now == N){
			if(status == SUCCESS) return 1;
			return 0;
		}
		return subset(now + 1, status) + subset(now + 1, status | arr[now]);
	}

}