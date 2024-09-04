import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int L, answer;
	static List<Node> personList;
	static List<Node> stairList;
	static int[][] edges;
	static int[] stairTime = new int[2];
	static List<Integer> stair0 = new ArrayList<>();
	static List<Integer> stair1 = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = Integer.MAX_VALUE;
			int N = Integer.parseInt(br.readLine());
			
			personList = new ArrayList<>();
			stairList = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
//					사람
					if(num == 1) {
						personList.add(new Node(i, j));
					}
//					계단
					if(num > 1) {
						stairList.add(new Node(i, j, num));
					}
				}
			}
			
//			사람 수
			L = personList.size();
			edges = new int[L][2];
			
//			계단 2개 각각의 사람과의 거리 저장
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < 2; j++) {
					edges[i][j] = Math.abs(personList.get(i).y - stairList.get(j).y) + Math.abs(personList.get(i).x - stairList.get(j).x);
				}
			}
			
//			계단별 소요시간 저장
			stairTime[0] = stairList.get(0).k;
			stairTime[1] = stairList.get(1).k;
			
//			부분집합으로 계단 정하기
			subset(0, 0);
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static void subset(int cnt, int select) {
		if(cnt == L) {
			calc(select);
			return;
		}
		
		subset(cnt+1, select);
		subset(cnt+1, select|1<<cnt);
	}
	
	static void calc(int select) {
		
		int dist;
		for(int i = 0; i < L; i++) {
//			첫번째 계단을 고른 경우
			if((select & 1<<i) == 0) {
				dist = edges[i][0];
				stair0.add(dist);
			}
//			두번째 계단을 고른 경우
			else if((select & 1<<i) != 0) {
				dist = edges[i][1];
				stair1.add(dist);
			}
		}
		
		int cnt = 0;
		int time0 = 0;
		int time1 = 0;
		int size0 = stair0.size();
		int size1 = stair1.size();
		int visited = 0;
//		내림차순으로 정렬하기
		Collections.sort(stair0);
		Collections.sort(stair1);
		
//		첫번째 계단 시뮬레이션
		if(size0 != 0) {
			while(true) {
//				모두 탈출했으면 break;
				if(visited == (1<<size0) -1) break;
				time0++;
				
				for(int i = 0; i < size0; i++) {
//					내려간 사람이면 패스
					if((visited & 1<<i) != 0) continue;
					
//					입구에 이미 도착한 사람이면
					if(stair0.get(i) == 0) {
//						내려갈 수 있으면 내려가기
						if(cnt != 3) {
							stair0.set(i, stair0.get(i)-1);
							cnt++;
						}
//						아니면 변동없음
					}
					
//					내려가고 있던 사람이라면
					else if(stair0.get(i) < 0) {
//						도착했다면
						if(stair0.get(i) == -stairTime[0]) {
//							도착처리
							visited |= 1<<i;
							cnt--;
						}
//						계속 내려가기
						else {
							stair0.set(i, stair0.get(i)-1);
						}
					}
					
//					이동
					else {
						stair0.set(i, stair0.get(i)-1);
					}
				}
			}
		}
		
		visited = 0;
		
//		두번째 계단 시뮬레이션
		if(size1 != 0) {
			while(true) {
//				모두 탈출했으면 break;
				if(visited == (1<<size1) -1) break;
				time1++;
				
				for(int i = 0; i < size1; i++) {
//					내려간 사람이면 패스
					if((visited & 1<<i) != 0) continue;
					
//					입구에 이미 도착한 사람이면
					if(stair1.get(i) == 0) {
//						내려갈 수 있으면 내려가기
						if(cnt != 3) {
							stair1.set(i, stair1.get(i)-1);
							cnt++;
						}
//						아니면 변동없음
					}
//					내려가고 있던 사람이라면
					else if(stair1.get(i) < 0) {
//						도착했다면
						if(stair1.get(i) == -stairTime[1]) {
//							도착처리
							visited |= 1<<i;
							cnt--;
						}
//						계속 내려가기
						else {
							stair1.set(i, stair1.get(i)-1);
						}
					}
					
//					이동
					else {
						stair1.set(i, stair1.get(i)-1);
					}
				}
			}
		}
		
//		최소값 갱신
		answer = Math.min(answer, Math.max(time0, time1));
		stair0.clear();
		stair1.clear();
	}
	
	static class Node{
		int y, x, k;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Node(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
		
	}
}