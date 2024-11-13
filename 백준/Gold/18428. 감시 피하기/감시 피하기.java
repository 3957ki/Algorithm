import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int L, N;
	static List<Node> empty, students, teachers;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new char[N+1][N+1];
		
		empty = new ArrayList<>();
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				
//				학생
				if(map[i][j] == 'S') students.add(new Node(i, j));				
				
//				선생
				else if(map[i][j] == 'T') teachers.add(new Node(i, j));
				
//				빈 공간
				else empty.add(new Node(i, j));
			}
		}
		
		L = empty.size();
		
		comb(0, 0);
		
		System.out.println("NO");
	}

	static void comb(int cnt, int start) {
		if(cnt == 3) {
			if(check()) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		
		for(int i = start; i < L; i++) {
			Node now = empty.get(i);
			map[now.y][now.x] = 'O';
			comb(cnt+1, i+1);
			map[now.y][now.x] = 'X';
		}
	}
	
	static boolean check() {
		for(Node teacher : teachers) {
			for(int i = teacher.x+1; i <= N; i++) {
				if(map[teacher.y][i] == 'O') break;
				if(map[teacher.y][i] == 'S') return false;
			}
			for(int i = teacher.y+1; i <= N; i++) {
				if(map[i][teacher.x] == 'O') break;
				if(map[i][teacher.x] == 'S') return false;
			}
			for(int i = teacher.x-1; i > 0; i--) {
				if(map[teacher.y][i] == 'O') break;
				if(map[teacher.y][i] == 'S') return false;
			}
			for(int i = teacher.y-1; i > 0; i--) {
				if(map[i][teacher.x] == 'O') break;
				if(map[i][teacher.x] == 'S') return false;				
			}
		}
		return true;
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}