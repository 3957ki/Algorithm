import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+1][N+1];

		for(int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for(int i = 1; i <= N; i++){
			if(Row(i, 1, new boolean[N+1], map)) answer++;
			if(Col(1, i, new boolean[N+1], map)) answer++;
		}

		System.out.println(answer);
    }

	static boolean Row(int y, int x, boolean[] visited, int[][] map){

		if(x >= N){
			return true;
		}

		// 갈 곳이 높을 때
		if(map[y][x] + 1 == map[y][x+1]){
			for(int i = 0; i < L; i++){
				if(x - i <= 0 || visited[x-i] || map[y][x] != map[y][x-i]) return false;
				visited[x-i] = true;
			}

			return Row(y, x+1, visited, map);
		}
		
		// 갈 곳이 낮을 때
		else if(map[y][x] == map[y][x+1] + 1){
			for(int i = 1; i <= L; i++){
				if(x + i > N || map[y][x+1] != map[y][x+i]) return false;
				visited[x+i] = true;
			}

			return Row(y, x + L, visited, map);
		}

		// 같을 때
		else if(map[y][x] == map[y][x+1]){
			return Row(y, x+1, visited, map);
		}

		// 불가능
		return false;
	}

	static boolean Col(int y, int x, boolean[] visited, int[][] map){
		if(y >= N){
			return true;
		}

		// 갈 곳이 높을 때
		if(map[y][x] + 1 == map[y+1][x]){
			for(int i = 0; i < L; i++){
				if(y - i <= 0 || visited[y-i] || map[y][x] != map[y-i][x]) return false;
				visited[y - i] = true;
			}

			return Col(y+1, x, visited, map);
		}

		// 갈 곳이 낮을 때
		else if(map[y][x] == map[y+1][x] + 1){
			for(int i = 1; i <= L; i++){
				if(y + i > N || map[y+1][x] != map[y+i][x]) return false;
				visited[y + i] = true;
			}

			return Col(y + L, x, visited, map);
		}

		// 같을 때
		else if(map[y][x] == map[y+1][x]){
			return Col(y+1, x, visited, map);
		}

		// 불가능
		return false;
	}
}