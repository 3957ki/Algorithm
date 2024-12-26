import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 종류별 개수
        int[] count = new int[d+1];

        int answer = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> temp_q = new ArrayDeque<>();


        for (int i = 0; i < k; i++) {
            int now = Integer.parseInt(br.readLine());
            queue.add(now);
            temp_q.add(now);
            if(count[now] == 0 & ++count[now] == 1) answer++;
        }

        if(count[c] == 0) answer++;
        int max = answer;

        for (int i = k; i < N; i++) {
            int prev = queue.poll();

            if(--count[prev] == 0 && prev != c) answer--;

            int now = Integer.parseInt(br.readLine());
            queue.add(now);
            if(count[now] == 0 & ++count[now] == 1 && now != c) answer++;

            max=Math.max(max, answer);
        }

        while(!temp_q.isEmpty()) {
            int prev = queue.poll();

            if(--count[prev] == 0 && prev != c) answer--;

            int now = temp_q.poll();
            queue.add(now);
            if(count[now] == 0 & ++count[now] == 1 && now != c) answer++;

            max=Math.max(max, answer);
        }

        System.out.println(max);
    }
}