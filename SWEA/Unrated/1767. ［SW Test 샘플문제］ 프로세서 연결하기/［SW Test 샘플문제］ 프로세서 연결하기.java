import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, coreCnt, maxCnt, wireCnt;
	static List<Node> list;
	static int[] up, down, left, right;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			list = new ArrayList<>();
			coreCnt = 0;
			maxCnt = 0;
			wireCnt = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			up = new int[N];
			down = new int[N];
			left = new int[N];
			right = new int[N];
			Arrays.fill(up, N);
			Arrays.fill(down, 0);
			Arrays.fill(left, N);
			Arrays.fill(right, 0);
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						up[j] = Math.min(up[j], i);
						down[j] = Math.max(down[j], i);
						left[i] = Math.min(left[i], j);
						right[i] = Math.max(right[i], j);
					}
					if(map[i][j] == 1 && i > 0 && i < N-1 && j > 0 && j < N-1) {
						list.add(new Node(i, j));
					}
				}
			}
			coreCnt = list.size();
			DFS(0, 0, 0);
			
			sb.append('#').append(t).append(' ').append(wireCnt).append('\n');
		}
		System.out.println(sb);
	}

	static void DFS(int start, int sum, int cnt) {
		if(start == coreCnt) {
			if(maxCnt == cnt) {
				wireCnt = Math.min(wireCnt, sum);
			}
			else if(maxCnt < cnt) {
				maxCnt = cnt;
				wireCnt = sum;
			}
			return;
		}
		
		Node node = list.get(start);
		int y = node.y;
		int x = node.x;
		
		int[] temp_up, temp_down, temp_left, temp_right;
		temp_up = new int[N];
		temp_down = new int[N];
		temp_left = new int[N];
		temp_right = new int[N];
		
		for(int i = 0; i < N; i++) {
			temp_up[i] = up[i];
			temp_down[i] = down[i];
			temp_left[i] = left[i];
			temp_right[i] = right[i];
		}
//		위로 연결 가능
		if(up[x] >= y) {
//			연결
			for(int i = y; i >= 0; i--) {
				up[x] = Math.min(up[x], i);
				down[x] = Math.max(down[x], i);
				left[i] = Math.min(left[i], x);
				right[i] = Math.max(right[i], x);
			}
			DFS(start+1, sum+y, cnt+1);
			for(int i = y; i >= 0; i--) {
				up[x] = temp_up[x];
				down[x] = temp_down[x];
				left[i] = temp_left[i];
				right[i] = temp_right[i];
			}
		}
//		아래로 연결 가능
		if(down[x] <= y) {
//			연결
			for(int i = y; i < N; i++) {
				up[x] = Math.min(up[x], i);
				down[x] = Math.max(down[x], i);
				left[i] = Math.min(left[i], x);
				right[i] = Math.max(right[i], x);
			}
			DFS(start+1, sum+N-y-1, cnt+1);
			for(int i = y; i < N; i++) {
				up[x] = temp_up[x];
				down[x] = temp_down[x];
				left[i] = temp_left[i];
				right[i] = temp_right[i];
			}
		}
//		왼쪽으로 연결 가능
		if(left[y] >= x) {
//			연결
			for(int i = x; i >= 0; i--) {
				up[i] = Math.min(up[i], y);
				down[i] = Math.max(down[i], y);
				left[y] = Math.min(left[y], i);
				right[y] = Math.max(right[y], i);
			}
			DFS(start+1, sum+x, cnt+1);
			for(int i = x; i >= 0; i--) {
				up[i] = temp_up[i];
				down[i] = temp_down[i];
				left[y] = temp_left[y];
				right[y] = temp_right[y];
			}
		}
//		오른쪽으로 연결 가능
		if(right[y] <= x) {
//			연결
			for(int i = x; i < N; i++) {
				up[i] = Math.min(up[i], y);
				down[i] = Math.max(down[i], y);
				left[y] = Math.min(left[y], i);
				right[y] = Math.max(right[y], i);
			}
			DFS(start+1, sum+N-x-1, cnt+1);
			for(int i = x; i < N; i++) {
				up[i] = temp_up[i];
				down[i] = temp_down[i];
				left[y] = temp_left[y];
				right[y] = temp_right[y];
			}
		}
//		연결 안하기
		DFS(start+1, sum, cnt);
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}