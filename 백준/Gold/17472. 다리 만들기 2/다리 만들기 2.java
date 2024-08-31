import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int L;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[N][M];
		
//		섬 개수
		L = 0;
//		섬 탐색 BFS
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				L++;
				queue.add(new Node(i, j));
				visited[i][j] = true;
//				섬 번호 지정
				map[i][j] = L;
				while(!queue.isEmpty()) {
					Node node = queue.poll();
					for(int d = 0; d < 4; d++) {
						int y = node.y + dy[d];
						int x = node.x + dx[d];
						if(x < 0 || x >= M || y < 0 || y >= N || visited[y][x] || map[y][x] == 0) continue;
						map[y][x] = L;
						visited[y][x] = true;
						queue.add(new Node(y, x));
					}
				}
			}
		}
		
		parents = new int[L+1];
		make();
//		간선 리스트
		List<Edge> edgeList = new ArrayList<>();
		
//		간선 찾기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
//				섬에서 4방 탐색
				for(int d = 0; d < 4; d++) {
					int y = i + dy[d];
					int x = j + dx[d];
//					못가거나 해안가가 아니면 패스
					if(x < 0 || x >= M || y < 0 || y >= N || map[y][x] != 0) continue;
					
					int tempY = y+dy[d];
					int tempX = x+dx[d];
					int k = 0;
					while(tempX >= 0 && tempX < M && tempY >= 0 && tempY < N) {
//						섬을 찾으면
						if(map[tempY][tempX] != 0) {
//							다리길이가 1이면 패스
							if(k == 0) break;
//							간선 저장
							edgeList.add(new Edge(map[i][j], map[tempY][tempX], Math.abs(i - tempY) + Math.abs(j - tempX)-1));
							break;
						}
						tempY += dy[d];
						tempX += dx[d];
						k++;
					}
				}
			}
		}
		
		Collections.sort(edgeList, (o1, o2) -> o1.weight - o2.weight);
		
//		크루스칼
		int cnt = 0;
		int answer = 0;
		for(Edge edge : edgeList) {
			if(union(edge.start, edge.end)) {
				answer+=edge.weight;
				if(++cnt == L-1) break;
			}
		}
//		트리의 노드개수가 섬개수랑 다르면 -1
		System.out.println(Math.abs(parents[findSet(1)]) == L ? answer : -1);
	}

	static void make() {
		for(int i = 1; i <= L; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	static class Edge{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
	}
	
}