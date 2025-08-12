import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int answer = Integer.MAX_VALUE;
	static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		arr = new int[N][N];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int k = 0; k < N; k++){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if(i == j) continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		perm(start, 1 << start, 0, N);
		System.out.println(answer);
    }

	static void perm(int now, int visited, int sum, int N){
		if(visited == (1 << N) - 1){
			answer = Math.min(answer, sum);
			return;
		}

		for(int i = 0; i < N; i++){
			if((visited & (1 << i)) > 0) continue;
			if(sum + arr[now][i] > answer) continue;
			perm(i, visited | (1 << i), sum + arr[now][i], N);
		}

	}
}