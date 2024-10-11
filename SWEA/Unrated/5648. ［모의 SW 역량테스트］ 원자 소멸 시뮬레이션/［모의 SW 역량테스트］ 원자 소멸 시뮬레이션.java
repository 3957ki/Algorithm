import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			Node[] arr = new Node[N];
			PriorityQueue<Crash> queue = new PriorityQueue<>((o1, o2) -> (int)((o1.time - o2.time)*10));
			double[] crashTime = new double[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				arr[i] = new Node(x, y, dir, k);
			}
			
			for(int i = 0; i < N-1; i++) {
				for(int j = i; j < N; j++) {
//					상
					if(arr[i].dir == 0) {
						if(arr[i].x == arr[j].x && arr[i].y < arr[j].y && arr[j].dir == 1) {
							queue.add(new Crash(i, j, (arr[j].y - arr[i].y)/2.0));
						}
						else if(Math.abs(arr[i].x - arr[j].x) == Math.abs(arr[i].y - arr[j].y)) {
							if(arr[i].x < arr[j].x && arr[i].y < arr[j].y && arr[j].dir == 2) {
								queue.add(new Crash(i, j, arr[j].y - arr[i].y));
							}
							else if(arr[i].x > arr[j].x && arr[i].y < arr[j].y && arr[j].dir == 3) {
								queue.add(new Crash(i, j, arr[j].y - arr[i].y));
							}
						}
					}
//					하
					if(arr[i].dir == 1) {
						if(arr[i].x == arr[j].x && arr[i].y > arr[j].y && arr[j].dir == 0) {
							queue.add(new Crash(i, j, (arr[i].y - arr[j].y)/2.0));
						}
						
						else if(Math.abs(arr[i].x - arr[j].x) == Math.abs(arr[i].y - arr[j].y)) {
							if(arr[i].x < arr[j].x && arr[i].y > arr[j].y && arr[j].dir == 2) {
								queue.add(new Crash(i, j, arr[i].y - arr[j].y));
							}
							else if(arr[i].x > arr[j].x && arr[i].y > arr[j].y && arr[j].dir == 3) {
								queue.add(new Crash(i, j, arr[i].y - arr[j].y));
							}
						}
					}
//					좌
					if(arr[i].dir == 2) {
						if(arr[i].y == arr[j].y && arr[i].x > arr[j].x && arr[j].dir == 3) {
							queue.add(new Crash(i, j, (arr[i].x - arr[j].x)/2.0));
						}
						
						else if(Math.abs(arr[i].x - arr[j].x) == Math.abs(arr[i].y - arr[j].y)) {
							if(arr[i].x > arr[j].x && arr[i].y < arr[j].y && arr[j].dir == 1) {
								queue.add(new Crash(i, j, arr[i].x - arr[j].x));
							}
							else if(arr[i].x > arr[j].x && arr[i].y > arr[j].y && arr[j].dir == 0) {
								queue.add(new Crash(i, j, arr[i].x - arr[j].x));
							}
						}
					}
//					우
					if(arr[i].dir == 3) {
						if(arr[i].y == arr[j].y && arr[i].x < arr[j].x && arr[j].dir == 2) {
							queue.add(new Crash(i, j, (arr[j].x - arr[i].x)/2.0));
						}
						
						else if(Math.abs(arr[i].x - arr[j].x) == Math.abs(arr[i].y - arr[j].y)) {
							if(arr[i].x < arr[j].x && arr[i].y < arr[j].y && arr[j].dir == 1) {
								queue.add(new Crash(i, j, arr[j].x - arr[i].x));
							}
							else if(arr[i].x < arr[j].x && arr[i].y > arr[j].y && arr[j].dir == 0) {
								queue.add(new Crash(i, j, arr[j].x - arr[i].x));
							}
						}
					}
				}
			}
			
			long answer = 0;
			while(!queue.isEmpty()) {
				Crash now = queue.poll();
//				터진적 없으면
				if(crashTime[now.start] == 0 && crashTime[now.end] == 0) {
					crashTime[now.start] = now.time;
					crashTime[now.end] = now.time;
					answer+=arr[now.start].k;
					answer+=arr[now.end].k;
				}
				else if(crashTime[now.end] == 0 && crashTime[now.start] != 0 && crashTime[now.start] == now.time) {
					crashTime[now.end] = now.time;
					answer+=arr[now.end].k;
				}
				else if(crashTime[now.start] == 0 && crashTime[now.end] != 0 && crashTime[now.end] == now.time) {
					crashTime[now.start] = now.time;
					answer+=arr[now.start].k;
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static class Crash{
		int start, end;
		double time;
		public Crash(int start, int end, double time) {
			super();
			this.start = start;
			this.end = end;
			this.time = time;
		}

		
	}
	
	static class Node{
		int x, y, dir, k;

		public Node(int x, int y, int dir, int k) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}
}