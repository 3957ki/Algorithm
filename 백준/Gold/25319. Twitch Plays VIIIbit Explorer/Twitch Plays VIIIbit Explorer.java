import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
//		알파벳별 좌표
		Queue<Node>[] arr = new Queue['z'+1];
		for(int i = 'a'; i <= 'z'; i++) arr[i] = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				char c = str.charAt(j);
				arr[c].add(new Node(i, j));
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		
		String id = br.readLine();
		
		int startY = 0;
		int startX = 0;
		int lastY = 0;
		int lastX = 0;
		int cnt = 0;
		
		A: while(true) {
			StringBuilder sb1 = new StringBuilder();
			for(int i = 0; i < L; i++) {
				while(true) {
//					큐가 비었으면 종료
					if(arr[id.charAt(i)].isEmpty()) break A;
//					뽑기
					Node now = arr[id.charAt(i)].poll();
//					방문했다면 다시 뽑기
					if(visited[now.y][now.x]) continue;
					visited[now.y][now.x] = true;
//					내려가기
					if(startY < now.y) {
						for(int j = 0; j < now.y-startY; j++) {
							sb1.append('D');
						}
					}
//					올라가기
					else if(startY > now.y) {
						for(int j = 0; j < startY-now.y; j++) {
							sb1.append('U');
						}
					}
//					오른쪽
					if(startX < now.x) {
						for(int j = 0; j < now.x-startX; j++) {
							sb1.append('R');
						}
					}
//					왼쪽
					else if(startX > now.x) {
						for(int j = 0; j < startX-now.x; j++) {
							sb1.append('L');
						}
					}
					sb1.append('P');
//					현재위치 갱신
					startY = now.y;
					startX = now.x;
//					id마지막이라면 cnt++
					if(i == L-1) {
						sb.append(sb1);
						lastY = now.y;
						lastX = now.x;
						cnt++;
					}
					break;
				}
			}
		}
//		내려가기
		if(lastY < N-1) {
			for(int j = 0; j < N-1-lastY; j++) {
				sb.append('D');
			}
		}
//		올라가기
		else if(lastY > N-1) {
			for(int j = 0; j < lastY-N+1; j++) {
				sb.append('U');
			}
		}
//		오른쪽
		if(lastX < M-1) {
			for(int j = 0; j < M-1-lastX; j++) {
				sb.append('R');
			}
		}
//		왼쪽
		else if(lastX > lastX) {
			for(int j = 0; j < lastX-M+1; j++) {
				sb.append('L');
			}
		}
		System.out.println(cnt+" "+sb.length());
		System.out.println(sb);
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