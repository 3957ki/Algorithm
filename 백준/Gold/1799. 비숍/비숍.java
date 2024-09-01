import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Node> blackBis;
	static List<Node> whiteBis;
	static int N, BL, WL;
	static int Banswer, Wanswer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
//		흑백 끼리는 서로 견제되지 않으니 나누기
		blackBis = new ArrayList<>();
		whiteBis = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
                    if ((i + j) % 2 == 0) { // 흑
                        blackBis.add(new Node(i, j));
                    } else { // 백
                        whiteBis.add(new Node(i, j));
                    }
                }
			}
		}
		BL = blackBis.size();
		WL = whiteBis.size();
		
//		왼쪽 위 대각선은 차가 같음
//		오른쪽 위 대각선은 합이 같음
		B_DFS(0, 0, 0, 0);
		W_DFS(0, 0, 0, 0);
		System.out.println(Banswer+Wanswer);
	}
	
	static void B_DFS(int start, int cnt, long left, long right) {
//		남은 비숍을 다 놓아도 최대값 이하면 return;
		if(BL-start + cnt <= Banswer) return;
		
		if(start == BL) {
			Banswer = Math.max(Banswer, cnt);
			return;
		}
		
		
		Node now = blackBis.get(start);
		int y = now.y;
		int x = now.x;
		
//		놓을 수 있다면 놓아보기
		if((left & 1<<(y-x+N)) == 0 && (right & 1<<(y+x)) == 0) {
			B_DFS(start+1, cnt+1, left | 1<<(y-x+N), right | 1<<(y+x));
		}
		B_DFS(start+1, cnt, left, right);
	}
	
	static void W_DFS(int start, int cnt, long left, long right) {
//		남은 비숍을 다 놓아도 최대값 이하면 return;
		if(WL-start + cnt <= Wanswer) return;
		
		if(start == WL) {
			Wanswer = Math.max(Wanswer, cnt);
			return;
		}
		
		
		Node now = whiteBis.get(start);
		int y = now.y;
		int x = now.x;
		
//		놓을 수 있다면 놓아보기
		if((left & 1<<(y-x+N)) == 0 && (right & 1<<(y+x)) == 0) {
			W_DFS(start+1, cnt+1, left | 1<<(y-x+N), right | 1<<(y+x));
		}
		W_DFS(start+1, cnt, left, right);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}