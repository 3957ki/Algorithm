import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
//			간선 리스트
			int L = N*N;
			List<Node>[] edges = new ArrayList[L];
			for(int i = 0; i < L; i ++) {
				edges[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
					for(int d = 0; d < 4; d++) {
						int y = i+dy[d];
						int x = j+dx[d];
						if(x < 0 || x >= N || y < 0 || y >= N) continue;
//						간선 저장
						edges[y*N+x].add(new Node(i*N+j, map[i][j]));
					}
				}
			}
			
//			다익스트라
			boolean[] visited = new boolean[L];
			int[] dist = new int[L];
			Arrays.fill(dist, INF);
			dist[0] = 0;
//			시작지점 0
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
			pq.add(new Node(0, 0));
			
			while(!pq.isEmpty()) {
				int now = pq.poll().v;
//				방문 처리
				if(visited[now]) continue;
				visited[now] = true;
//				다음 위치 탐색
				for(Node next : edges[now]) {
					if(dist[next.v] > dist[now] + next.w) {
						dist[next.v] = dist[now] + next.w;
						pq.add(new Node(next.v, dist[next.v]));
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(dist[L-1]).append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
	}
}