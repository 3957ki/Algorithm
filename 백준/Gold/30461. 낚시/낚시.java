import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+2][M+2];

		for(int i = 2; i <= N+1; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 2; j <= M+1; j++){
				int now = Integer.parseInt(st.nextToken());
				map[i][j] = map[i-1][j-1] + map[i-1][j] - map[i-2][j-1] + now;
			}
		}

		while(Q-- > 0){
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			sb.append(map[y+1][x+1]).append('\n');
		}

		System.out.println(sb);
    }
}