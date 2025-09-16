import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		func(0, 0, 1, false);
		System.out.println(-1);
    }

	static void func(long now, int prev, int cnt, boolean flag){
		if(cnt == 11){
			if(--N == 0){
				System.out.println(now / 10);
				System.exit(0);
			}
			return;
		}

		if(!flag){
			for(int i = 0; i <= 9; i++){
				func((now+i)*10, i, cnt+1, flag);
				flag = true;
			}
		}
		else{
			for(int i = 0; i <= 9; i++){
				if(i >= prev) return;
				func((now+i)*10, i, cnt+1, flag);
			}
		}
    }
}