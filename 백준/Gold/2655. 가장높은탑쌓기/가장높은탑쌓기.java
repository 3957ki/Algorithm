import jdk.nashorn.internal.ir.Block;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Block[] arr = new Block[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[i] = new Block(i+1, area, height, weight);
        }

        Arrays.sort(arr, (o1, o2) -> o1.area - o2.area);

        Node[] dp = new Node[N];
        for (int i = 0; i < N; i++) dp[i] = new Node();

        int maxHeight = 0;
        int answer = 0;

        for(int i = 0; i < N; i++) {
            Block now = arr[i];
            dp[i].total = now.height;
            dp[i].maxWeight = now.weight;
            dp[i].list.add(now.num);

            for(int j = i-1; j >= 0; j--) {
                if(dp[j].maxWeight <= now.weight && dp[i].total < dp[j].total + arr[i].height) {
                    dp[i].total = dp[j].total + arr[i].height;
                    dp[i].list = new ArrayList<>();
                    dp[i].list.add(now.num);
                    dp[i].list.addAll(dp[j].list);
                }
            }

            if(maxHeight < dp[i].total) {
                maxHeight = dp[i].total;
                answer = i;
            }
        }

        sb.append(dp[answer].list.size()).append('\n');
        Collections.reverse(dp[answer].list);
        for (Integer num : dp[answer].list) {
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }

    static class Node {
        int total, maxWeight;
        List<Integer> list = new ArrayList<>();
    }

    static class Block {
        int num, area, height, weight;

        public Block(int num, int area, int height, int weight) {
            this.num = num;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }
}