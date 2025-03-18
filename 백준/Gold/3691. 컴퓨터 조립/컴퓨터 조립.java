import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Map<String, List<Node>> map = new HashMap<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				String name = st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				int quality = Integer.parseInt(st.nextToken());

				// 이미 타입이 있다면
				if (map.containsKey(type)) {
					map.get(type).add(new Node(price, quality));
				}

				// 없다면
				else {
					List<Node> list = new ArrayList<>();
					list.add(new Node(price, quality));
					map.put(type, list);
				}
			}

			List<Node[]> list = new ArrayList<>();

			for (String type : map.keySet()) {
				List<Node> nodes = map.get(type);

				// 가격 오름차순 정렬
				Collections.sort(nodes, (o1, o2) -> {
					if (o1.price == o2.price) {
						return o2.quality - o1.quality;
					}
					return o1.price - o2.price;
				});

				// 현재 최대 성능
				int max = 0;
				for (Node node : nodes) {
					// 현재 성능이 max보다 크다면 업데이트
					if (node.quality > max) {
						max = node.quality;
					}

					// 아니라면 max로 변경
					else {
						node.quality = max;
					}
				}
				list.add(nodes.toArray(new Node[0]));
			}

			int L = list.size();

			int left = 0;
			int right = 1000000000;
			int mid;

			int maxQuality = 0;
			while (left <= right) {

				// 성능
				mid = (left + right) >> 1;

				// 현재 들인 돈
				int sum = 0;

				boolean flag = false;
				for (int i = 0; i < L; i++) {
					Node[] nodes = list.get(i);
					int low = 0;
					int high = nodes.length - 1;
					int midmid;

					int minPrice = Integer.MAX_VALUE;

					while (low <= high) {
						// 인덱스
						midmid = (low + high) >> 1;

						// true
						if (nodes[midmid].quality >= mid) {
							minPrice = Math.min(minPrice, nodes[midmid].price);
							high = midmid - 1;
						}

						// false
						else {
							low = midmid + 1;
						}
					}

					// 성능 충족 못시킴
					if (minPrice == Integer.MAX_VALUE) {
						flag = true;
						break;
					}
					sum += minPrice;

					// 예산 초과
					if (sum > M) {
						flag = true;
						break;
					}
				}

				// 불가능
				if (flag) {
					right = mid - 1;
				} else {
					maxQuality = Math.max(maxQuality, mid);
					left = mid + 1;
				}
			}
			sb.append(maxQuality).append('\n');
		}
		System.out.println(sb);
	}

	static class Node {
		int price, quality;

		public Node(int price, int quality) {
			this.price = price;
			this.quality = quality;
		}
	}
}